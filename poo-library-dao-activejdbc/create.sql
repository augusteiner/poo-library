# ---------------------------------------------------------------------
# Arquivo de criação da base de dados
# ---------------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS `biblioteca`;

USE `biblioteca`;

SET default_storage_engine=InnoDB;
ALTER DATABASE CHARACTER SET utf8 COLLATE 'utf8_general_ci';

/* CREATE USER IF NOT EXISTS 'biblioteca'@'%' IDENTIFIED BY '123456'; */

GRANT USAGE ON *.* TO 'biblioteca'@'%';

GRANT
    SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE,
    CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT,
    REFERENCES, TRIGGER, UPDATE, LOCK TABLES
ON `biblioteca`.* TO 'biblioteca'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (

    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(100) NOT NULL,
    `cpf` CHAR(11) NOT NULL,

    PRIMARY KEY (`id`)

)
COMMENT '';

DROP TABLE IF EXISTS `item_acervo`;

CREATE TABLE `item_acervo` (

    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `data_aluguel` DATE NOT NULL,
    `data_devolucao` DATE NOT NULL,
    `custo` DECIMAL(12, 4) NOT NULL,

    PRIMARY KEY (`id`)

)
COMMENT '';


