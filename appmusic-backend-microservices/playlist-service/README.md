# Avaliação Java - Camada de serviço para pseudo loja de games

## Descrição

  Esse projeto é o backend de uma aplicação para de recomendação de playlists. A recomendação é feita baseando-se no clima da cidade informada como valor de entrada. Foi escrito utilizando a linguagem Java com Spring Boot.

## Como executar os testes

  O projeto utiliza o Maven Wrapper, o que quer dizer que não será necessário a instalação do Maven na máquina, uma vez que a ferramenta é instalado em um diretório local ao projeto. Utilize o comando `mvnw clean test` para a execução da fase de testes.

## Como executar os testes de produção

	Para a execução de testes nos endpoints, poderá ser utilizada a ferramenta Postman, Curl ou o próprio navegador.
	Após rodar o comando `mvnw clean package`, execute `java -jar XXXXX.jar` da pasta "target" que será gerada. Após a inicialização da aplicação, o endpoint `/playlist` já estará disponíveis para requests.

## Endpoints
Por meio dos endpoints descritos abaixo, é possível interagir com as resouces. Não foram implementadas camadas de authenticação, como Bearer tokens.

### http://localhost:8084/playlist/

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | retorna um objeto playlist

| Parâmetros    | Descrição     
|:-------------:|:-------------
| cityName      | Retorna um um objeto playlist para a cidade informada de acordo com a temperatura
| orderBy       | Ordena a lista de músicas da playlist. Aceita os valores [ songName, artistName]
| decresc       | Altera a ordenação de crescente para decrescente
| limit         | retorna um um objeto playlist
| offset        | retorna um um objeto playlist

## Response Codes

| Status Code   | Descrição     
|:-------------:|:-------------
| 200           | Cidade informada foi encontrada e retornada com sucesso.
| 404           | Não foi possível encontrar a cidade requisitada.
| 400           | Altera a ordenação de crescente para decrescente
| 5000          | retorna um um objeto playlist

### Object Reference

| Playlist      | Descrição     
|:-------------:|:-------------
| Genre          | aceita objeto JSON no body do request e adiciona ao carrinho do usuário
| songList      | retorna o carrinho atualizado

## Requisitos testes
Java 8 instalado na máquina

## Sobre a entrega
Como todo cógido, o projeto pode ser melhorado para atender requisitos adicionais como mais enpoints, segurança, escalabilidade. Algumas partes do código não estão bem comentadas, novamente, devido ao prazo de entrega.
