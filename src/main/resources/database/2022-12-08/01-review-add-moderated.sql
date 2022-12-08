--liquibase formatted sql
--changeset nurys:9
alter table review add moderated boolean default false;