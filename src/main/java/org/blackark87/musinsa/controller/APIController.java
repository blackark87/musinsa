package org.blackark87.musinsa.controller;

import lombok.RequiredArgsConstructor;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.service.APIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

	private final APIService apiService;

	@GetMapping("/lowest-price-items")
	public Mono<ResponseObject.LowestPriceItem> getLowestPriceItem() {
		return Mono.just(apiService.getLowestPrice());
	}

	@GetMapping("/lowest-price-brand-items")
	public Mono<ResponseObject.LowestPriceItem> getLowestPriceBrandItem() {
		return Mono.just(apiService.getLowestPriceBrand());
	}

	@GetMapping("/get-hign-low-item/{category}")
	public Mono<ResponseObject.HighLowItem> getHighLowItem(@PathVariable("category") String category) {
		return Mono.just(apiService.getHighLowItem(category));
	}
}
