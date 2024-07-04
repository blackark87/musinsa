insert into item(id, brand, category, price, stock, created_at, updated_at)
	(select RANDOM_UUID(), brand, category, price, stock, now(), now()
	 from csvread('src/main/resources/data/item.csv', null, 'charset=UTF-8 fieldSeparator=,'));