package org.example.base;

import org.example.reporter.ExtentReportListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({ExtentReportListener.class})
public class BaseTest extends ExtentReportListener {

    @BeforeSuite
    public void setup() {
    }
}
