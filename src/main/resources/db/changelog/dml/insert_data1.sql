--liquibase formatted sql
--changeset lidij:add_data_roles_1
insert into roles (name) values('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_MANUFACTURER'),
('ROLE_COURIER');
--changeset lidij:add_data_users_1
insert into users (balance,email,nicname,password,phonenumber,username, role_id )
values (10000.00,'user@mail.ru','USER', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','9111111111','user', 1);

