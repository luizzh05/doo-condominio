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

Não há migrations: o projeto ainda está fora de produção. Para atualizar um banco local
existente, recrie-o com `database/schema.sql` (e, opcionalmente, `database/dados_exemplo.sql`).

## Organização do código

- `view`: formulários Swing;
- `controller.cadastro` e `controller.consulta`: eventos, leitura dos campos e apresentação;
- `service`: operações de cadastro e consulta, além das regras de negócio;
- `model`: entidades;
- `model.DAO`: acesso ao MySQL.

Os controladores chamam os services, que usam os DAOs. Para executar as telas com
dados persistidos, crie as tabelas com `database/schema.sql` e configure a conexão.

## Geração de condomínios

Menu Operações > Gestão de Condomínios. Selecione o edifício, o mês e o ano; o sistema
gera os condomínios do mês ANTERIOR (referência) para todas as unidades ativas do edifício.

- Valor emitido = água e gás (consumo das leituras × preço do m³ do edifício) + rateio dos
  movimentos de caixa do edifício com rateio marcado e data de emissão no mês de referência
  (Despesa soma, Receita abate).
- Fórmula "Por Und." divide igual entre as unidades; "Por m²" divide proporcionalmente à
  metragem individual. Movimento sem fórmula usa a fórmula do edifício.
- É bloqueado se já houver condomínio do edifício naquele mês.
- Só gera meses encerrados.

Para testar com `database/dados_exemplo.sql`: edifício "Edificio Teste Geracao", mês 11,
ano 2026 (referência 10/2026).

### Ações na grade

Selecione um condomínio na grade da Gestão de Condomínios:

- **Editar** (ou duplo clique): abre o cadastro do condomínio já carregado; a grade recarrega ao gravar.
- **Pagar**: mostra o valor pago = valor emitido + multa de 2% (se pago após o vencimento) +
  juros de 1% ao mês proporcional aos dias de atraso + correção (valor informado, padrão 0).
  Grava data, multa, juros, correção e valor pago, e muda o status para Pago.
  Só para condomínios Ativos ou Processados.
- **Processar**: muda o status de Ativo para Processado.
- **Ativar/Desativar**: alterna entre Ativo e Inativo (condomínio Pago não pode ser desativado).

Status gravados: `A` Ativo, `I` Inativo, `R` Processado, `P` Pago.
