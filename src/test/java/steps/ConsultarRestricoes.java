package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.assertEquals;


public class ConsultarRestricoes {
    String url = "http://localhost:8080/api/v1/";
    Response response;


    @Dado("que eu tenha acesso a API de consulta de CPF")
    public void que_eu_tenha_acesso_a_api_de_consulta_de_cpf_s() {
        System.out.println(url);
    }

    @Quando("informar um CPF sem restrição e realizar a consulta")
    public void informar_um_CPF_sem_restrição_e_realizar_a_consulta() {
        url = url + "restricoes/40129091855";
        response = RestAssured.request(Method.GET, url);
        System.out.println("Status code após a requisição é " + response.statusCode());
    }

    @Entao("o status code retornado é {int}")
    public void o_status_code_retornado_é(Integer code) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);
    }

    @Quando("realizar uma consulta informando um {string} que contenha restrição")
    public void realizar_uma_consulta_informando_um_que_contenha_restrição(String cpf_restricao) {
        String url_restricao = url + "restricoes/" + cpf_restricao;
        response = RestAssured.request(Method.GET, url_restricao);

    }

    @Entao("o status code retornado é {int} e a mensagem {string}")
    public void o_status_code_retornado_é_e_a_mensagem(Integer code, String message) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);

        JsonPath resultMessage = new JsonPath(response.getBody().asString());
        assertEquals(resultMessage.getString("mensagem"),message);
    }


}
