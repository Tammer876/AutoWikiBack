-- V5__update_article_table.sql

ALTER TABLE articles
ADD COLUMN approval_token VARCHAR(255);
ALTER TABLE articles
ADD COLUMN denial_token VARCHAR(255);
