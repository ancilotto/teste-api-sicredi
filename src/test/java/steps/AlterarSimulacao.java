package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static org.junit.Assert.assertEquals;

public class AlterarSimulacao {

    Faker faker = new Faker();
    String url = null;
    String payloadAlterarSimulacao;
    Response response;

    @Dado("que eu tenha acesso à URL de alterar simulações")
    public void que_eu_tenha_acesso_à_url_de_alterar_simulações() {
        url = "http://localhost:8080/api/v1/simulacoes/";
        System.out.println("URL para alterar simulações é: " + url);
    }

    @E("informe um payload com informações válidas e uma URL com um CPF que nao tenha cadastro")
    public void informe_um_payload_com_informações_válidas_e_uma_url_com_um_cpf_que_nao_tenha_cadastro() {
        String nome = faker.name().fullName();
        String email = faker.internet().emailAddress();
        Integer valor = faker.number().numberBetween(2000, 5000);
        Integer parcelas = faker.number().numberBetween(2, 48);
        boolean seguros = faker.random().nextBoolean();

        JSONObject payloadCreate = new JSONObject()
                .put("nome", nome)
                .put("email", email)
                .put("valor", valor)
                .put("parcelas", parcelas)
                .put("seguro", seguros);

        String payloadCreateString = payloadCreate.toString();
        payloadAlterarSimulacao = payloadCreateString;
    }

    @Quando("eu realizar a requisição para alterar com um CPF não cadastrado")
    public void eu_realizar_a_requisição_para_alterar_com_um_cpf_não_cadastrado() {
        String cpf = faker.numerify("###########");

        response = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(payloadAlterarSimulacao)
                .when()
                .put(url + cpf)
                .then()
                .extract().response();
    }


    @E("informe no payload o campo {string} para ser alterado recebendo a informação {string}")
    public void informe_no_payload_o_campo_para_ser_alterado_recebendo_a_informação(String campoPayload, String info) {
        String nome = null;
        String email = null;
        Integer valor = null;
        Integer parcelas = null;
        Boolean seguros = null;

        if (campoPayload.equals("nome")) {
            nome = info;
        } else if (!campoPayload.equals("nome")) {
            nome = faker.name().fullName();
        }

        if (campoPayload.equals("email")) {
            email = info;
        } else if (!campoPayload.equals("email")) {
            email = faker.internet().emailAddress();
        }

        if (campoPayload.equals("valor")) {
            valor = Integer.valueOf(info);
        } else if (!campoPayload.equals("valor")) {
            valor = faker.number().numberBetween(2000, 5000);
        }

        if (campoPayload.equals("parcelas")) {
            parcelas = Integer.valueOf(info);
        } else if (!campoPayload.equals("parcelas")) {
            parcelas = faker.number().numberBetween(2, 48);
        }

        if (campoPayload.equals("seguros")) {
            seguros = Boolean.valueOf(info);
        } else if (!campoPayload.equals("seguros")) {
            seguros = faker.random().nextBoolean();
        }

        JSONObject payloadCreate = new JSONObject()
                .put("nome", nome)
                .put("email", email)
                .put("valor", valor)
                .put("parcelas", parcelas)
                .put("seguro", seguros);

        String payloadCreateString = payloadCreate.toString();
        payloadAlterarSimulacao = payloadCreateString;
    }

    @Quando("eu realizar a requisição para alterar a simulação ja existente")
    public void eu_realizar_a_requisição_para_alterar_a_simulação_ja_existente() {
        response = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(payloadAlterarSimulacao)
                .when()
                .put(url + "66414919004")
                .then()
                .extract().response();
    }


    @E("o status code for um {int}")
    public void o_status_code_for_um(Integer code) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);
        System.out.println("SUCESSO: StatusCode foi " + code + ", de acordo com o esperado!");
    }

    @Então("o contrato de retorno deve ser validado")
    public void o_contrato_de_retorno_deve_ser_validado() {
        System.out.println("Iniciando validação de Contrato/Schema");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("criarSimulacao.json"));
        System.out.println("CONTRATO/SCHEMA VALIDADO COM SUCESSO!");
    }

    @Então("a mensagem {string}")
    public void a_mensagem(String message) {
        JsonPath resultMessage = new JsonPath(response.getBody().asString());
        assertEquals(resultMessage.getString("mensagem"), message);
    }

    @Então("a mensagem de erro {string} para o campo {string} deve ser retornada")
    public void a_mensagem_de_erro_para_o_campo_deve_ser_retornada(String message, String campoPayload) {
        JsonPath resultMessage = new JsonPath(response.getBody().asString());
        assertEquals(resultMessage.getString("erros." + campoPayload), message);
    }
}

