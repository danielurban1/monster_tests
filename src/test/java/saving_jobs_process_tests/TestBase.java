package saving_jobs_process_tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import enums.BrowserSize;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;
import utils.CustomWebDriverProvider;
import utils.Reporter;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.Reporter.*;

public abstract class TestBase {

    @BeforeTest
    public void setUp() {
        setUpReporter();
        CustomWebDriverProvider.configureChromeDriver(BrowserSize.FULL_HD);
    }

    @AfterMethod
    public void endReport(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logFail("Test FAILED");
            logFail(result.getThrowable().toString());
            String screenshotPath = getScreenshot(result.getName());
            addScreenShotToReport(screenshotPath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            logSkip("Test was SKIPPED");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logPass("Test PASSED");
        }
        flush();
    }

    private String getScreenshot(String screenShotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getWebDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/screenshots/" + screenShotName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
