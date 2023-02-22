#language:pt
Funcionalidade: Consultar o CPF informando, retornando se ele possui ou não uma restrição

  Contexto:
    Dado que eu tenha acesso a API de consulta de CPF

  @Teste
  Cenário: Verificar se o resultado ao consultar um cpf sem restrição está de acordo com a documentação
    Quando informar um CPF sem restrição e realizar a consulta
    Entao o status code retornado é 204

  @Teste
  Esquema do Cenário: Realizar consultar em CPF's que possuam restrição e validar o retorno de acordo com a regra
    Quando realizar uma consulta informando um "<cpf_restricao>" que contenha restrição
    Entao o status code retornado é 200 e a mensagem "O CPF <cpf_restricao> tem restrição"

    Exemplos:
      | cpf_restricao |
      | 97093236014   |
      | 60094146012   |
      | 84809766080   |
      | 62648716050   |
      | 26276298085   |
      | 01317496094   |
      | 55856777050   |
      | 19626829001   |
      | 58063164083   |
      | 24094592008   |
