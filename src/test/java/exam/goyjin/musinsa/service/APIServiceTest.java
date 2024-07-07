package exam.goyjin.musinsa.service;

import exam.goyjin.musinsa.model.RequestObject;
import exam.goyjin.musinsa.model.ResponseObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ComponentScan(basePackages = "org.blackark87.musinsa")
@Sql(scripts = {"/data.sql"})
class APIServiceTest {

	@Autowired
	private APIService apiService;

	@Test
	void getLowestPrice() {
		ResponseObject.LowestPriceItem lowestPriceItem = apiService.getLowestPrice();
		Assertions.assertNotNull(lowestPriceItem);
		Assertions.assertEquals(34100D, lowestPriceItem.totalPrice());
	}

	@Test
	void getHighLowItem() {
		ResponseObject.HighLowItem highLowItem = apiService.getHighLowItem("top");
		Assertions.assertNotNull(highLowItem);

		Assertions.assertEquals("top", highLowItem.high().category());
		Assertions.assertEquals(11400D, highLowItem.high().price());

		Assertions.assertEquals("top", highLowItem.low().category());
		Assertions.assertEquals(10000D, highLowItem.low().price());

		Assertions.assertEquals("top", highLowItem.category());
	}

	@Test
	void getLowestPriceBrand(){
		ResponseObject.LowestPriceBrandItem lowestPriceBrandItem = apiService.getLowestPriceBrand();
		Assertions.assertNotNull(lowestPriceBrandItem);
		Assertions.assertEquals("D", lowestPriceBrandItem.brand());
		Assertions.assertEquals(36100D, lowestPriceBrandItem.totalPrice());

	}

	@Test
	void addItem() {
		RequestObject.Item item = new RequestObject.Item("Z", "top", 10000D);
		ResponseObject.plainResult result = apiService.addItem(item);
		Assertions.assertEquals("OK", result.result());
	}

	@Test
	void deleteItem(){
		RequestObject.Item item = new RequestObject.Item("Z", "top", 10000D);
		apiService.addItem(item);

		ResponseObject.plainResult result = apiService.deleteItem(item);
		Assertions.assertEquals("OK", result.result());
	}

	@Test
	void updateItem(){
		RequestObject.Item item = new RequestObject.Item("brand", "category", 10000D);
		ResponseObject.plainResult result = apiService.updateItem("brand", "category", item);
		Assertions.assertEquals("OK", result.result());
	}
}