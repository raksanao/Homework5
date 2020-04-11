package com.automation.tests;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigReader;
import com.automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver = Driver.getDriver();

    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;


    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BrowserUtils.wait(4);
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void after(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            String screenshortResult = BrowserUtils.getScreenshot(iTestResult.getName());
        }
        BrowserUtils.wait(3);
        Driver.closeDriver();

    }
}