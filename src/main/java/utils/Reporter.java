package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;

//TODO Reporter class should be
public class Reporter {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    private Reporter(){}

    public static void setUpReporter() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/report.html");
        sparkReporter.config().setDocumentTitle("Monster tests results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public static void createTest(String testName) {
        extentTest = extentReports.createTest(testName);
    }

    public static void logPass(String details) {
        extentTest.log(Status.PASS, details);
    }

    public static void logInfo(String details) {
        extentTest.log(Status.INFO, details);
    }

    public static void logSkip(String details) {
        extentTest.log(Status.SKIP, details);
    }

    public static void logFail(String details) {
        extentTest.log(Status.FAIL, details);
    }

    public static void flush() {
        extentReports.flush();
    }

    public static void addScreenShotToReport(String screenshotPath) throws IOException {
        extentTest.addScreenCaptureFromPath(screenshotPath);
    }

}

