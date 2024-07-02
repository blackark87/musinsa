package org.blackark87.musinsa.model;

public class ResponseObject {

	public record LowestPriceItem(
		Item top,
		Item outer,
		Item pants,
		Item shoes,
		Item bag,
		Item hat,
		Item socks,
		Item accessory
	){

	}

	public record Item(
			String brand,
			long price
	){

	}


}
