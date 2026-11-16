# doo-condominio

Projeto Java Swing para cadastro e consulta de dados de condomínio (Java 21 / NetBeans).

## Banco de dados

O driver MySQL está em `lib/mysql-connector-java-8.0.13.jar` e já faz parte do classpath do projeto.
`model.DAO.ConnectionFactory` usa, por padrão, `jdbc:mysql://localhost:3306/Condominio?useSSL=false&serverTimezone=UTC`
com usuário `root` e senha vazia. Ajuste a conexão com as variáveis de ambiente:

- `CONDOMINIO_DB_URL`: URL JDBC completa;
- `CONDOMINIO_DB_USER`: usuário;
- `CONDOMINIO_DB_PASSWORD`: senha.

O esquema usado pelos DAOs está em `database/schema.sql`. Ele foi baseado no
`CondominioScript.sql` e ajustado para guardar o logradouro do edifício e o tipo
textual da unidade, conforme as telas e os modelos.

## Organização do código

- `view`: formulários Swing;
- `controller.cadastro` e `controller.consulta`: eventos, leitura dos campos e apresentação;
- `service`: operações de cadastro e consulta, além das regras de negócio;
- `model`: entidades;
- `model.DAO`: acesso ao MySQL.

Os controladores chamam os services, que usam os DAOs. Para executar as telas com
dados persistidos, crie as tabelas com `database/schema.sql` e configure a conexão.

Para um banco já criado, aplique `database/migrations/2026_11_16_preco_m3_edificio.sql`
antes de executar a versão com preços de água e gás por m³.
