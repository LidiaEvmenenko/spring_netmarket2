--liquibase formatted sql
--changeset lidij:create_table_categories
create table categories (
   id serial primary key,
   title varchar(30) not null unique
);
--changeset lidij:create_table_manufacturers1
create table manufacturers (
   id serial primary key,
   title varchar(30) not null unique,
   balance numeric(8, 2)
);


