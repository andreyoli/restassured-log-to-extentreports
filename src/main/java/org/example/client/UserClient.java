package org.example.client;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.requestSpecification.GoRestRequesSpecification;

import static io.restassured.RestAssured.given;
import static org.example.reporter.RestAssuredLogConverterToExtent.logRestAssuredReport;

public class UserClient {
    RequestSpecification goRestRequesSpecification = GoRestRequesSpecification.get();
    private RequestSpecification request;

    public UserClient() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public ValidatableResponse getUserById(String user) {
        request = given()
                .spec(goRestRequesSpecification)
                .when();

        ValidatableResponse response = request.pathParam("user", user).get("/{user}").then();

        logRestAssuredReport(request, response);

        return response;
    }

    public ValidatableResponse getUsers() {
        request = given()
                .spec(goRestRequesSpecification)
                .when();

        ValidatableResponse response = request.get().then();

        logRestAssuredReport(request, response);

        return response;
    }
}
