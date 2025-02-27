package exam.goyjin.musinsa.model;

import java.util.List;

public class ResponseObject {

	public record LowestPriceItem(
			List<Item> items,
			double totalPrice
	) {
		public LowestPriceItem(List<Item> items) {
			this(items, items.stream().mapToDouble(Item::price).sum());
		}

		@Override
		public double totalPrice() {
			return totalPrice;
		}
	}

	public record LowestPriceBrandItem(
			double totalPrice,
			String brand,
			List<Item> items
	){
		public LowestPriceBrandItem(String brand, List<Item> items) {
			this(items.stream().mapToDouble(Item::price).sum(), brand, items);
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
	) {

	}

	public record HighLowItem(
			String category,
			Item high,
			Item low
	) {

	}

	public record plainResult(
			String result
	){

	}


}
