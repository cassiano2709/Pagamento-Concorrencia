# Pagamento Concorrência API

## Visão Geral

A **Pagamento Concorrência API** é um serviço RESTful desenvolvido para gerenciar e processar pagamentos simultâneos com segurança e eficiência, garantindo integridade e confiabilidade das transações mesmo em ambientes de alta concorrência.

Esta API foi projetada para resolver problemas comuns em aplicações financeiras onde múltiplas transações precisam ser processadas ao mesmo tempo, evitando condições de corrida e inconsistências.

## Funcionalidades Principais

- **Processamento Concorrente de Pagamentos:**
  - Capacidade robusta para lidar com múltiplas requisições simultâneas sem comprometer a integridade dos dados.

- **Transações Seguras:**
  - Garante a segurança e a consistência dos dados financeiros usando mecanismos apropriados de concorrência.

- **Consulta de Status de Transações:**
  - Permite acompanhar o status e histórico das transações processadas.

- **Tratamento de Erros Eficiente:**
  - Respostas claras e informativas sobre falhas e inconsistências encontradas durante o processamento.

## Tecnologias Utilizadas

- **Java com Spring Boot**
- **Banco de Dados Relacional (Ex: PostgreSQL, MySQL)**
- **Docker (Opcional)**
- **Testes Unitários e de Integração**

## Endpoints Principais

| Método HTTP | Endpoint                  | Descrição                                     |
|-------------|---------------------------|-----------------------------------------------|
| POST        | `/api/pagamentos`         | Cria uma nova transação de pagamento.         |
| GET         | `/api/pagamentos/{id}`    | Recupera detalhes de uma transação específica. |
| GET         | `/api/pagamentos`         | Lista todas as transações efetuadas.          |
| PUT         | `/api/pagamentos/{id}`    | Atualiza detalhes de uma transação existente. |

## Como Executar

### Requisitos

- Java 21
- Maven ou Gradle
- Docker (Opcional)

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/cassiano2709/Pagamento-Concorrencia.git
```

2. Execute a aplicação usando o Maven:
```bash
mvn spring-boot:run
```

3. Acesse a API através de:
```
http://localhost:8080/api/pagamentos
```

## Documentação

A documentação completa da API está disponível via Swagger:
```
http://localhost:8080/swagger-ui.html
```

## Contribuições

Contribuições são bem-vindas! Siga estes passos:
- Faça um fork deste repositório
- Crie uma nova branch (`git checkout -b minha-nova-funcionalidade`)
- Faça commit das alterações (`git commit -am 'Adiciona nova funcionalidade'`)
- Faça push da branch (`git push origin minha-nova-funcionalidade`)
- Abra um Pull Request

---

**Pagamento Concorrência API - Garantindo segurança e eficiência em transações simultâneas.**
