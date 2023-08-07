package org.example.requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {
    private String basePath;

    public static RequestSpecificationBuilder builder() {
        return new RequestSpecificationBuilder();
    }

    public RequestSpecificationBuilder basePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public RequestSpecification build() {
        return new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .setBaseUri("https://gorest.co.in/public/v2")
                .setBasePath(basePath)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
