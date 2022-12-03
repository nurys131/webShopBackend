--liquibase formatted sql
--changeset nurys:2
alter table product add image varchar(128) after currency;