--liquibase formatted sql
--changeset nurys:3
ALTER TABLE product ADD slug varchar(255) AFTER image;
ALTER TABLE product ADD CONSTRAINT ui_product_slug UNIQUE KEY(slug);