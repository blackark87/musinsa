package exam.goyjin.musinsa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import exam.goyjin.musinsa.entity.ItemEntity;
import exam.goyjin.musinsa.enums.ItemCategoryEnum;
import exam.goyjin.musinsa.enums.ResponseExceptionCodeEnums;
import exam.goyjin.musinsa.exceptions.CustomException;
import exam.goyjin.musinsa.model.RequestObject;
import exam.goyjin.musinsa.model.ResponseObject;
import exam.goyjin.musinsa.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class APIService {

	private final ItemRepository itemRepository;

	public ResponseObject.LowestPriceItem getLowestPrice() {
		List<String> itemCategoryList = Arrays.stream(ItemCategoryEnum.values()).map(ItemCategoryEnum::getCategory).toList();

		Map<String, ItemEntity> itemEntityMap= new HashMap<>();
		itemCategoryList.forEach(itemCategory -> {
			List<ItemEntity> itemList = itemRepository.findAllByCategoryOrderByPrice(itemCategory);
			itemList.forEach(itemEntity -> {
				if(itemEntityMap.containsKey(itemCategory)){
					itemEntityMap.computeIfPresent(itemCategory, (category, item) -> {
						if(item.getPrice() > itemEntity.getPrice()){
							return itemEntity;
						}
						return item;
					});
				} else{
					itemEntityMap.put(itemCategory, itemEntity);
				}
			});
		});

		List<ResponseObject.Item> itemList = new ArrayList<>();
		itemEntityMap.forEach((category, itemEntity) -> {
			ResponseObject.Item item = new ResponseObject.Item(itemEntity.getBrand(), itemEntity.getCategory(), itemEntity.getPrice());
			itemList.add(item);
		});

		return new ResponseObject.LowestPriceItem(itemList);
	}

	public ResponseObject.LowestPriceBrandItem getLowestPriceBrand() {
		List<ItemEntity> itemEntityList = itemRepository.findAll();

		List<String> brandList = itemEntityList.stream().collect(Collectors.groupingBy(ItemEntity::getBrand)).keySet().stream().toList();

		Map<String, Double> priceByBrandMap = new HashMap<>();

		brandList.forEach(brand -> {
			Double totalPrice = itemEntityList.stream().filter(itemEntity -> itemEntity.getBrand().equals(brand)).mapToDouble(ItemEntity::getPrice).sum();
			priceByBrandMap.put(brand, totalPrice);
		});

		AtomicReference<String> brand = new AtomicReference<>();
		priceByBrandMap.entrySet().stream().min(Map.Entry.comparingByValue()).ifPresent(entry -> brand.set(entry.getKey()));

		List<ResponseObject.Item> itemList = new ArrayList<>();

		itemEntityList.forEach(itemEntity -> {
			if(itemEntity.getBrand().equals(brand.get())){
				ResponseObject.Item item = new ResponseObject.Item(itemEntity.getBrand(), itemEntity.getCategory(), itemEntity.getPrice());
				itemList.add(item);
			}
		});

		return new ResponseObject.LowestPriceBrandItem(brand.get(), itemList);
	}

	public ResponseObject.HighLowItem getHighLowItem(String category) {
		AtomicReference<ResponseObject.Item> highItem = new AtomicReference<>();
		AtomicReference<ResponseObject.Item> lowItem = new AtomicReference<>();

		List<ItemEntity> itemList = itemRepository.findAllByCategoryOrderByPrice(category);

		if(itemList.isEmpty()){
			throw new CustomException(ResponseExceptionCodeEnums.NOT_FOUND);
		}

		itemList.stream().max(Comparator.comparing(ItemEntity::getPrice)).ifPresent(itemEntity -> highItem.set(new ResponseObject.Item(itemEntity.getBrand(), itemEntity.getCategory(), itemEntity.getPrice())));
		itemList.stream().min(Comparator.comparing(ItemEntity::getPrice)).ifPresent(itemEntity -> lowItem.set(new ResponseObject.Item(itemEntity.getBrand(), itemEntity.getCategory(), itemEntity.getPrice())));

		return new ResponseObject.HighLowItem(category, highItem.get(), lowItem.get());
	}

	public ResponseObject.plainResult addItem(RequestObject.Item item) {
		ItemEntity itemEntity = new ItemEntity();

		Optional.of(item.brand()).ifPresentOrElse(itemEntity::setBrand, () -> {
			throw new CustomException(ResponseExceptionCodeEnums.NO_PRICE);
		});

		Optional.of(item.category()).ifPresentOrElse(itemEntity::setCategory, () -> {
			throw new CustomException(ResponseExceptionCodeEnums.NO_CATEGORY);
		});

		Optional.of(item.price()).ifPresentOrElse(itemEntity::setPrice, () -> {
			throw new CustomException(ResponseExceptionCodeEnums.NO_PRICE);
		});

		itemRepository.save(itemEntity);

		return new ResponseObject.plainResult("OK");
	}

	public ResponseObject.plainResult deleteItem(RequestObject.Item item) {
		ItemEntity itemEntity = itemRepository.findByBrandAndCategory(item.brand(), item.category()).orElseThrow(() -> new CustomException(ResponseExceptionCodeEnums.NOT_EXIST));
		itemRepository.delete(itemEntity);
		return new ResponseObject.plainResult("OK");
	}

	public ResponseObject.plainResult updateItem(String brand, String category, RequestObject.Item item) {
		ItemEntity itemEntity = itemRepository.findByBrandAndCategory(brand, category).orElseThrow(() -> new CustomException(ResponseExceptionCodeEnums.NOT_EXIST));

		Optional.of(item.brand()).ifPresent(itemEntity::setBrand);
		Optional.of(item.category()).ifPresent(itemEntity::setCategory);
		Optional.of(item.price()).ifPresent(itemEntity::setPrice);

		itemRepository.save(itemEntity);

		return new ResponseObject.plainResult("OK");
	}
}
