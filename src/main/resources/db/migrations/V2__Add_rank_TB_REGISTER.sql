-- V2: Migration to add 'rank' column to 'TB_REGISTER' table
ALTER TABLE tb_register
ADD COLUMN rank VARCHAR(255);