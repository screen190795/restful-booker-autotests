package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com/")
                .setContentType(ContentType.ANY)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
