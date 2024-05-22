# Projeto de Processos - Spring Boot

Este projeto é uma aplicação Spring Boot para gerenciar processos, incluindo o upload de documentos PDF associados a cada processo. 

## Funcionalidades

- Cadastrar processos com os seguintes campos:
  - Número do Processo Único (NPU)
  - Data de Cadastro
  - Município
  - UF
  - Upload de documento PDF
- Listar processos com paginação
- Visualizar detalhes de um processo
- Marcar um processo como visualizado
- Excluir um processo

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Maven

## Configuração do Projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.6 ou superior
- Postman (para testes de API, dentro do projeto tem uma pasta chamda "Collections" onde está as collections criadas durante a criação da API.)
- Git (para controle de versão)

### Clonar o Repositório

```bash
git clone https://github.com/otaviooasc/fsbr-desafio
