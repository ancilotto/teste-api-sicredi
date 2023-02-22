package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsultarSimulacoes {
    String url = "http://localhost:8080/api/v1/";
    Response response;

    @Quando("realizar uma consulta para listar todas as simulacoes já criadas")
    public void realizar_uma_consulta_para_listar_todas_as_simulacoes_já_criadas() {
        url = url + "simulacoes";
        response = RestAssured.request(Method.GET, url);
        System.out.println("Status code após a requisição é: " + response.statusCode());
    }

    @Entao("é retornado o status code {int}")
    public void é_retornado_o_status_code(Integer code) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);
    }

    @Quando("realizar uma consulta informando um CPF que ja tenha realizado uma simulação")
    public void realizar_uma_consulta_informando_um_cpf_que_ja_tenha_realizado_uma_simulação() {
        url = url + "simulacoes/66414919004";
        response = RestAssured.request(Method.GET, url);
    }

    @E("o retorno da consulta é validado")
    public void o_retorno_da_consulta_for_validado() {
        System.out.println("Iniciando validação de Contrato/Schema");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("consultarSimulacao.json"));
        System.out.println("Response body recebido: " + response.getBody().asString());
    }

    @Quando("realizar a consulta informando um {string} que não tenha uma simulação previamente cadastrada")
    public void realizar_a_consulta_informando_um_que_não_tenha_uma_simulação_previamente_cadastrada(String cpf) {
        url = url + "simulacoes/" + cpf;
        System.out.println(url);
        response = RestAssured.request(Method.GET, url);
    }

    @Entao("o status code para a consultade simulação retornado é {int} e a mensagem {string}")
    public void o_status_code_para_a_consultade_simulação_retornado_é_e_a_mensagem(Integer code, String message) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);

        JsonPath resultMessage = new JsonPath(response.getBody().asString());
        assertEquals(resultMessage.getString("mensagem"), message);
    }
}
