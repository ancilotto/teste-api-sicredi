package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CriarSimulacao {
    String url = null;
    String payloadSimulacao;
    Response response;

    @Dado("que eu tenha acesso à API de criação de simulações")
    public void que_eu_tenha_acesso_a_api_de_criacao_de_simulacoes() {
        url = "http://localhost:8080/api/v1/simulacoes";
        System.out.println("URL para realizar a criação é: " + url);
    }

    @E("tenha um payload com dados válidos para utilizar na criação")
    public void tenha_um_payload_com_dados_validos_para_utilizar_na_criacao() throws JsonProcessingException {
        Faker faker = new Faker();

        String nome = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String cpf = faker.numerify("###########");
        Integer valor = faker.number().numberBetween(2000, 5000);
        Integer parcelas = faker.number().numberBetween(2, 48);
        boolean seguros = faker.random().nextBoolean();

        JSONObject payloadCreate = new JSONObject()
                .put("cpf", cpf)
                .put("nome", nome)
                .put("email", email)
                .put("valor", valor)
                .put("parcelas", parcelas)
                .put("seguro", seguros);

        String payloadCreateString = payloadCreate.toString();
        payloadSimulacao = payloadCreateString;
    }

    @Quando("eu realizar a requisição para criar a simulação de crédito")
    public void eu_realizar_a_requisição_para_criar_a_simulação_de_crédito() {
        response =  RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(payloadSimulacao)
                .when()
                .post(url)
                .then()
                .extract().response();

    }
    @E("o status code na criação for um {int}")
    public void o_status_code_na_criação_for_um(Integer code) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);
        System.out.println("SUCESSO: StatusCode foi 201, de acordo com o esperado!");
    }

    @Então("o response é validado de acordo com o esperado")
    public void o_response_é_validado_de_acordo_com_o_esperado() {
        System.out.println("Iniciando validação de Contrato/Schema");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("criarSimulacao.json"));
        System.out.println("CONTRATO/SCHEMA VALIDADO COM SUCESSO!");
    }

}
