#language:pt

Funcionalidade: criar uma simulação de proposta de crédito

  Contexto:
    Dado que eu tenha acesso à API de criação de simulações

  #Criação de simulação com sucesso
  Cenario: Criar uma simulação informando dados válidos e validar o sucesso da solicitação
    E tenha um payload com dados válidos para utilizar na criação
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 201
    Então o response é validado de acordo com o esperado

  #Criação de simulação com CPF que já tenha sido cadastrado
  Cenario: Criar uma simulação informando um CPF que ja tenha sido utilizado anteriormente
    E informe no payload um CPF que ja tenha uma simulação cadastrada para o mesmo
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem "CPF duplicado" é retornada

  #Não permitir criação com valor superior a 40.000
  Cenario: Criar uma simulação onde o valor da mesma seja maior que o permitido pela regra
    E informe um payload que o valor da simulação seja superior ao permitido
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem "Valor deve ser menor ou igual a R$ 40.000" é retornada

  #Não permitir criacao com valor inferior a 1.000
  Cenario: Criar uma simulação onde o valor da mesma seja inferior que o permitido pela regra
    E informe um payload que o valor da simulação seja inferior ao permitido
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem "Valor deve ser maior ou igual a R$ 1.000" é retornada


