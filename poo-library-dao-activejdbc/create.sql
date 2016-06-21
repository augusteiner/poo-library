# ---------------------------------------------------------------------
# Arquivo de criação das tabelas
# ---------------------------------------------------------------------

SET storage_engine=InnoDB;
ALTER DATABASE CHARACTER SET utf8 COLLATE 'utf8_general_ci'

CREATE DATABASE 'biblioteca';

CREATE USER 'biblioteca'@'localhost' IDENTIFIED BY '123456';

GRANT USAGE ON *.* TO 'biblioteca'@'localhost';

GRANT
    SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE,
    CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT,
    REFERENCES, TRIGGER, UPDATE, LOCK TABLES
ON `biblioteca`.* TO 'biblioteca'@'localhost' WITH GRANT OPTION;

CREATE TABLE `usuario` (

	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`cpf` CHAR(11) NOT NULL,

	PRIMARY KEY (`id`)

)
COMMENT '';


