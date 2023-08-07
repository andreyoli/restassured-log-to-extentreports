package org.example.reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static org.example.reporter.ExtentReportListener.test;

public class RestAssuredLogConverterToExtent {

    public static void logAllRequestInfo(RequestSpecification request) {
        test.log(Status.INFO, "<h3>Request</h3>");
        FilterableRequestSpecification requestSpecification = (FilterableRequestSpecification) request;

        String body = requestSpecification.getBody() == null ? "{}" : requestSpecification.getBody().toString();

        List<String> lines = new ArrayList<>();
        lines.add("URI: " + requestSpecification.getBaseUri());

        if (!requestSpecification.getQueryParams().isEmpty())
            lines.add("QueryParams: " + requestSpecification.getQueryParams().toString());

        if (!requestSpecification.getFormParams().isEmpty())
            lines.add("FormParams: " + requestSpecification.getFormParams().toString());

        if (!requestSpecification.getPathParams().isEmpty())
            lines.add("PathParams: " + requestSpecification.getPathParams());

        if (requestSpecification.getHeaders().size() <= 0)
            lines.add("Headers: " + requestSpecification.getHeaders().toString());

        if (requestSpecification.getBody() != null)
            lines.add("Body: " + body);


        test.log(Status.INFO, String.join("<br>", lines));
    }

    public static void logAllResponseInfo(ValidatableResponse response) {
        test.log(Status.INFO, "<h3>Response</h3>");
        test.log(Status.INFO, "StatusCode: " + response.extract().statusCode());
        test.log(Status.INFO, "Response Body: " + MarkupHelper.createCodeBlock(response.extract().asPrettyString(), CodeLanguage.JSON).getMarkup());
    }

    public static void logRestAssuredReport(RequestSpecification request, ValidatableResponse response) {
        logAllRequestInfo(request);
        logAllResponseInfo(response);
    }

}
