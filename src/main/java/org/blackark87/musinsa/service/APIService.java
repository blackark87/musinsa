package org.blackark87.musinsa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blackark87.musinsa.enums.ItemCategoryEnum;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class APIService {

	private final ItemRepository itemRepository;

	public Mono<ResponseObject.LowestPriceItem> getLowestPrice() {
		return null;
	}
}
