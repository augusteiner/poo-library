# ---------------------------------------------------------------------
# Arquivo de criação da base de dados
# ---------------------------------------------------------------------

SET @@FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS `biblioteca`;

USE `biblioteca`;

SET default_storage_engine=InnoDB;
ALTER DATABASE CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci';

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

    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

    `nome` VARCHAR(100) NOT NULL,
    `cpf` CHAR(11) NOT NULL,
    `tipo` ENUM('COMUM', 'ADMIN') NOT NULL DEFAULT 'COMUM',

    `enderecoLogradouro` VARCHAR(100) NULL,
    `enderecoNumero` VARCHAR(10) NULL,

    `criadoEm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `atualizadoEm` TIMESTAMP NULL,

    PRIMARY KEY (`id`)

)
COMMENT '';


DROP TABLE IF EXISTS `item_acervo`;

CREATE TABLE `item_acervo` (

    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

    `data_aluguel` DATE NOT NULL,
    `data_devolucao` DATE NOT NULL,
    `custo` DECIMAL(12, 4) NOT NULL,

    PRIMARY KEY (`id`)

)
COMMENT '';


DROP TABLE IF EXISTS `emprestimo`;

CREATE TABLE `emprestimo` (

    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

    `usuarioId` INT UNSIGNED NOT NULL,
    `itemAcervoId` INT UNSIGNED NOT NULL,

    `valorEmprestimo` DECIMAL(10, 4) NOT NULL DEFAULT 0.0,

    `devolverAte` DATE NOT NULL,
    `devolvidoEm` TIMESTAMP NULL DEFAULT NULL,
    `realizadoEm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `fk_emprestimo_usuario`
        FOREIGN KEY (`usuarioId`)
        REFERENCES `usuario` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT `fk_emprestimo_item_acervo`
        FOREIGN KEY (`itemAcervoId`)
        REFERENCES `item_acervo` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT

)
COMMENT '';


DROP TABLE IF EXISTS `reserva`;

CREATE TABLE `reserva` (

    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

    `usuarioId` INT UNSIGNED NOT NULL,
    `itemAcervoId` INT UNSIGNED NOT NULL,

    `validaAte` DATETIME NOT NULL,
    `realizadaEm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `fk_reserva_usuario`
        FOREIGN KEY (`usuarioId`)
        REFERENCES `usuario` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT `fk_reserva_item_acervo`
        FOREIGN KEY (`itemAcervoId`)
        REFERENCES `item_acervo` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT

)
COMMENT '';

SET @@FOREIGN_KEY_CHECKS=1;


