# ---------------------------------------------------------------------
# Arquivo de criação da base de dados
# ---------------------------------------------------------------------

SET @@FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS `biblioteca`;

USE `biblioteca`;

SET default_storage_engine=InnoDB;
ALTER DATABASE CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci';

GRANT USAGE ON *.* TO 'biblioteca'@'%', 'biblioteca'@'localhost';

GRANT
  SELECT, DELETE, INSERT, UPDATE, LOCK TABLES
ON `biblioteca`.* TO 'biblioteca'@'%', 'biblioteca'@'localhost';

FLUSH PRIVILEGES;

SET PASSWORD FOR 'biblioteca'@'%' = PASSWORD('123456');
SET PASSWORD FOR 'biblioteca'@'localhost' = PASSWORD('123456');

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `nome` VARCHAR(100) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `tipo` ENUM('COMUM', 'ADMIN') NOT NULL,

  `endereco` VARCHAR(100) NULL,

  `criadoEm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `atualizadoEm` TIMESTAMP NULL /*!99999 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP */,

  PRIMARY KEY (`id`)

)
COMMENT '';

DROP TABLE IF EXISTS `biblioteca`;

CREATE TABLE `biblioteca` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `nome` VARCHAR(100) NOT NULL,
  `multaDiaria` DECIMAL(12, 4) NOT NULL DEFAULT 0,

  `qteDiasValidadeReserva` SMALLINT UNSIGNED NOT NULL,
  `qteDiasLocacao` SMALLINT UNSIGNED NOT NULL,

  PRIMARY KEY (`id`)
)
COMMENT '';

DROP TABLE IF EXISTS `item_acervo`;

CREATE TABLE `item_acervo` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `bibliotecaId` INT UNSIGNED NOT NULL,

  `categoria` ENUM('LIVRO', 'TEXTO', 'APOSTILA') NOT NULL,

  `titulo` VARCHAR(100) NULL,
  `autor` VARCHAR(100) NOT NULL,
  `edicao` INT NULL DEFAULT NULL,
  `isbn` VARCHAR(30) NULL DEFAULT NULL,

  `qteTotal` INT UNSIGNED NOT NULL DEFAULT 0,
  `qteDisponivel` INT UNSIGNED NOT NULL DEFAULT 0,

  `precoAluguel` DECIMAL(12, 4) NOT NULL,

  PRIMARY KEY (`id`),

  CONSTRAINT `fk_item_acervo_biblioteca`
    FOREIGN KEY (`bibliotecaId`)
    REFERENCES `biblioteca` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT

)
COMMENT '';


DROP TABLE IF EXISTS `aluguel`;

CREATE TABLE `aluguel` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `usuarioId` INT UNSIGNED NOT NULL,
  `itemAcervoId` INT UNSIGNED NOT NULL,

  `precoCobrado` DECIMAL(12, 4) NOT NULL DEFAULT 0.0,

  `devolverAte` DATE NOT NULL,
  `devolvidoEm` TIMESTAMP NULL DEFAULT NULL,
  `realizadaEm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (`id`),

  CONSTRAINT `fk_aluguel_usuario`
    FOREIGN KEY (`usuarioId`)
    REFERENCES `usuario` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

  CONSTRAINT `fk_aluguel_item_acervo`
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


