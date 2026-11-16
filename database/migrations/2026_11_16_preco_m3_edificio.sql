ALTER TABLE `Condominio`.`edificio`
  ADD COLUMN `preco_m3_agua` DECIMAL(12,4) NOT NULL DEFAULT 0.0000 AFTER `numero_unidade_gas`,
  ADD COLUMN `preco_m3_gas` DECIMAL(12,4) NOT NULL DEFAULT 0.0000 AFTER `preco_m3_agua`;
