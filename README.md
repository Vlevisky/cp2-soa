# CP2 - SOA

CRUD de pedidos feito em Spring Boot para o segundo checkpoint da matéria.

## Integrantes

- Maria Eduarda Ferrés - RM 560418
- Gabriela Queiroga Cocuzza da Silva - RM 560035
- Rafael Mandel - RM 560333
- Felipe Silva do Prado Lima - RM 559848
- Davi Corrêa Paião - RM 560438

## Rodando o projeto

É necessário ter o Java 21 instalado.

```powershell
.\mvnw.cmd spring-boot:run
```

A API roda em `http://localhost:8085`.

## Endpoints

- `POST /pedidos`
- `GET /pedidos`
- `GET /pedidos/{id}`
- `PUT /pedidos/{id}`
- `DELETE /pedidos/{id}`

Os exemplos para testar as requisições estão no arquivo `requests.http`.
