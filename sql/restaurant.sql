DROP TABLE IF EXISTS dish_ingredient;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS summary;

CREATE TABLE dish (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	title text NOT NULL,
	description text NOT NULL,
	image bytea NOT NULL,
	price int NOT NULL
);

CREATE TABLE ingredient (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	title text NOT NULL,
	type text NOT NULL
);

CREATE TABLE dish_ingredient (
	dish_id int REFERENCES dish(id),
	ingredient_id int REFERENCES ingredient(id)
);

CREATE TABLE address (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	country text NOT NULL,
	city text  NOT NULL,
	street text NOT NULL,
	number int  NOT NULL
);

CREATE TABLE restaurant (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	title text  NOT NULL,
	address_id int REFERENCES address(id),
	start_time time NOT NULL,
	end_time time NOT NULL
);

CREATE TABLE employee (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	first_name text NOT NULL,
	last_name text NOT NULL,
	salary int NOT  NULL,
	position text NOT NULL,
	photo bytea NOT NULL,
	restaurant_id int REFERENCES restaurant(id)
);

CREATE TABLE summary (
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	first_name text NOT NULL,
	last_name text NOT NULL,
	email text  NOT NULL,
	position text NOT NULL,
	about text NOT NULL
);