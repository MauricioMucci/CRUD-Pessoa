## 📋 Descrição do Projeto

O projeto consiste em duas aplicações:

- **Frontend**: Interface de usuário para interação com o sistema
- **Backend**: Aplicação Java responsável pela lógica de negócio e pela comunicação com a API dos correios para
  validação de cep

## 💻 Tecnologias

- Java 21
- Apache Maven
- PostgreSQL
- Spring Framework
- Docker
- React

## 🚀 Funcionalidades Implementadas

- **CRUD de Pessoa**:
  - Criação de pessoa com validação de dados
  - Consulta de pessoa por CPF
  - Listagem de todas as pessoas
  - Exclusão de pessoa

- **Integração com API Externa**:
  - Consulta de endereços via API ViaCep (Correios)
  - Validação e formatação de CEP

- **Persistência de Dados**:
  - Salvamento de pessoas e endereços no banco de dados PostgreSQL

## ⚠️ Requisitos Não Implementados

- **Integração com Filas**:
  - Envio da pessoa para uma fila interna (JMS/RabbitMQ)

## 🛠️ Configuração e Execução

### Pré-requisitos
- Java 21
- Docker e Docker Compose
- Maven

### Passos para Execução

1. **Clone o repositório**:
   ```
   git clone <url-do-repositorio>
   ```

2. **Inicie os serviços com Docker Compose**:
   ```
   cd backend
   docker-compose up -d
   ```

   Isso iniciará:
  - PostgreSQL na porta 5432
  - RabbitMQ na porta 15672

3. **Execute o backend**:
   ```
   cd backend
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

4. **Execute o frontend**:
   ```
   cd frontend
   npm install
   npm run dev
   ```
   
Acesse o web app em: http://localhost:5173/

## 📝 Observações

- O banco de dados está configurado com as seguintes credenciais:
  - URL: jdbc:postgresql://localhost:5432/senai
  - Usuário: root
  - Senha: root

- A API ViaCep é utilizada para consulta de endereços a partir do CEP

## 📊 Estrutura do Projeto

O backend segue uma arquitetura em camadas:
- **Presentation**: Controllers e DTOs
- **Application**: Serviços e lógica de negócio
- **Domain**: Entidades e repositórios
- **Infrastructure**: Componentes, configurações e utilitários
