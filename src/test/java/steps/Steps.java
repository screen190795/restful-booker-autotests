package steps;

import config.ServerConfig;
import endpoints.Endpoints;
import io.qameta.allure.Step;
import objects.User;
import specifications.Specifications;

import static io.restassured.RestAssured.given;


public class Steps {
    private ServerConfig config;

    @Step("переход на сайт, авторизация")
    public String goBookerCreateToken() {
        User user = new User(config.restful_booker_username(), config.restful_booker_password());
        return given()
                .spec(Specifications.requestSpec())
                .body(user)
                .when()
                .post(Endpoints.createToken)
                .then()
                .log().body()
                .extract().body().as(String.class);
    }
}
