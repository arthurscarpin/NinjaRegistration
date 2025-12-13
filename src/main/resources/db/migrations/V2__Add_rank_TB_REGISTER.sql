-- V2: Migration to add 'rank' column to 'tb_register' table
ALTER TABLE tb_register
ADD COLUMN `rank` VARCHAR(255);