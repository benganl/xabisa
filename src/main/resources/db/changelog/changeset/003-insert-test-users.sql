--liquibase formatted sql
--changeset benganl:02-initial-create
--------------------------------------------------------------- ROLES ----------------------------------------------------------------
insert into roles(id, name, description, date_created)
values(UNHEX(REPLACE('550e8401-e29b-41d4-a716-446655440000', '-', '')), 'ROLE_USER', 'Default user role', '2024-03-14');

insert into roles(id, name, description, date_created)
values(UNHEX(REPLACE('550e8402-e29b-41d4-a716-446655440002', '-', '')), 'ROLE_ADMIN', 'Default user role', '2024-03-14');

--changeset benganl:04-insert-test-admin
--------------------------------------------------------------- ADMIN USER ----------------------------------------------------------------
insert into users(id, username, pssword, date_created, date_updated)
values(UNHEX(REPLACE('550e8403-e29b-41d4-a716-446655440003', '-', '')), 'admin', '$2a$12$dWTXhWAKuf.LjEu3PD1Oc.Yrzm8MDE5QScjE8xTLPPeCq6JvSOhuC', '2024-03-14', '2024-03-14');

insert into user_roles(role_id, user_id)
values((select id from roles where name = 'ROLE_ADMIN'), (select id from users where username = 'admin'));

--changeset benganl:04-insert-test-user
--------------------------------------------------------------- TEST USER ----------------------------------------------------------------
insert into users(id, username, pssword, date_created, date_updated)
values(UNHEX(REPLACE('550e8405-e29b-41d4-a716-446655440005', '-', '')), 'user', '$2a$12$jEVW2WdeESDCjZkcBkl4v.DmVqn.K2N3MFFYZGvUt5Fel0450hz9W', '2024-03-14', '2024-03-14');

insert into user_roles(role_id, user_id)
values((select id from roles where name = 'ROLE_ADMIN'), (select id from users where username = 'user'));

insert into user_roles(role_id, user_id)
values((select id from roles where name = 'ROLE_USER'), (select id from users where username = 'user'));
