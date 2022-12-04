--liquibase formatted sql
--changeset nurys:6
ALTER TABLE product ADD category_id bigint after category;
ALTER TABLE product DROP column category;
ALTER TABLE product ADD constraint fk_product_category_id FOREIGN KEY (category_id) references category(id);