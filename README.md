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

Cada classe concreta de `model` possui um DAO com cadastro, consulta, atualização e exclusão.
As telas ainda utilizam dados demonstrativos e seus controladores não chamam os DAOs.
Para usar o banco nas telas, é preciso ligar os controladores a essas classes e criar
as tabelas com o script acima.
