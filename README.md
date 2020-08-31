# portal-fazenda-backend

Este projeto foi gerado com [Spring Initializr](https://start.spring.io). Spring Boot versão 2.3.3 e Java 14.
Para a criação das tabelas utilizado o flyway.

## Development server

Execute o Spring Boot para o servidor de desenvovimento. O servidor ficara disponível na portal 8080 `http://127.0.0.1:8080/portal/`.

## Finality

Buscar os status dos serviços de nota scal eletrônica por estado `http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx` 
a cada 5 minutos gravando em um banco de dados h2.

## Endpoint

* Os status atual dos serviços por estado. 
`http://127.0.0.1:8080/portal/api/servico/status`
* O status atual do serviço filtrando por estado. 
`http://127.0.0.1:8080/portal/api/servico/status/autorizador/{id}`
* Os status dos serviços por estado filtrando por data. 
`http://127.0.0.1:8080/portal/api/servico/status/periodo?dhInicio=2020-08-30T00:00:00&dhFim=2020-08-30T23:59:59&page=0&size=20`
* Estado com mais indisponibilidade de serviço. 
`http://127.0.0.1:8080/portal/api/servico/status/indisponibilidade`
