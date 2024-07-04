package org.blackark87.musinsa.service;

import org.blackark87.musinsa.model.ResponseObject;
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
}