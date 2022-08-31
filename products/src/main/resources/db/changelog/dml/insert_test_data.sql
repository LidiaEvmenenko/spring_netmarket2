--liquibase formatted sql
--changeset lidij:add_data_categories_1
insert into categories (title) values('Продукты'),
('Молочные продукты и яйца'),
('Овощи и фрукты'),
('Хлеб и выпечка'),
('Мясо и птица'),
('Колбаса и сосиски'),
('Замороженные продукты'),
('Рыба и морепродукты'),
('Сладости'),
('Напитки'),
('Чай, кофе, какао'),
('Бакалея');
--changeset lidij:add_data_manufacturers_1
insert into manufacturers (manufacturer, balance) values('Царицыно', 0),
('Домик в деревне', 0),
('Простоквашино', 0),
('Велком', 0),
('Черкизово', 0),
('Fish House', 0),
('Аленка', 0),
('Красный Октябрь', 0),
('Бабаевский', 0),
('Агро-Альянс', 0),
('Коломенское', 0);
--changeset lidij:add_data_roles_1
insert into roles (name) values('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_COURIER'),
('ROLE_PROVIDER');
