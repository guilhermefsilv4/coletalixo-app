# language: pt

@auth
Funcionalidade: Autenticação de usuários no sistema
  Como um usuário do sistema de coleta de lixo
  Eu quero me autenticar na plataforma
  Para que eu possa gerenciar meus agendamentos de coleta

  Contexto:
    Dado que o sistema possui um endpoint de autenticação disponível

  @login
  Cenário: Login bem-sucedido com credenciais válidas
    Quando eu enviar uma requisição POST para "/auth/login" com os dados:
      | email    | usuario@teste.com |
      | senha    | senha123          |
    Então o status da resposta deve ser 200
    E o corpo da resposta deve conter um token JWT válido

  @login
  Cenário: Falha de login com credenciais inválidas
    Quando eu enviar uma requisição POST para "/auth/login" com os dados:
      | email    | usuario@teste.com |
      | senha    | senhaErrada       |
    Então o status da resposta deve ser 401
    E o corpo da resposta deve conter uma mensagem de erro apropriada

  @registro
  Cenário: Registro bem-sucedido de novo usuário
    Quando eu enviar uma requisição POST para "/auth/register" com os dados:
      | nome     | Novo Usuário      |
      | email    | novo@teste.com    |
      | senha    | senha123          |
    Então o status da resposta deve ser 201
    E o corpo da resposta deve conter os dados do usuário cadastrado
    E o corpo da resposta não deve conter a senha do usuário

  @registro
  Cenário: Falha no registro com email já existente
    Dado que existe um usuário cadastrado com o email "existente@teste.com"
    Quando eu enviar uma requisição POST para "/auth/register" com os dados:
      | nome     | Usuário Duplicado |
      | email    | existente@teste.com |
      | senha    | senha123          |
    Então o status da resposta deve ser 409
    E o corpo da resposta deve conter uma mensagem indicando que o usuário já existe 