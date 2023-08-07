package org.example.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportListener implements ITestListener {
    protected static ExtentReports reports;
    public static ExtentTest test;

    private static String resultPath = getResultPath();

    private static String getResultPath() {
        resultPath = "Test";
        if (!new File(resultPath).isDirectory()) {
            new File(resultPath);
        }
        return resultPath;
    }

    String reportLocation = "report/" + resultPath + "/";

    public void onTestStart(ITestResult result) {
        test = reports.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "<h2>Test: " + result.getMethod().getMethodName() + "</h2>");
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test is pass");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getThrowable());
        test.log(Status.FAIL, "Test is fail");
    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test is skip");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter(reportLocation);
        spark.config().setTheme(Theme.DARK);
        reports = new ExtentReports();
        reports.attachReporter(spark);
    }

    public void onFinish(ITestContext context) {
        reports.flush();
    }

    public void infoReport(String message) {
        test.log(Status.INFO, message);
    }

}
