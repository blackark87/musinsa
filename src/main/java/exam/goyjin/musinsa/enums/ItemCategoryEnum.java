package exam.goyjin.musinsa.enums;

import lombok.Getter;

public enum ItemCategoryEnum {

	TOP("top"),
	OUTER("outer"),
	PANTS("pants"),
	SHOES("shoes"),
	BAG("bag"),
	HAT("hat"),
	SOCKS("socks"),
	ACCESSORY("accessory")
	;


	@Getter
	final String category;

	ItemCategoryEnum(String category) {
		this.category = category;
	}


}
