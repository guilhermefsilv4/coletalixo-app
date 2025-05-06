
# Coleta Lixo

**Descrição**: Este projeto é uma aplicação Java que utiliza o framework Spring Boot para gerenciar e realizar a coleta de lixo.

## 📋 Pré-requisitos

Antes de iniciar, certifique-se de que você tem os seguintes requisitos instalados:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/) (opcional, se você decidir utilizá-lo)

## 📁 Estrutura do Projeto

/coletalixo-app<br>
├── .gitignore<br>
├── Dockerfile<br>
├── docker-compose.yml<br>
└── src/            # Código-fonte da aplicação

## 🚀 Como Inicializar e Executar o Projeto

### Usando o Dockerfile

1. **Clone o repositório**

   ```bash
   git clone https://github.com/guilhermefsilv4/coletalixo-app.git
   cd coletalixo-app

2. **Construa a imagem**

   #### No diretório onde o Dockerfile está localizado, execute:

    ```bash
    docker build -t nome-da-imagem .
    ```
   Inicie o contêiner

3. **Após a construção, inicie o contêiner com:**

    ```bash
    docker run -p 8080:8080 nome-da-imagem
    ```
   Acesse a aplicação

   #### Após iniciar o contêiner, a aplicação estará disponível em http://localhost:8080.


### Usando Docker Compose


1. **Clone o repositório (se ainda não fez)**

    ```bash
    git clone https://github.com/guilhermefsilv4/coletalixo-app.git
    cd coletalixo-app
    ```
   Inicie a aplicação com Docker Compose

   **Para construir a imagem e iniciar a aplicação, execute:**
    ```bash
    docker-compose up --build
    ```
   Acesse a aplicação
   #### Após iniciar o contêiner, a aplicação estará disponível em http://localhost:8080.

## ⚙️ Configurações Adicionais

### A seguinte variável de ambiente é definida:

SPRING_PROFILES_ACTIVE=prod para a configuração de produção.

## ⛔ Parar os Contêineres
### Para parar os contêineres em execução, você pode usar:

```bash
docker-compose down
```

**Se você usou o comando docker run, pode parar o contêiner usando:**

  ```bash
  docker stop <container_id>
  ```

**Para Listar Contêineres**

  ```bash
  docker ps -a
  ```

## 🧪 Como Rodar os Testes

Certifique-se de que o projeto está em execução antes de rodar os testes. Siga os passos abaixo:

### 1. Inicie o projeto

Certifique-se de que a aplicação está rodando. Você pode iniciar o projeto utilizando o Docker ou o Docker Compose, ou até abrindo a ides como intellij e apertando na setinha verde, conforme descrito na seção **🚀 Como Inicializar e Executar o Projeto**.

### 2. Execute os testes

No diretório raiz do projeto, utilize o seguinte comando para rodar os testes:

```bash
mvn test -Dspring.profiles.active=dev
