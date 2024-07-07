insert into item(id, brand, category, price, created_at, updated_at)
	(select RANDOM_UUID(), brand, category, price, now(), now()
	 from csvread('src/main/resources/data/item.csv', null, 'charset=UTF-8 fieldSeparator=,'));