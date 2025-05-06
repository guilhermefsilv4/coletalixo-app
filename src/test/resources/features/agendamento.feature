# language: pt

@agendamento
Funcionalidade: Gerenciamento de agendamentos de coleta
  Como um usuário do sistema de coleta de lixo
  Eu quero gerenciar meus agendamentos de coleta
  Para que eu possa programar a retirada de resíduos em minha localização

  Contexto:
    Dado que eu estou autenticado como um usuário comum
    E tenho acesso aos endpoints de agendamento

  @criacao
  Cenário: Criar um agendamento de coleta com sucesso
    Quando eu enviar uma requisição POST para "/agendamento" com os dados:
      | dataAgendamento | 2023-12-30      |
      | localizacao     | Rua Exemplo, 123 |
    Então o status da resposta deve ser 201
    E o corpo da resposta deve conter os dados do agendamento criado
    E o status do agendamento deve ser "PENDENTE"

  @consulta
  Cenário: Consultar meus agendamentos
    Dado que eu possuo um agendamento cadastrado
    Quando eu enviar uma requisição GET para "/agendamento/meus-agendamentos"
    Então o status da resposta deve ser 200
    E o corpo da resposta deve conter uma lista de agendamentos
    E a lista deve incluir meu agendamento cadastrado

  @atualizacao
  Cenário: Atualizar a localização de um agendamento pendente
    Dado que eu possuo um agendamento pendente com ID "agendamento123"
    Quando eu enviar uma requisição PUT para "/agendamento" com os dados:
      | id              | agendamento123   |
      | dataAgendamento | 2023-12-30       |
      | localizacao     | Nova Rua, 456    |
    Então o status da resposta deve ser 200
    E a localização do agendamento deve ser atualizada para "Nova Rua, 456"

  @cancelamento
  Cenário: Cancelar um agendamento pendente
    Dado que eu possuo um agendamento pendente com ID "agendamento123"
    Quando eu enviar uma requisição PUT para "/agendamento/cancelar/agendamento123"
    Então o status da resposta deve ser 200
    E o status do agendamento deve ser atualizado para "CANCELADO"

  @regras-negocio
  Cenário: Não permitir atualização de um agendamento concluído
    Dado que eu possuo um agendamento com status "CONCLUIDO" e ID "agendamento123"
    Quando eu enviar uma requisição PUT para "/agendamento" com os dados:
      | id              | agendamento123   |
      | dataAgendamento | 2023-12-30       |
      | localizacao     | Nova Rua, 456    |
    Então o status da resposta deve ser 409
    E o corpo da resposta deve conter uma mensagem indicando que não é possível alterar um agendamento concluído 