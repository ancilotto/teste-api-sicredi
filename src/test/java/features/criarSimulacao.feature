#language:pt
@criarSimulacaoGeral
Funcionalidade: criar uma simulação de proposta de crédito

  Contexto:
    Dado que eu tenha acesso à API de criação de simulações

  @criarSimulação
  Cenario: Criar uma simulação informando dados válidos e validar o sucesso da solicitação
    E tenha um payload com dados válidos para utilizar na criação
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 201
    Então o response é validado de acordo com o esperado

  @criarSimulaçãoSemCampoNome
  Cenario: Criar uma simulação não informando o campo nome que é obrigatorio
    E informe um payload em que o nome nao seja informado
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de erro para nome "Nome não pode ser vazio" é retornada

  @criarSimulaçãoCpfDuplicado
  Cenario: Criar uma simulação informando um CPF que ja tenha sido utilizado anteriormente
    E informe no payload um CPF que ja tenha uma simulação cadastrada para o mesmo
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de cpf duplicado "CPF duplicado" é retornada

  @criarSimulaçãoValorMaior
  Cenario: Criar uma simulação onde o valor da mesma seja maior que o permitido pela regra
    E informe um payload que o valor da simulação seja superior ao permitido
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de erro para valor "Valor deve ser menor ou igual a R$ 40.000" é retornada

  @criarSimulaçãoValorMenor
  Cenario: Criar uma simulação onde o valor da mesma seja inferior que o permitido pela regra
    E informe um payload que o valor da simulação seja inferior ao permitido
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de erro para valor "Valor deve ser maior ou igual a R$ 1000" é retornada

  @criarSimulaçãoValidandoParcelas
  Esquema do Cenário: Cenario: Criar simulação informando valor limite de parcelas
    E informe um payload que possua a quantidade de parcelas <parcela>
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de erro para parcela "<message>" é retornada

    Exemplos:
      | parcela | message                                 |
      | 1       | Parcelas deve ser igual ou maior que 2  |
      | 49      | Parcelas deve ser igual ou menor que 48 |

  @criarSimulaçãoValidandoemail
  Esquema do Cenário: Cenario: Criar simulação informando valores de email inválidos
    E informe um payload que contenha um email no formato inválido "<email>"
    Quando eu realizar a requisição para criar a simulação de crédito
    E o status code na criação for um 400
    Então a mensagem de erro para email "<message>" é retornada

    Exemplos:
      | email       | message                          |
      | Teste@teste | E-mail deve ser um e-mail válido |
      | teste       | não é um endereço de e-mail      |