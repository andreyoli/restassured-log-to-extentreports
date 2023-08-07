package org.example.requestSpecification;

import io.restassured.specification.RequestSpecification;

public class GoRestRequesSpecification {
    public GoRestRequesSpecification() {
    }

    public static RequestSpecification get() {
        return RequestSpecificationBuilder.builder().basePath("/users").build();
    }
}
