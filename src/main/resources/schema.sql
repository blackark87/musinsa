create table item
(
	id       UUID primary key,
	brand     varchar(50),
	category  varchar(50),
	price   double,
	created_at timestamp,
	updated_at timestamp
);