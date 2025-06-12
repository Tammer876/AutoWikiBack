-- V2__update_user_table.sql

ALTER TABLE users
ADD COLUMN password_reset_token VARCHAR(255);