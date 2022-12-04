--liquibase formatted sql
--changeset nurys:4
alter table product add full_description text default null after description;