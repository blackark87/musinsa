package exam.goyjin.musinsa.controller;

import exam.goyjin.musinsa.model.RequestObject;
import exam.goyjin.musinsa.service.APIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import exam.goyjin.musinsa.model.ResponseObject;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "API", description = "API Controller")
public class APIController {

	private final APIService apiService;

	@GetMapping("/lowest-price-items")
	@Operation(summary = "Get Lowest Price Items", description = "Get Lowest Price Items")
	public Mono<ResponseObject.LowestPriceItem> getLowestPriceItem() {
		return Mono.just(apiService.getLowestPrice());
	}

	@GetMapping("/lowest-price-brand-items")
	@Operation(summary = "Get Lowest Price Brand Items", description = "Get Lowest Price Brand Items")
	public Mono<ResponseObject.LowestPriceBrandItem> getLowestPriceBrandItem() {
		return Mono.just(apiService.getLowestPriceBrand());
	}

	@GetMapping("/high-low-item/{category}")
	@Operation(summary = "Get High Low Item", description = "Get High Low Item")
	public Mono<ResponseObject.HighLowItem> getHighLowItem(@PathVariable("category") String category) {
		return Mono.just(apiService.getHighLowItem(category));
	}

	@PostMapping("/item")
	@Operation(summary = "Save Item", description = "Save Item")
	public Mono<ResponseObject.plainResult> addItem(@RequestBody RequestObject.Item item) {
		return Mono.just(apiService.addItem(item));
	}

	@DeleteMapping("/item")
	@Operation(summary = "Delete Item", description = "Delete Item")
	public Mono<ResponseObject.plainResult> deleteItem(@RequestBody RequestObject.Item item) {
		return Mono.just(apiService.deleteItem(item));
	}

	@PatchMapping("/item/{brand}/{category}")
	@Operation(summary = "Update Item", description = "Update Item")
	public Mono<ResponseObject.plainResult> updateItem(@PathVariable("brand") String brand,
	                                               @PathVariable("category") String category,
	                                               @RequestBody RequestObject.Item item) {
		return Mono.just(apiService.updateItem(brand, category, item));
	}
}
