# customer-management
O exercício consiste no desenvolvimento de um protótipo de um sistema de gestão online de dados de clientes baseado nas tecnologias utilizadas na **MULTICERT**.

# Execução da Aplicação
Executar o seguinte comando na raíz do projeto.
```
$ mvn wildfly:run
```

# Especificação da API

## Adicionar um novo cliente

### Pedido
`POST /customers`
```
$ curl -i -H "Content-Type: application/json" -d @add-customer.json http://localhost:8080/CustomerManagement/customers
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Content-Type: text/plain;charset=ISO-8859-1
Content-Length: 38
Date: Mon, 11 May 2020 14:35:54 GMT

New Customer has been saved with ID: 1
```

## Apagar um cliente da base de dados

### Pedido
`DELETE /customers/id`
```
$ curl -i -H "Accept: application/json" -X DELETE http://localhost:8080/CustomerManagement/customers/1
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Content-Type: application/json
Content-Length: 34
Date: Mon, 11 May 2020 02:57:12 GMT

Book has been deleted successfully
```

## Listar clientes

### Pedido
`GET /customers`
```
$ curl -i -H "Accept: application/json" http://localhost:8080/CustomerManagement/customers
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json
Date: Mon, 11 May 2020 14:36:29 GMT

[{"id":1,"name":"Ruben","phoneNumber":"+351917070707","nif":11111110,"addresses":[{"id":1,"address":"Rua Principal N┬║1","zipCode":"2300-001","city":"Cidade1"},{"id":2,"address":"Rua Principal N┬║2","zipCode":"2300-002","city":"Cidade2"}]}]
```

## Obter a informação de um cliente a partir do NIF

### Pedido
`GET /customers?nif=`
```
$ curl -i -H "Accept: application/json" http://localhost:8080/CustomerManagement/customers?nif=11111110
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json
Date: Mon, 11 May 2020 14:38:37 GMT

{"id":1,"name":"Ruben","phoneNumber":"+351917070707","nif":11111110,"addresses":[{"id":1,"address":"Rua Principal N┬║1","zipCode":"2300-001","city":"Cidade1"},{"id":2,"address":"Rua Principal N┬║2","zipCode":"2300-002","city":"Cidade2"}]}
```
## Obter todos os clientes com um dado nome

### Pedido
`GET /customers?name=`
```
$ curl -i -H "Accept: application/json" http://localhost:8080/CustomerManagement/customers?name=Ruben
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json
Date: Mon, 11 May 2020 14:40:28 GMT

[{"id":1,"name":"Ruben","phoneNumber":"+351917070707","nif":11111110,"addresses":[{"id":1,"address":"Rua Principal N┬║1","zipCode":"2300-001","city":"Cidade1"},{"id":2,"address":"Rua Principal N┬║2","zipCode":"2300-002","city":"Cidade2"}]}]
```
## Obter todos os clientes de uma dada localidade

### Pedido
`GET /customers?city=`
```
$ curl -i -H "Accept: application/json" http://localhost:8080/CustomerManagement/customers?city=Cidade1
```
### Resposta
```
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json
Date: Mon, 11 May 2020 14:44:27 GMT

[{"id":1,"name":"Ruben","phoneNumber":"+351917070707","nif":11111110,"addresses":[{"id":1,"address":"Rua Principal N┬║1","zipCode":"2300-001","city":"Cidade1"}]}]
```
