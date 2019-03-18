CREATE TABLE `conta` (
  `id` bigint(20) NOT NULL,
   `nome` varchar(255) DEFAULT NULL,
  `data_pagamento` datetime NOT NULL,
  `data_vencimento` datetime NOT NULL,
  `valor_original` double NOT NULL,
  `dias_atraso` int NOT NULL ,
  `regra_juros` varchar(100) NOT NULL,
  `valor_corrigido` double  NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `conta`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
