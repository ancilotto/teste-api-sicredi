<div align="center">
  <img src="https://tinypic.host/images/2023/02/26/Sicredi.png" width="300px" />
</div>

<h1 align="center">Projeto de automação de testes. SICREDI </h1>

<div class="objetivo">
  <h2> 📌 Objetivo do repositorio</h2>
<p>
  ▪️Esse repositorio foi criado com o objetivo de criar e manter a coberturas dos
  testes automatizados referente à API de simulações de credito do banco SICREDI.
  <h5>Para mais informações sobre regras de negócio, e api alvo acessar o repositorio: https://github.com/rh-southsystem/Sicredi-Digital-QA </h5>
</p>
</div>

<div class="API">
  <h2> 📌 Nossa automação</h2>
<p>
  Basicamente nossa api é composta por três servicos principais, que seriam eles:<br>
  ▪️<b>Criar Simulação</b>➡️<i>Endpoint do tipo POST responsável por criar simulações de crédito</i><br>
  ▪️<b>Consultar Simulações</b>➡️<i>Consultar através de um GET simulações criadas anteriormente ou por um CPF específico</i><br>
  ▪️<b>Alterar Simulações</b>➡️<i>Altera através de um PUT uma simulação que já havia sido criada</i><br>
  ▪️<b>Consultar Restrições</b>➡️<i>Consulta se um CPF possui restrição para criar uma simulação de crédito ou não</i><br>
  ▪️<b>Deletar Simulações</b>➡️<i>Apaga através de um DEL uma simulação ja cadastrada</i><br>
</p>
</div>

<div class="TECNOLOGIAS">
  <h2> 📌 Técnologias utilizadas:</h2>
  <div align="center">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg"  width="100px" alt=""> 
    <img src="https://tinypic.host/images/2023/02/26/19369327.png" width="100px" alt="">
    <img src="https://user-images.githubusercontent.com/62122651/168498190-74c8e300-6aa6-4f29-bf18-d039a1125252.png" width="100px" />
    <img src="https://tinypic.host/images/2023/02/26/Apache_Maven_logo.svg.png" width="100px alt="">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gitlab/gitlab-original-wordmark.svg" width="100px" alt="" />
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original-wordmark.svg" width="100px" alt="" />                                                                     
  </div>
                                                                                                                          
  <div class="testando">
  <h2> 📌 Como executar os testes?</h2>
  <div align="center">
    ▪️<b>Clone esse repositorio para seu desktop e execute com sua IDE favorita.</i><br>
    ▪️<b>Certifique-se de ter a versão do JDK 8 ou superior instalada no seu computador.</i><br> 
    ▪️<b>Faça a compilação do projeto, famoso buildar o projeto.</i><br>
    ▪️<b>Dentro da pasta "src/teste/java/features" você encontrara os cenários e pode executa-los.</i><br>                                      
  </div>                                                                                                                        
                                                                                                                          
  <div class="relatório atual">
  <h2> 📌 Defeitos encontrados</h2>
  </div>
  <table>
  <thead>
    <tr>
      <th>ENPOINT</th>
      <th>REGRA </th>
      <th>COMPORTAMENTO REAL</th>
      <th>COMPORTAMENTO ESPERADO</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Consultar restrição</td>
      <td>Ao consultar um CPF que tenha restrição retornar mensagem de erro e status code 200</td>
      <td>Mensagem de retorno: "O CPF ########### tem problema"</td>
      <td>Mensagem de retorno: "O CPF ########### possuí restrição"</td
    </tr>
    <tr>
      <td>Criar Simulação</td>
      <td>Aceitar apenas CPF válido no campo referente ao mesmo</td>
      <td>Criando Simulação com CPF inválido</td>
      <td>Não criar a simulação e retornar mensagem de erro para CPF inválido</td                        
    </tr>
    <tr>
      <td>Criar Simulação</td>
      <td>Criar Simulação com valor inferior a 40.000 e maior que 1000</td>
      <td>Permite criar simulação com valor inferior a 1000</td>
      <td>Retornar mensagem de erro referente a valor minimo e não criar a simulação</td                        
    </tr>
    <tr>
      <td>Criar Simulação</td>
      <td>Criar Simulação onde as parcelas devem ser maior ou igual a 2 e igual ou menor que 48</td>
      <td>Permite criar simulação parcelas acima de 48</td>
      <td>Retornar mensagem de erro referente a parcela acima do limite e nao criar a simulação</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Campo seguro deve aceitar apenas valores como true ou false</td>
      <td>Campo aceita qualquer valor desde que esteja dentro de uma string</td>
      <td>Campo deveria retornar mensagem de erro para o valor informado no cmapo seguro e não alterar a simulação</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Campo pode ser alterado desde que seguindo as regras de limite minimo e maximo</td>
      <td>Campo recebe os valores referente à alteração, e altera a mesma</td>
      <td>Alteração é solicitada e o campo valor não é alterado, campo nao permite alteração</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Campo valor pode ser alterado desde que seguindo as regras de limite minimo e maximo</td>
      <td>Campo valor recebe os valores referente à alteração, e altera a mesma</td>
      <td>Alteração é solicitada e o campo valor não é alterado, campo nao permite alteração</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Campo parcelas pode ser alterado desde que seguindo as regras de limite minimo e maximo</td>
      <td>Campo parcelas recebe o valor acima de 48 e a alteração é realizada</td>
      <td>Alteração não deve ser realizada e deve retornar mensagem de erro referente ao numero de parcelas acima do permitido</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Informar um CPF que não seja encontrado </td>
      <td>Retorno statusCode 400</td>
      <td>Retorno statusCode 404</td                        
    </tr>
    <tr>
      <td>Alterar Simulação</td>
      <td>Informar um email no formato inválido e não realizar a alteração</td>
      <td>Mensagem de erro varia entre "E-mail deve ser um e-mail válido" e "não é um endereço de e-mail"</td>
      <td>Devemos padronizar as mensagens</td                        
    </tr>
    <tr>
      <td>Remover simulação</td>
      <td>Quando informar um id válido para deleção a mesma deve ser deletada e o status code deve ser 204</td>
      <td>Status code retornado 200</td>
      <td>Deve retornar status code 204</td                        
    </tr>
    <tr>
      <td>Remover simulação</td>
      <td>Quando informar um id que não exista para deleção deve retornar um status code 404</td>
      <td>Status code retornado 200</td>
      <td>Deve retornar status code 404 e mensagem de id não encontrado</td                        
    </tr>                                                                                                      
  </tbody>
</table>

</div>
