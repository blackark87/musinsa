package org.blackark87.musinsa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blackark87.musinsa.entity.ItemEntity;
import org.blackark87.musinsa.enums.ItemCategoryEnum;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

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
					itemEntityMap.computeIfPresent(itemCategory, (k, v) -> {
						if(v.getPrice() > itemEntity.getPrice()){
							return itemEntity;
						}
						return v;
					});
				} else{
					itemEntityMap.put(itemCategory, itemEntity);
				}
			});
		});

		List<ResponseObject.Item> itemList = new ArrayList<>();
		itemEntityMap.forEach((k, v) -> {
			ResponseObject.Item item = new ResponseObject.Item(v.getBrand(), v.getCategory(), v.getPrice());
			itemList.add(item);
		});

		return new ResponseObject.LowestPriceItem(itemList);
	}
}
