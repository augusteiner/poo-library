# ---------------------------------------------------------------------
# Arquivo de criação da base de dados
# ---------------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS `biblioteca`;

USE `biblioteca`;

SET storage_engine=InnoDB;
ALTER DATABASE CHARACTER SET utf8 COLLATE 'utf8_general_ci';

/* CREATE USER 'biblioteca'@'localhost' IDENTIFIED BY '123456';

GRANT USAGE ON *.* TO 'biblioteca'@'localhost';

GRANT
    SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE,
    CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT,
    REFERENCES, TRIGGER, UPDATE, LOCK TABLES
ON `biblioteca`.* TO 'biblioteca'@'localhost' WITH GRANT OPTION; */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (

    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(100) NOT NULL,
    `cpf` CHAR(11) NOT NULL,

    PRIMARY KEY (`id`)

)
COMMENT '';


