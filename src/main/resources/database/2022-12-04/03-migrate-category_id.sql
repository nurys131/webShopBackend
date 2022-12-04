--liquibase formatted sql
--changeset nurys:7
INSERT INTO category(id, name, description, slug) VALUES (1, 'Inne', '', 'inne');
UPDATE product SET category_id=1;
ALTER TABLE product MODIFY category_id bigint NOT NULL;