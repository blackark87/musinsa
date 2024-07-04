package org.blackark87.musinsa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blackark87.musinsa.entity.ItemEntity;
import org.blackark87.musinsa.enums.ItemCategoryEnum;
import org.blackark87.musinsa.enums.ResponseExceptionCodeEnums;
import org.blackark87.musinsa.exceptions.CustomException;
import org.blackark87.musinsa.model.RequestObject;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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

	public ResponseObject.LowestPriceItem getLowestPriceBrand() {
		return null;
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

	public String saveItem(RequestObject.Item item) {
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

		itemEntity.setStock(1);
		itemRepository.save(itemEntity);
		return "OK";
	}

	public String deleteItem(RequestObject.Item item) {
		ItemEntity itemEntity = itemRepository.findByBrandAndCategory(item.brand(), item.category()).orElseThrow(() -> new CustomException(ResponseExceptionCodeEnums.NOT_EXIST));
		itemRepository.delete(itemEntity);
		return "OK";
	}

	public String updateItem(String brand, String category, RequestObject.Item item) {
		ItemEntity itemEntity = itemRepository.findByBrandAndCategory(brand, category).orElseThrow(() -> new CustomException(ResponseExceptionCodeEnums.NOT_EXIST));

		Optional.of(item.brand()).ifPresent(itemEntity::setBrand);
		Optional.of(item.category()).ifPresent(itemEntity::setCategory);
		Optional.of(item.price()).ifPresent(itemEntity::setPrice);

		itemRepository.save(itemEntity);

		return "OK";
	}
}
