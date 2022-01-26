package project.demo.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import project.demo.dto.AccountDTO;
import project.demo.model.AccountType;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountControllerTest {

    final int EXSISTUSERID = 123456789;
    final int NONEXSISTUSERID = 427513245;
    final String NOTEXISTIBAN = "NL88LEEN0012345000";
    final String EXISTIBAN = "NL88LEEN0012345678";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void initRestAssuredMockMvcStandAlone() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    // -- POST METHODS

    @Test
    public void saveNewTransaction_returns_404() {
        given()
                .param("transactionAmount", 1000)
                .param("ibanSender", "NL88LEEN0012345678")
                .param("ibanReceiver", NONEXSISTUSERID)
                .param("description", "Test Controller Test Not real iban")
                .when()
                .post("/saveNewTransaction")
                .then().statusCode(200);
    }

    @Test
    public void saveNewTransaction_returns_200() {
        given()
                .param("transactionAmount", 1000)
                .param("ibanSender", "NL88LEEN0012345678")
                .param("ibanReceiver", "NL98LEEN0050958749")
                .param("description", "Test Controller Test")
                .when()
                .post("/saveNewTransaction")
                .then().statusCode(200);
    }

    // -- GET METHODS

    // test getAccountWhereUserIsAuthorized
    // test 200 OK GET
    @Test
    public void getAccountWhereUserIsAuthorized_returns_200() {
        when()
                .get("/getAccountWhereUserIsAuthorized?id=" + EXSISTUSERID)
                .then()
                .statusCode(200);
    }

    // 404 not found
    @Test
    public void getAccountWhereUserIsAuthorized_returns_404() {
        when()
                .get("/getAccountWhereUserIsAuthorized?id=" + NONEXSISTUSERID)
                .then()
                .statusCode(404);
    }

    // test content type is application/json
    @Test
    public void getAccountWhereUserIsAuthorized_returns_content_type_application_json() {
        when()
                .get("/getAccountWhereUserIsAuthorized?id=" + EXSISTUSERID)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //test body contains correct message
    @Test
    public void getAccountWhereUserIsAuthorized_body_contains_correct_message() {
        when()
                .get("/getAccountWhereUserIsAuthorized?id=" + EXSISTUSERID)
                .then()
                .body(containsString("van Bramer, H"));
    }

    // test getAccountWhereUserIsOwner
// test 200 OK GET
    @Test
    public void getAccountWhereUserIsOwner_returns_200() {
        when()
                .get("/getAccountWhereUserIsOwner?id=" + EXSISTUSERID)
                .then()
                .statusCode(200);
    }

    // test 404
    @Test
    public void getAccountWhereUserIsOwner_returns_404() {
        when()
                .get("/getAccountWhereUserIsOwner?id=" + NONEXSISTUSERID)
                .then()
                .statusCode(404);
    }

    // test content type is application/json
    @Test
    public void getAccountWhereUserIsOwner_returns_content_type_application_json() {
        when()
                .get("/getAccountWhereUserIsOwner?id=" + EXSISTUSERID)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //test body contains correct message
    @Test
    public void getAccountWhereUserIsOwner_body_contains_correct_message() {
        when()
                .get("/getAccountWhereUserIsOwner?id=" + EXSISTUSERID)
                .then()
                .body(containsString("van Woert, S"));
    }

    // test getAccountByIBAN
// test 200 OK GET
    @Test
    public void getAccountByIBAN_returns_200() {
        when()
                .get("/getAccountByIBAN?iban=" + EXISTIBAN)
                .then()
                .statusCode(200);

    }

    // get 404
    @Test
    public void getAccountByIBANShouldReturn_404() {
        when()
                .get("/getAccountByIBAN?iban=" + NOTEXISTIBAN)
                .then()
                .statusCode(404);
    }

    // test content type is application/json
    @Test
    public void getAccountByIBAN_returns_content_type_application_json() {
        when()
                .get("/getAccountByIBAN?iban=" + EXISTIBAN)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //test body contains correct message
    @Test
    public void getAccountByIBAN_body_contains_correct_message() {
        when()
                .get("/getAccountByIBAN?iban=" + EXISTIBAN)
                .then()
                .body(containsString("Jansen, M"));
    }

}
