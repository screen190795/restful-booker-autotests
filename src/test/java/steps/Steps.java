package steps;

import io.qameta.allure.Step;
import objects.Booking;
import objects.User;

import java.util.List;
import java.util.Map;

import static endpoints.Endpoints.booking;
import static endpoints.Endpoints.createToken;
import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpec;
import static specifications.Specifications.responseSpec;


public class Steps {
    @Step("переход на сайт, авторизация")
    public String goBookerCreateToken(String username, String password) {
        User user = new User(username, password);
        return given()
                .spec(requestSpec())
                .body(user)
                .when()
                .post(createToken)
                .then()
                .log().body()
                .extract().body().jsonPath().getString("token");
    }

    @Step("переход на сайт, авторизация")
    public String goBookerCreateInvalidToken(String username, String password) {
        User user = new User(username, password);
        return given()
                .spec(requestSpec())
                .body(user)
                .when()
                .post(createToken)
                .then()
                .spec(responseSpec())
                .log().status().log().body()
                .extract().body().jsonPath().getString("reason");
    }

    @Step("получение списка всех id бронирований")
    public List<Integer> getBookingIds() {
        return given()
                .spec(requestSpec())
                .when()
                .get(booking)
                .then()
                .spec(responseSpec())
                .log().status()
                .extract().body().jsonPath().getList("bookingid");
    }

    @Step("получение бронирования по id")
    public Booking getBooking(String bookingId) {
        return given()
                .spec(requestSpec())
                .when()
                .get(booking + "/" + bookingId)
                .then()
                .spec(responseSpec())
                .log().status().log().body()
                .extract().body().as(Booking.class);
    }

    @Step("получение списка всех id бронирований с параметрами фильтра")
    public List<Integer> getBookingIdsByParams(Map<String, Object> paramsMap) {
        return given()
                .spec(requestSpec())
                .params(paramsMap)
                .when()
                .get(booking)
                .then()
                .spec(responseSpec())
                .log().status()
                .log().body()
                .extract().body()
                .jsonPath().getList("bookingid");
    }

}
