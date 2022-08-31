--liquibase formatted sql
--changeset lidij:create_table_products
create table products (
   id serial primary key,
   title varchar(30) not null unique,
   expiration_date date not null unique,
   price numeric(8,2) not null unique,
   description varchar(500),
   weight numeric(8,3),
   photo bytea;
   category_id bigint references categories (id),
   manufacturer_id bigint references manufacturers (id)
);
--changeset lidij:create_table_carts
create table carts (
   user_id bigint references users (id),
   product_id bigint references products (id),
   amount numeric(7, 3)
);
--changeset lidij:create_table_orders
create table orders (
   user_id bigint references users (id),
   product_id bigint references products (id),
   datetime timestamp,
);
--changeset lidij:create_table_stocks
create table stocks (
   id serial primary key,
   delivery_date date not null unique,
   amount numeric(10,3),
   product_id bigint references products (id)
);
