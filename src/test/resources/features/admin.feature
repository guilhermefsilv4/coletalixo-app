# language: pt

@admin
Funcionalidade: Gerenciamento administrativo do sistema
  Como um administrador do sistema de coleta de lixo
  Eu quero gerenciar todos os agendamentos
  Para que eu possa monitorar e controlar as operações de coleta

  Contexto:
    Dado que eu estou autenticado como administrador
    E tenho acesso aos endpoints administrativos

  @listagem-admin
  Cenário: Listar todos os agendamentos do sistema
    Quando eu enviar uma requisição GET para "/agendamento"
    Então o status da resposta deve ser 200
    E o corpo da resposta deve conter uma lista com todos os agendamentos
    E a lista deve incluir agendamentos de diferentes usuários

  @detalhe-admin
  Cenário: Consultar detalhes de um agendamento específico
    Dado que existe um agendamento com ID "agendamento123" no sistema
    Quando eu enviar uma requisição GET para "/agendamento/agendamento123"
    Então o status da resposta deve ser 200
    E o corpo da resposta deve conter os detalhes completos do agendamento

  @remocao-admin
  Cenário: Remover um agendamento do sistema
    Dado que existe um agendamento com ID "agendamento123" no sistema
    Quando eu enviar uma requisição DELETE para "/agendamento/agendamento123"
    Então o status da resposta deve ser 204
    E o agendamento deve ser removido do sistema

  @restricao-acesso
  Cenário: Usuário comum não pode acessar listagem completa
    Dado que eu estou autenticado como um usuário comum
    Quando eu enviar uma requisição GET para "/agendamento"
    Então o status da resposta deve ser 403
    E o corpo da resposta deve conter uma mensagem indicando acesso não autorizado 