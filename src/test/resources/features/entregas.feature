# language: pt

@entregas
Funcionalidade: Gerenciamento de cadastro de entregas de resíduos
  Como um usuário do sistema de coleta de lixo
  Eu quero gerenciar minhas entregas de resíduos
  Para que eu possa registrar e acompanhar a coleta de materiais recicláveis

  Contexto:
    Dado que eu estou autenticado como um usuário do sistema
    E tenho acesso aos endpoints de cadastro de entregas

  @criacao
  Cenário: Criar uma entrega de resíduos com sucesso
    Quando eu enviar uma requisição POST para o endpoint "/entregas" com os dados:
      | dataEntrega     | 2023-12-30       |
      | enderecoColeta  | Rua Exemplo, 123 |
      | tipoResiduo     | RECICLAVEL       |
      | quantidade      | 5kg              |
    Então o status da resposta deve ser Entrega 201
    E o corpo da resposta deve conter os dados da entrega criada
    E o status da entrega deve ser "PENDENTE"

  @consulta
  Cenário: Consultar minhas entregas
    Dado que eu possuo uma entrega cadastrada
    Quando eu enviar uma requisição GET para o endpoint "/entregas/minhas-entregas"
    Então o status da resposta deve ser Entrega 200
    E o corpo da resposta deve conter uma lista de entregas
    E a lista deve incluir minha entrega cadastrada

  @atualizacao
  Cenário: Atualizar o endereço de coleta de uma entrega pendente
    Dado que eu possuo uma entrega com status "PENDENTE" e ID "entrega123"
    Quando eu enviar uma requisição PUT para o endpoint "/entregas/entrega123" com os dados:
      | enderecoColeta  | Nova Rua, 456    |
      | tipoResiduo     | RECICLAVEL       |
      | quantidade      | 5kg              |
    Então o status da resposta deve ser Entrega 200
    E o endereço de coleta da entrega deve ser atualizado para "Nova Rua, 456"

  @cancelamento
  Cenário: Cancelar uma entrega pendente
    Dado que eu possuo uma entrega com status "PENDENTE" e ID "entrega123"
    Quando eu enviar uma requisição PUT para o endpoint "/entregas/entrega123/cancelar"
    Então o status da resposta deve ser Entrega 200
    E o status da entrega deve ser atualizado para "CANCELADO"

  @exclusao
  Cenário: Excluir uma entrega pendente
    Dado que eu possuo uma entrega com status "PENDENTE" e ID "entrega123"
    Quando eu enviar uma requisição DELETE para o endpoint "/entregas/entrega123"
    Então o status da resposta deve ser Entrega 204
    E a entrega deve ser removida do sistema

  @regras-negocio
  Cenário: Não permitir atualização de uma entrega concluída
    Dado que eu possuo uma entrega com status "CONCLUIDO" e ID "entrega123"
    Quando eu enviar uma requisição PUT para o endpoint "/entregas/entrega123" com os dados:
      | enderecoColeta  | Nova Rua, 456    |
      | tipoResiduo     | RECICLAVEL       |
      | quantidade      | 5kg              |
    Então o status da resposta deve ser Entrega 409
    E o corpo da resposta deve conter uma mensagem indicando que não é possível alterar uma entrega concluída 