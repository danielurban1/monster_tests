package utils;

import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UrlUtils {

    private UrlUtils(){}

    private static WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);

    public static boolean isCurrentUrlEqualTo(String expectedUrl){
        return wait.until(d -> d.getCurrentUrl().equals(expectedUrl));
    }
}
