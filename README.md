# Sistema de Associação

API REST para gerenciamento de associados.

## Tecnologias
- Java 21
- Spring Boot 4.0.2
- H2 Database
- Lombok

## Endpoints
- POST /api/v1/associados - Criar associado
- GET /api/v1/associados - Listar todos
- GET /api/v1/associados/{id} - Buscar por ID
- GET /api/v1/associados/cpf/{cpf} - Buscar por CPF
- PUT /api/v1/associados/{id} - Editar Associado por Id
- DELETE /api/v1/associados/{id} - Deletar

## Como executar
```bash
./mvnw spring-boot:run
