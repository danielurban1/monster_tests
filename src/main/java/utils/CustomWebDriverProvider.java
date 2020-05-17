package utils;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import enums.BrowserSize;
import enums.GlobalTimeouts;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriverProvider {

    private CustomWebDriverProvider(){}

    public static void configureChromeDriver() {
        configureChromeDriver(BrowserSize.STANDARD);
    }

    //TODO browser driver specification should be mer detailed
    public static void configureChromeDriver(BrowserSize browserSize) {
        Configuration.browser = Browsers.CHROME;
        WebDriverManager.chromedriver().version("local");
        Configuration.browserSize = browserSize.getResolution();
        Configuration.timeout= GlobalTimeouts.DRIVER_TIMEOUT_MILLIS.getTimeout();
    }
}
