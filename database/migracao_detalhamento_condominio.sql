-- Execute uma vez em bancos criados antes das colunas de detalhamento.
USE `Condominio`;
SET NAMES utf8mb4;

ALTER TABLE `condominio`
  ADD COLUMN `valor_agua` DECIMAL(20,2) NULL,
  ADD COLUMN `valor_gas` DECIMAL(20,2) NULL,
  ADD COLUMN `valor_rateio_unidade` DECIMAL(20,2) NULL,
  ADD COLUMN `valor_rateio_m2` DECIMAL(20,2) NULL;

-- Recupera a composição das cobranças geradas no formato anterior.
UPDATE `condominio`
SET `valor_rateio_unidade` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(`observacao`, ';', 1), ' ', -1) AS DECIMAL(20,2)),
    `valor_rateio_m2` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(`observacao`, ';', 2), ' ', -1) AS DECIMAL(20,2)),
    `valor_agua` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(`observacao`, ';', 3), ' ', -1) AS DECIMAL(20,2)),
    `valor_gas` = CAST(SUBSTRING_INDEX(`observacao`, ' ', -1) AS DECIMAL(20,2))
WHERE `observacao` REGEXP '^Gerado: Und -?[0-9]{1,18}[.][0-9]{2}; m² -?[0-9]{1,18}[.][0-9]{2}; Água -?[0-9]{1,18}[.][0-9]{2}; Gás -?[0-9]{1,18}[.][0-9]{2}$';
