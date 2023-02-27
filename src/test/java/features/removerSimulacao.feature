#language:pt
@RemoverSimulacaoGeral
Funcionalidade: Apagar uma simulacao que ja tenha sido criada anteriormente e validar retornos esperados

  Contexto:
    Dado que eu possua acesso à URL para deletar simulações

  @removerSimulacao
  Cenario: deletar uma simulação cadastrada anteriormente com base no seu ID
    E cadastre uma simulação de credito para um CPF válido
    Quando eu solicitar a deleção dessa simulação com base no id gerado anteriormente
    Então o status code retornado é um 204

  @removerSimulacaoInexistente
  Cenario: Tentar remover uma simulação informando um id que não exista
    Quando eu solicitar a deleção informando um id que não exista
    Então o status code retornado é um 404
