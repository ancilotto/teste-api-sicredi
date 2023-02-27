#language:pt
@alterarSimulacaoGeral
Funcionalidade: Endpoint responsável por realizar a alteracao de informacoes referentes a uma simulação previamente cadastrada

  Contexto:
    Dado que eu tenha acesso à URL de alterar simulações

  @alterarSimulaçãoSucesso
  Esquema do Cenário: Fazer alterações de sucesso por meio do endpoint responsavel
    E informe no payload o campo "<campoPayload>" para ser alterado recebendo a informação "<info>"
    Quando eu realizar a requisição para alterar a simulação ja existente
    E o status code for um 200
    Então o contrato de retorno deve ser validado

    Exemplos:
      | campoPayload | info                   |
      | email        | ancilotto.ne@gmail.com |
      | nome         | Pedro Ancilotto        |
      | valor        | 23000                  |
      | parcelas     | 34                     |
      | seguro       | true                   |
      | seguro       | false                  |

  @alterarSimulaçãoCpfDuplicado
  Cenario:Tentar fazer uma alteração para um CPF que não tenha uma simulação previamente cadastrada
    E informe um payload com informações válidas e uma URL com um CPF que nao tenha cadastro
    Quando eu realizar a requisição para alterar com um CPF não cadastrado
    E o status code for um 404
    Então a mensagem "CPF não encontrado"

  @alterarSimulaçãoDadosInválidos
  Esquema do Cenário: Informar valores inválidos nos campos para realizar alterações
    E informe no payload o campo "<campoPayload>" para ser alterado recebendo a informação "<info>"
    Quando eu realizar a requisição para alterar a simulação ja existente
    #E o status code for um 400
    Então a mensagem de erro "<message>" para o campo "<campoPayload>" deve ser retornada

    Exemplos:
      | campoPayload | info       | message                                   |
      | valor        | 999        | Valor deve ser maior ou igual a R$ 1000   |
      | valor        | 50000      | Valor deve ser menor ou igual a R$ 40.000 |
      | parcelas     | 1          | Parcelas deve ser igual ou maior que 2    |
      | parcelas     | 50         | Parcelas deve ser igual ou menor que 48   |
      | email        | ancilotto@ | E-mail deve ser um e-mail válido          |
      | seguro       | abecedario |                                           |


