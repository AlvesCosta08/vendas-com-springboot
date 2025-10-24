# Loja Digital – Backend (Spring Boot)

Backend de um sistema de e-commerce desenvolvido com **Spring Boot 3**, **JPA**, **JWT** e boas práticas de segurança e arquitetura.

## 🚀 Funcionalidades
- Cadastro e gerenciamento de **categorias** e **produtos**
- Autenticação via **JWT**
- Validação de dados
- Testes unitários com JUnit e Mockito

## 🛠 Tecnologias
- Java 17+
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- H2 / PostgreSQL (configurável)
- Lombok (opcional)
- Maven

## ⚙️ Como rodar
1. Clone o repositório  
2. Execute: `./mvnw spring-boot:run`  
3. A API estará em: `http://localhost:8080`

> O frontend espera esta API rodando na porta 8080 (veja `proxy.conf.json` no frontend).

## 🔒 Endpoints
- `POST /api/auth/login`
- `GET /api/categorias`
- `GET /api/produtos`
- `POST /api/produtos` (admin)

## 🧪 Testes
```bash
./mvnw test

