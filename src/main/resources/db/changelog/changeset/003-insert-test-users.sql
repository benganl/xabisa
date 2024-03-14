--liquibase formatted sql
--changeset benganl:02-initial-create
--------------------------------------------------------------- ROLES ----------------------------------------------------------------
insert into roles(id, name, description, date_created)
values('f3fda147-de11-49dc-b1d6-dca40f569e71', 'ROLE_USER', 'Default user role', '2024-03-14');

insert into roles(id, name, description, date_created)
values('a272fe96-606b-499c-a8eb-69249308bf80', 'ROLE_ADMIN', 'Default user role', '2024-03-14');


--changeset benganl:04-insert-test-admin
--------------------------------------------------------------- ADMIN USER ----------------------------------------------------------------
insert into users(id, username, pssword, date_created, date_updated)
values('92ab8af1-c77b-4f9c-ad4b-463008c06595', 'admin', '$2a$12$dWTXhWAKuf.LjEu3PD1Oc.Yrzm8MDE5QScjE8xTLPPeCq6JvSOhuC', '2024-03-14', '2024-03-14');

insert into user_roles(id, role_id, user_id)
values('1c5521b5-beea-473e-a27f-6ad03e5b60b2', (select id from roles where name = 'ROLE_ADMIN'), (select id from users where username = 'admin'));

--changeset benganl:04-insert-test-user
--------------------------------------------------------------- TEST USER ----------------------------------------------------------------
insert into users(id, username, pssword, date_created, date_updated)
values('92ab8af1-c77b-4f9c-ad4b-463008c06597', 'user', '$2a$12$jEVW2WdeESDCjZkcBkl4v.DmVqn.K2N3MFFYZGvUt5Fel0450hz9W', '2024-03-14', '2024-03-14');

insert into user_roles(id, role_id, user_id)
values('1c5521b5-beea-473e-a27f-6ad03e5b60b8', (select id from roles where name = 'ROLE_ADMIN'), (select id from users where username = 'user'));

insert into user_roles(id, role_id, user_id)
values('1c5521b5-beea-473e-a27f-6ad03e5b60b9', (select id from roles where name = 'ROLE_USER'), (select id from users where username = 'user'));
