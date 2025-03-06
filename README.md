# Desafio - Construção de APP para gerenciamento de usuários

## Objetivo
Criar uma API RESTful com Spring Boot que implemente as seguintes operações: 

* Criar um novo usuário.
* Buscar todos os usuários.
* Buscar um usuário por ID.
* Atualizar as informações de um usuário existente.
* Excluir um usuário.
* Conectar a aplicação a um banco de dados relacional de sua preferência (ex.: MySQL, PostgreSQL, H2, etc.). 
* Implementar validações para os dados recebidos na API.
* Configurar o projeto para ser executado e testado localmente, com instruções no README.md.
* Publicar o projeto em um repositório público no GitHub.

## Introdução
Todas as nossas APIs foram construídas utilizando o padrão RESTful, o qual é adotado largamente no mercado para construção de modernas interfaces de integração. 
Nos próximos capítulos você terá a descrição de todos os endpoints, recursos REST de uma maneira que você possa consumi-las através do protocolo HTTP enviando e recebendo paylods no formato [JSON](http://www.json.org/).
Nesse documento você vai encontrar todas as informações necessárias para realizar a execução localmente.

**Api Gateway**

Como está sendo considerado apenas o ambeinte local, a URL será única.

| Ambiente    | URL´s                   |
|-------------|-------------------------|
| Stage Local | <https://locahost:8080> |

**Executando o APP**

O aplicativo foi desenvolvido com a tecnologia de 'Container' para facilitar seu execução. Dessa forma, existem dois arquivos chaves para que a rotina funcione:
*Dockerfile*, *docker-compose.yml*.

**Passos:**
1. Clonar o repositório do projeto no Github:
   *Entrar em um diretório de sua preferência, abrir o Git Bash e digitar o comando abaixo.*
      ```bash
        git clone https://github.com/thiagobrasil10/desafio.git   
      ```
2. Iniciar o Docker em seu equipamento e executar os comandos:
      ```bash
           docker-compose down -v
      ```
      ```bash
           docker-compose up --build
      ```

**Testes**

Ao executar os passos acima, os teste irão iniciar antes do build e poderão ser acompanhados pelo CONSOLE.

**Swagger**

Após a aplicação executar por completo, será possível acessar a especificação através da URL **<http://localhost:8080/swagger-ui/index.html>**.

### HTTP Headers

Os seguintes parâmetros de headers são necessários em cada requisição REST:

_**Content-Type: application-json**_

### HTTP Status Code

Os seguintes HTTP Status Code são usados por nossas APIs:

| Atributo | Descrição                                                                                                                                                              |
| -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 200      | Operação realizada com sucesso                                                                                                                                         |
| 201      | Criado. Quando o recurso é criado com sucesso.                                                                                                                         |
| 204      | Sem conteúdo. Operação realizada com sucesso, mas nenhuma resposta foi retornada.                                                                                      |
| 400      | Requisição inválida.                                                                                                                                                   |
| 401      | Não autorizado                                                                                                                                                         |
| 404      | Não encontrado - O recurso requisitado não foi encontrado, mas pode ser disponibilizado novamente no futuro. As solicitações subsequentes pelo cliente são permitidas. |
| 422      | Entidade não processável - quando dados mandatórios não foram enviados.                                                                                                |
| 500      | Erro interno do servidor.  

### Data/Hora

Todos os valores de datas e horas estão no formato ISO 8601 YYYY-MM-DD.

Ex.: 2025-03-06.

## Funcionalidades

Abaixo encontram-se as funcionalidades da API:

### Listar Usuários
Retorna a lista de todos os usuários cadastrados.

- Método HTTP: GET
- Endpoint: /api/users

#### Respota da requisição
- Status: 200 - OK

   - Body:

     ```json
     [
        {
        "id": 9007199254740991,
        "fullName": "string",
        "email": "string",
        "phone": "+95 53 52179-0204",
        "birthDate": "2025-03-06",
        "userType": "ADMIN"
        }
      ]
     ```

### Criar um Usuário
Cria um novo usuário na base de dados.

- Método HTTP: POST
- Endpoint: /api/user

#### Body - Corpo da requisição

   ```json
        {
           "id": 9007199254740991,
           "fullName": "string",
           "email": "string",
           "phone": "+2 63 76565-9340",
           "birthDate": "2025-03-06",
           "userType": "ADMIN"
        }
   ```


#### Respota da requisição
- Status: 200 - OK

   - Body:

     ```json
     {
        "id": 9007199254740991,
        "fullName": "string",
        "email": "string",
        "phone": "+9 63 69542-9426",
        "birthDate": "2025-03-06",
        "userType": "ADMIN"
      }
     ```

- Status: 400 

   - Body:

     ```json
     {
        "email": "Campo 'E-mail' deverá ser válido."
     }
     ```

     ```json
     {
        "error": "Campo 'Tipo Usuário' inválido. Opções possíveis: ADMIN, EDITOR, VIEWER"
     }
     ```

### Excluir um Usuário
Remove um usuário do sistema pelo ID.

- Método HTTP: DELETE
- Endpoint: /api/user/{id}

#### Parametros

   ```json   
    "id" = 9007199254740991   
   ```

#### Respota da requisição
- Status: 200 - OK

- Status: 400

   - Body:

     ```json
     {
        "error": "Usuário não encontrado."
     }
     ```

### Atualizar um Usuário
Atualiza um usuário do sistema pelo ID e retorna com as novas informações.

- Método HTTP: PUT
- Endpoint: /api/user/{id}

#### Body - Corpo da requisição

   ```json   
    "id" = 9007199254740991   
   ```

#### Respota da requisição
- Status: 200 - OK

   - Body:

     ```json
     {
        "id": 9007199254740991,
        "fullName": "string",
        "email": "string",
        "phone": "+610 35 12847-2277",
        "birthDate": "2025-03-06",
        "userType": "ADMIN"
     }
     ```

- Status: 400

   - Body:

     ```json
     {
        "error": "Usuário não encontrado."
     }
     ```

     ```json
     {
        "error": "E-mail já registrado em outro usuário."
     }
     ```

### Buscar Usuário por ID
Retorna um usuário específico pelo seu ID.

- Método HTTP: GET
- Endpoint: /api/user/{id}

#### Body - Corpo da requisição

   ```json   
    "id" = 9007199254740991   
   ```

#### Respota da requisição
- Status: 200 - OK

   - Body:

     ```json
     [
        {
        "id": 9007199254740991,
        "fullName": "string",
        "email": "string",
        "phone": "+95 53 52179-0204",
        "birthDate": "2025-03-06",
        "userType": "ADMIN"
        }
      ]
     ```

- Status: 400

   - Body:

     ```json
     {
        "error": "Usuário não encontrado com ID: 99"
     }
     ```

