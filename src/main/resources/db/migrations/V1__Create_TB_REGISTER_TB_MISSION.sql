-- v1 - Initial migration to create tb_register and tb_mission tables

-- Missions table
CREATE TABLE tb_mission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `rank` VARCHAR(50)
);

-- Ninjas table
CREATE TABLE tb_register (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    image_url VARCHAR(255),
    age INT NOT NULL,
    mission_id BIGINT,
    CONSTRAINT fk_mission
        FOREIGN KEY (mission_id)
        REFERENCES tb_mission(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);