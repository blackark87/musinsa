package org.blackark87.musinsa.controller;

import lombok.RequiredArgsConstructor;
import org.blackark87.musinsa.model.ResponseObject;
import org.blackark87.musinsa.service.APIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

	private final APIService apiService;

	@GetMapping("/lowest-price-item")
	public Mono<ResponseObject.LowestPriceItem> getLowestPriceItem() {
		return apiService.getLowestPrice();
	}
}
