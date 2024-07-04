package org.blackark87.musinsa.controller;

import lombok.RequiredArgsConstructor;
import org.blackark87.musinsa.model.RequestObject;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.service.APIService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public Mono<ResponseObject.LowestPriceItem> getLowestPriceBrandItem() {
		return Mono.just(apiService.getLowestPriceBrand());
	}

	@GetMapping("/get-high-low-item/{category}")
	public Mono<ResponseObject.HighLowItem> getHighLowItem(@PathVariable("category") String category) {
		return Mono.just(apiService.getHighLowItem(category));
	}

	@PostMapping("/item")
	public Mono<ResponseEntity<String>> saveItem(@RequestBody RequestObject.Item item) {
		String result = apiService.saveItem(item);
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result));
	}

	@DeleteMapping("/item")
	public Mono<ResponseEntity<String>> deleteItem(@RequestBody RequestObject.Item item) {
		String result = apiService.deleteItem(item);
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result));
	}

	@PatchMapping("/item/{brand}/{category}")
	public Mono<ResponseEntity<String>> updateItem(@PathVariable("brand") String brand,
	                                               @PathVariable("category") String category,
	                                               @RequestBody RequestObject.Item item) {
		String result = apiService.updateItem(brand, category, item);
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result));
	}
}
