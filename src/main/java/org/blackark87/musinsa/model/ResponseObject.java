package org.blackark87.musinsa.model;

import java.util.List;

public class ResponseObject {

	public record LowestPriceItem(
		List<Item> items,
		double totalPrice
	){
		public LowestPriceItem(List<Item> items) {
			this(items, items.stream().mapToDouble(Item::price).sum());
		}

		@Override
		public double totalPrice() {
			return totalPrice;
		}
	}

	public record Item(
			String brand,
			String category,
			double price
	){

	}


}
