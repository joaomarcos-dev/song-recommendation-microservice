# Avaliação Java - Camada de serviço para pseudo loja de games

## Descrição

  Esse projeto é o backend de uma aplicação para de recomendação de playlists. A recomendação é feita baseando-se no clima da cidade informada como valor de entrada. Foi escrito utilizando a linguagem Java com Spring Boot.

## Como executar os testes do build

  O projeto utiliza o Maven Wrapper, o que quer dizer que não será necessário a instalação do Maven na máquina, uma vez que a ferramenta é instalado em um diretório local ao projeto. Utilize o comando `mvnw clean test` para a execução da fase de testes.

## Como executar os testes de produção

Para a execução de testes nos endpoints, poderá ser utilizada a ferramenta Postman, Curl ou o próprio navegador.
Após rodar o comando `mvnw clean package`, execute `java -jar target/suggestion-microservice.jar` da pasta `target` que será gerada. Após a inicialização da aplicação, o endpoint `/playlist` já estará disponíveis para responder às requisições.

## Endpoints
Por meio dos endpoints descritos abaixo, é possível interagir com as resouces. Não foram implementadas camadas de authenticação, como Bearer tokens.

# http://localhost:8084/playlist/

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | retorna um objeto playlist

| Parâmetros    | Descrição     
|:-------------:|:-------------
| cityName      | `Required` Retorna um objeto playlist para a cidade informada de acordo com a temperatura
| orderBy       | Ordena a lista de músicas da playlist. Aceita os valores [ songName, artistName]
<!-- | decresc       | Altera a ordenação de crescente para decrescente -->
<!-- | limit         | retorna um um objeto playlist
| offset        | retorna um um objeto playlist -->

# Response Codes

| Status Code   | Descrição     
|:-------------:|:-------------
| 200           | Cidade informada foi encontrada e playlist retornada com sucesso.
| 404           | Não foi possível encontrar a cidade requisitada.
| 400           | Houve erro ao tentar processar a requisição, verificar se nome dos parâmetros foram digitados corretamente
| 500           | retorna um um objeto playlist

# Object Reference

| Playlist      | Descrição     
|:-------------:|:-------------
| playlist      | retorna objeto com lista de todas as músicas relacionadas ao clima da cidade requisitada

## Requisitos testes

Java 8 instalado na máquina.

Para que o log de requisições funcione corretamente, o banco de dados `appmusic` deve ter as tabelas do script [DBAppender_Postgres.sql](./appmusic-backend-microservices/playlist-service/src/main/resources/logback/DBAppender_Postgres.sql) criadas. Além disso, informações de acesso ao banco devem ser supridas no arquivo [logback-spring.xml](./playlist-service/src/main/resources/logback-spring.xml).

## Notas sobre o projeto

Como todo cógido, o projeto pode ser melhorado para atender requisitos adicionais como mais enpoints, segurança, escalabilidade.

Seria interessante adicionar mecanismos de cache ao fazer requisições para OpenWeather API, uma vez que a documentação orienta a não realizar requisições para a mesma localidade com menos de 10 min de intervalo.

Para manter o projeto simples, o mecanismo de log das requisições é feito utilizando o framework de logging Logback, mais especificamente com `DBappender`. De modo asyncrônico, as requições são salvas na tabela `logging_event`, na coluna `formatted_message`, no formato `<método> <resource-path> <query-string> <json-payload>`, no banco de dados `appmusic`. Em um ambiente de produção, os logs conteriam o ID do client e poderia ser implementados com JPA/SpringData/Hibernate.

A aplicação frontend `appmusic-frontend` está localizada nesse mesmo repositório e foi desenvolvida em outro branch para manter a organização do histórico do repositório.
