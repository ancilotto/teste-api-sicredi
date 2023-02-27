package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class RemoverSimulacao {
    String url = null;
    Response response = null;
    Faker faker = new Faker();
    String payloadSimulacao;
    String idCriacao = null;

    @Dado("que eu possua acesso à URL para deletar simulações")
    public void que_eu_possua_acesso_à_url_para_deletar_simulações() {
        url = "http://localhost:8080/api/v1/simulacoes/";
        System.out.println("URL para deletar simulações é: " + url);
    }

    @E("cadastre uma simulação de credito para um CPF válido")
    public void cadastre_uma_simulação_de_credito_para_um_cpf_válido() {
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

        response = given()
                .log().all()
                .contentType("application/json")
                .body(payloadSimulacao)
                .when()
                .post(url)
                .then()
                .extract().response();

        ValidatableResponse validacao = response.then();
        validacao.statusCode(201);

        idCriacao = response.jsonPath().getString("id");
    }

    @Quando("eu solicitar a deleção dessa simulação com base no id gerado anteriormente")
    public void eu_solicitar_a_deleção_dessa_simulação_com_base_no_id_gerado_anteriormente() {
        response = given()
                .delete(url+idCriacao)
                .then()
                .extract().response();
    }

    @Quando("eu solicitar a deleção informando um id que não exista")
    public void eu_solicitar_a_deleção_informando_um_id_que_não_exista() {
        response = given()
                .delete(url+"123453")
                .then()
                .extract().response();
    }



    @Então("o status code retornado é um {int}")
    public void o_status_code_retornado_é_um(Integer code) {
        ValidatableResponse validacao = response.then();
        validacao.statusCode(code);
        System.out.println("SUCESSO: StatusCode foi " + code + ", de acordo com o esperado!");
    }
}
