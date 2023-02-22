#language:pt
Funcionalidade: Consultar simulações de credito criadas para determinado CPF ou consultar todas as simulações já criadas

  Contexto:
    Dado que eu tenha acesso a API de consulta de CPF

  Cenário: Verificar se o retorno referente a simulações está de acordo com a documentação
    Quando realizar uma consulta para listar todas as simulacoes já criadas
    Entao o status code retornado é 200

  Cenário: Consultar um CPF que já tenha cadastrado uma simulação de crédito
    Quando realizar uma consulta informando um CPF que ja tenha realizado uma simulação
    Entao o status code retornado é 200

  Esquema do Cenário: Realizar a consulta validando um CPF que não tenha cadastrado uma simulaçao
    Quando realizar a consulta informando um <cpf> que não tenha uma simulação previamente cadastrada
    Entao o status code retornado é 404 e a mensagem "O CPF <cpf> tem restrição"

    Exemplos:
      | cpf         |
      | 40129091855 |
#      | 46282163803 |
