-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Condominio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Condominio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Condominio` DEFAULT CHARACTER SET utf8 ;
USE `Condominio` ;

-- -----------------------------------------------------
-- Table `Condominio`.`area_compartilhada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`area_compartilhada` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`edificio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`edificio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `quantidade_andares` INT NOT NULL,
  `quantidade_unidades` INT NOT NULL,
  `cnpj` CHAR(14) NOT NULL,
  `ano_lancamento` CHAR(4) NOT NULL,
  `area_total` DOUBLE NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `logradouro` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NOT NULL,
  `numero_unidade_agua` VARCHAR(45) NOT NULL,
  `numero_unidade_gas` VARCHAR(45) NOT NULL,
  `formula_calculo` VARCHAR(45) NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`area_compartilhada_edificio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`area_compartilhada_edificio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `area_compartilhada_id` INT NOT NULL,
  `edificio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_area_compartilhada_edificio_area_compartilhada1_idx` (`area_compartilhada_id` ASC)  ,
  INDEX `fk_area_compartilhada_edificio_edificio1_idx` (`edificio_id` ASC)  ,
  CONSTRAINT `fk_area_compartilhada_edificio_area_compartilhada1`
    FOREIGN KEY (`area_compartilhada_id`)
    REFERENCES `Condominio`.`area_compartilhada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_area_compartilhada_edificio_edificio1`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `Condominio`.`edificio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`proprietario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`proprietario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_fantasia` VARCHAR(100) NOT NULL,
  `razao_social` VARCHAR(100) NULL,
  `cpf` CHAR(11) NULL,
  `rg` CHAR(10) NULL,
  `cnpj` CHAR(14) NULL,
  `inscricao_estadual` VARCHAR(45) NULL,
  `fone1` VARCHAR(14) NOT NULL,
  `fone2` VARCHAR(14) NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_nascimento` DATE NULL,
  `data_cadastro` DATE NOT NULL,
  `estado_civil` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(100) NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `metragem_total` DOUBLE NOT NULL,
  `metragem_individual` DOUBLE NOT NULL,
  `tipo_unidade` VARCHAR(45) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `edificio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_unidade_edificio1_idx` (`edificio_id` ASC)  ,
  CONSTRAINT `fk_unidade_edificio1`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `Condominio`.`edificio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`unidade_condomino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`unidade_condomino` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_aquisicao` DATE NOT NULL,
  `data_venda` DATE NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `proprietario_id` INT NOT NULL,
  `unidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_unidade_condomino_proprietario1_idx` (`proprietario_id` ASC)  ,
  INDEX `fk_unidade_condomino_unidade1_idx` (`unidade_id` ASC)  ,
  CONSTRAINT `fk_unidade_condomino_proprietario1`
    FOREIGN KEY (`proprietario_id`)
    REFERENCES `Condominio`.`proprietario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_unidade_condomino_unidade1`
    FOREIGN KEY (`unidade_id`)
    REFERENCES `Condominio`.`unidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_hora_inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_hora_fim` DATETIME NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `area_compartilhada_edificio_id` INT NOT NULL,
  `unidade_condominio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reserva_area_compartilhada_edificio_idx` (`area_compartilhada_edificio_id` ASC)  ,
  INDEX `fk_reserva_unidade_condominio1_idx` (`unidade_condominio_id` ASC)  ,
  CONSTRAINT `fk_reserva_area_compartilhada_edificio`
    FOREIGN KEY (`area_compartilhada_edificio_id`)
    REFERENCES `Condominio`.`area_compartilhada_edificio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_unidade_condominio1`
    FOREIGN KEY (`unidade_condominio_id`)
    REFERENCES `Condominio`.`unidade_condomino` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`custo_nivel1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`custo_nivel1` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo_cc` VARCHAR(45) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`custo_nivel2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`custo_nivel2` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `custo_nivel1_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_custo_nivel2_custo_nivel11_idx` (`custo_nivel1_id` ASC)  ,
  CONSTRAINT `fk_custo_nivel2_custo_nivel11`
    FOREIGN KEY (`custo_nivel1_id`)
    REFERENCES `Condominio`.`custo_nivel1` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_fantasia` VARCHAR(100) NOT NULL,
  `razao_social` VARCHAR(45) NULL,
  `cpf` CHAR(11) NULL,
  `rg` CHAR(7) NULL,
  `cnpj` CHAR(14) NULL,
  `inscricao_estadual` VARCHAR(45) NULL,
  `fone1` VARCHAR(45) NOT NULL,
  `fone2` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `data_nascimento` DATE NULL,
  `data_cadastro` DATE NOT NULL,
  `estado_civil` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`movimento_caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`movimento_caixa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_emissao` DATE NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `data_pagamento` DATE NULL,
  `valor_emitido` DOUBLE NOT NULL,
  `multas` DOUBLE NULL,
  `correcao_monetaria` DOUBLE NULL,
  `juros` DOUBLE NULL,
  `valor_pagamento` DOUBLE NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `flag_rateio` TINYINT NULL,
  `flag_formula` VARCHAR(45) NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `edificio_id` INT NOT NULL,
  `custo_nivel2_id` INT NOT NULL,
  `fornecedor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movimento_caixa_edificio1_idx` (`edificio_id` ASC)  ,
  INDEX `fk_movimento_caixa_custo_nivel21_idx` (`custo_nivel2_id` ASC)  ,
  INDEX `fk_movimento_caixa_fornecedor1_idx` (`fornecedor_id` ASC)  ,
  CONSTRAINT `fk_movimento_caixa_edificio1`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `Condominio`.`edificio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimento_caixa_custo_nivel21`
    FOREIGN KEY (`custo_nivel2_id`)
    REFERENCES `Condominio`.`custo_nivel2` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimento_caixa_fornecedor1`
    FOREIGN KEY (`fornecedor_id`)
    REFERENCES `Condominio`.`fornecedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`leitura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`leitura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_leitura` DATE NOT NULL,
  `mes_referencia` INT NOT NULL,
  `ano_referencia` INT NOT NULL,
  `medicao_anterior` DOUBLE NULL,
  `medicao_atual` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `unidade_condomino_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_leitura_unidade_condomino1_idx` (`unidade_condomino_id` ASC)  ,
  CONSTRAINT `fk_leitura_unidade_condomino1`
    FOREIGN KEY (`unidade_condomino_id`)
    REFERENCES `Condominio`.`unidade_condomino` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`condominio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`condominio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mes_referencia` INT NOT NULL,
  `ano_referencia` INT NOT NULL,
  `data_emissao` DATE NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `data_pagamento` DATE NULL,
  `juros` DOUBLE NULL,
  `multas` DOUBLE NULL,
  `correcao` DOUBLE NULL,
  `valor_emitido` DOUBLE NOT NULL,
  `valor_pago` DOUBLE NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `unidade_condomino_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_condominio_unidade_condomino1_idx` (`unidade_condomino_id` ASC)  ,
  CONSTRAINT `fk_condominio_unidade_condomino1`
    FOREIGN KEY (`unidade_condomino_id`)
    REFERENCES `Condominio`.`unidade_condomino` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`sindico_profissional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`sindico_profissional` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cra` VARCHAR(45) NOT NULL,
  `nome_fantasia` VARCHAR(100) NOT NULL,
  `razao_social` VARCHAR(100) NULL,
  `cpf` CHAR(11) NULL,
  `rg` CHAR(10) NULL,
  `cnpj` CHAR(14) NULL,
  `inscricao_estadual` VARCHAR(45) NULL,
  `fone1` VARCHAR(14) NOT NULL,
  `fone2` VARCHAR(14) NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_nascimento` DATE NULL,
  `data_cadastro` DATE NOT NULL,
  `estado_civil` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(100) NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cra_UNIQUE` (`cra` ASC)  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Condominio`.`funcao_mandato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Condominio`.`funcao_mandato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `funcao` VARCHAR(100) NOT NULL,
  `data_inicio` DATE NOT NULL,
  `data_fim` DATE NULL,
  `observacao` VARCHAR(100) NULL,
  `status` CHAR(1) NOT NULL,
  `edificio_id` INT NOT NULL,
  `proprietario_id` INT NULL,
  `sindico_profissional_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_funcao_mandato_edificio1_idx` (`edificio_id` ASC)  ,
  INDEX `fk_funcao_mandato_proprietario1_idx` (`proprietario_id` ASC)  ,
  INDEX `fk_funcao_mandato_sindico_profissional1_idx` (`sindico_profissional_id` ASC)  ,
  CONSTRAINT `fk_funcao_mandato_edificio1`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `Condominio`.`edificio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcao_mandato_proprietario1`
    FOREIGN KEY (`proprietario_id`)
    REFERENCES `Condominio`.`proprietario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcao_mandato_sindico_profissional1`
    FOREIGN KEY (`sindico_profissional_id`)
    REFERENCES `Condominio`.`sindico_profissional` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
