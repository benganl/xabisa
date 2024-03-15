--liquibase formatted sql

--changeset benganl:004-insert-updated-date-column
ALTER TABLE roles ADD date_updated timestamp;