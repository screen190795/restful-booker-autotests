package steps;

import endpoints.Endpoints;
import io.qameta.allure.Step;
import objects.User;
import specifications.Specifications;

import static io.restassured.RestAssured.given;


public class Steps {
    @Step("переход на сайт, авторизация")
    public String goBookerCreateToken(String username, String password) {
        User user = new User(username, password);
        return given()
                .spec(Specifications.requestSpec())
                .body(user)
                .when()
                .post(Endpoints.createToken)
                .then()
                .log().body()
                .extract().body().jsonPath().getString("token");
    }

    @Step("переход на сайт, авторизация")
    public String goBookerCreateInvalidToken(String username, String password) {
        User user = new User(username, password);
        return given()
                .spec(Specifications.requestSpec())
                .body(user)
                .when()
                .post(Endpoints.createToken)
                .then()
                .spec(Specifications.responseSpec())
                .log().status().log().body()
                .extract().body().jsonPath().getString("reason");
    }
}
