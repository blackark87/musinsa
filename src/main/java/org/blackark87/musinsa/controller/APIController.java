package org.blackark87.musinsa.controller;

import lombok.RequiredArgsConstructor;
import org.blackark87.musinsa.model.RequestObject;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.service.APIService;
import org.springframework.web.bind.annotation.*;
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
	public Mono<ResponseObject.LowestPriceBrandItem> getLowestPriceBrandItem() {
		return Mono.just(apiService.getLowestPriceBrand());
	}

	@GetMapping("/get-high-low-item/{category}")
	public Mono<ResponseObject.HighLowItem> getHighLowItem(@PathVariable("category") String category) {
		return Mono.just(apiService.getHighLowItem(category));
	}

	@PostMapping("/item")
	public Mono<ResponseObject.plainResult> saveItem(@RequestBody RequestObject.Item item) {
		return Mono.just(apiService.saveItem(item));
	}

	@DeleteMapping("/item")
	public Mono<ResponseObject.plainResult> deleteItem(@RequestBody RequestObject.Item item) {
		return Mono.just(apiService.deleteItem(item));
	}

	@PatchMapping("/item/{brand}/{category}")
	public Mono<ResponseObject.plainResult> updateItem(@PathVariable("brand") String brand,
	                                               @PathVariable("category") String category,
	                                               @RequestBody RequestObject.Item item) {
		return Mono.just(apiService.updateItem(brand, category, item));
	}
}
