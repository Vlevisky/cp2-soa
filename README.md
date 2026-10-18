# Checkpoint 2 - API de Pedidos

Projeto da disciplina de Arquitetura SOA e Web Services. A aplicação faz o cadastro e o gerenciamento de pedidos usando uma API REST com Spring Boot.

## Integrantes

- Maria Eduarda Ferrés - RM 560418
- Gabriela Queiroga Cocuzza da Silva - RM 560035
- Rafael Mandel - RM 560333
- Felipe Silva do Prado Lima - RM 559848
- Davi Corrêa Paião - RM 560438

## Tecnologias usadas

- Java 21
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Flyway
- Lombok

## Como executar

É necessário ter o Java 21 instalado.

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

A aplicação será executada na porta `8085`.

## Rotas

| Método | Rota | Função |
|---|---|---|
| POST | `/pedidos` | Cadastrar um pedido |
| GET | `/pedidos` | Listar os pedidos |
| GET | `/pedidos/{id}` | Buscar um pedido pelo ID |
| PUT | `/pedidos/{id}` | Atualizar um pedido |
| DELETE | `/pedidos/{id}` | Excluir um pedido |

Exemplo para cadastrar um pedido:

```json
{
  "clienteNome": "Ana Souza",
  "valorTotal": 149.90
}
```

A data do pedido é preenchida automaticamente. O nome do cliente é obrigatório e o valor total não pode ser negativo.

O arquivo `requests.http` possui exemplos para testar todas as rotas.

## Banco de dados

O projeto usa o banco H2 em memória.

- Console: `http://localhost:8085/h2-console`
- JDBC URL: `jdbc:h2:mem:checkpoint2`
- Usuário: `sa`
- Senha: deixar em branco

## Testes

```powershell
.\mvnw.cmd test
```
