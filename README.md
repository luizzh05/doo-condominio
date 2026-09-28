# doo-condominio

Projeto Java Swing para cadastro e consulta de dados de condomínio (Java 21 / NetBeans).

## Banco de dados

O driver MySQL está em `lib/mysql-connector-java-8.0.13.jar` e já faz parte do classpath do projeto.
`model.DAO.ConnectionFactory` usa, por padrão, `jdbc:mysql://localhost:3306/condominio?useSSL=false&serverTimezone=UTC`
com usuário `root` e senha vazia. Ajuste a conexão com as variáveis de ambiente:

- `CONDOMINIO_DB_URL`: URL JDBC completa;
- `CONDOMINIO_DB_USER`: usuário;
- `CONDOMINIO_DB_PASSWORD`: senha.

Os DAOs de `Proprietario` e `Unidade` fornecem cadastro, consulta, atualização e exclusão.
As telas ainda utilizam dados demonstrativos e seus controladores não chamam os DAOs.
Para usar o banco nas telas, é preciso ligar os controladores a essas classes e dispor
das tabelas `proprietario` e `unidade` com as colunas esperadas pelos DAOs.
