# SpringNoSql com RabbitMQ

Este repositório contém um serviço em **Java com Spring Boot** com o objetivo de ser uma API que realiza um CRUD e, ao cadastrar, deve publicar mensagens em uma fila no **RabbitMQ**. Este projeto é parte de uma prática sobre a utilização de um banco NoSQL com Spring Boot e sistemas de mensageria, e funciona em conjunto com o serviço Email-Service (Golang), cujo repositório pode ser encontrado na mesma organização no GitHub

## Finalidade
- Implementar uma API **Java** com **Spring Boot** persistindo em um banco **MongoDB**;
- Praticar conceitos de teste unitário utilizando **JUnit**;
- Praticar conceitos de **mensageria** usando **RabbitMQ**;
---

## Funcionalidades
- CRUD básico persistindo em um mongoDB;
- Conexão com uma fila RabbitMQ para publicar mensagens em uma fila;
- Configuração via variáveis de ambiente.

---

## Tecnologias Utilizadas
- **Spring Boot** (API Rest)
- **RabbitMQ** (mensageria)
- **MongoDB** (persistência)
- **JUnit** (Testes unitários)

---

## Arquivo de Configuração (`application.yml`)
Para configurar o serviço, edite o arquivo `src/main/resources/application.yml` com as seguintes configurações:

```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      host: localhost
      database: spring_test

rabbitmq:
  queue:
    person_notification: person_notifications
  routing:
    key:
      person: person_notifications
  exchange:
    default: ""

logging:
  level:
    org.springframework.amqp: DEBUG
```

---

## Instalação e Execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/RafaelFernando12/SpringNoSql.git
   cd SpringNoSql
   ```

2. **Configure as variáveis de ambiente**:
   Crie um arquivo `application.yml` ou exporte as variáveis no terminal conforme indicado acima.

3. **Testando**:
   - Execute o comando `make env up` para subir os serviços do rabbitMQ e MongoDB.
   - Cadastre uma Pessoa no endereço `localhost:8080/api/person` enviando no body um objeto com os atributos `name, email e age`.
   - Verifique se a pessoa cadastra foi publicada em uma mensagem na fila `person_notifications`.
   - Verifique se o email foi enviado através do serviço `email-service`.

---

## Estrutura do Projeto
```
├── src/main/java
│   ├── com.example.springnosql
│       ├── controller       # Controladores REST
│       ├── service          # Lógica de negócio
│       ├── repository       # Repositórios do MongoDB
│       ├── config           # Configurações do RabbitMQ
│       └── model            # Modelos de dados (Entidades)
├── src/test/java            # Testes unitários
├── Dockerfile               # Configuração do build para gerar uma imagem docker
├── docker-compose.yml       # Configuração de dependências (RabbitMQ, MongoDB) para execução local
├── docker-compose-app.yml   # Configuração de dependências (Imagem Final, MongoDB) para subir um container com api executando
├── pom.xml                  # Gerenciamento de dependências Maven
└── Makefile                 # Automação de tarefas
```

---

## Requisitos
- **Java** 17 ou superior;
- **Maven** instalado;
- **RabbitMQ** configurado e em execução;
- Docker(opcional, para execução dos containers).

---

## Observações
- A API utiliza o MongoDB como banco de dados NoSQL.
- O RabbitMQ é necessário para a funcionalidade de mensageria.

---

## Serviço Relacionado
- [**email-service**](https://github.com/RafaelFernando12/email-service) - Serviço em Golang que complementa este projeto.
---

