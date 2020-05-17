package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.GlobalTimeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.Reporter.logInfo;

public abstract class BasePage {
    private static WebDriverWait wait;

    //Top menu
    private SelenideElement btnMyJobSearch = $(By.xpath("//ul[@class = 'nav navbar-nav']/li[2]"));
    private SelenideElement btnSavedJobs = $(By.xpath("//a[contains(@href, '/SavedJobs/')]"));

    //Footer
    private SelenideElement btnPhilipsJobs = $(By.xpath("//a[@href = 'https://browse.monsterworksdemo.com/search/?cn=Philips']"));

    protected void click(SelenideElement by) {
        waitForElementToBeActive(by);
        $(by).click();
    }

    protected void sendText(SelenideElement by, String text) {
        waitForElementToBeActive(by);
        if (!$(by).getValue().equals("")) {
            $(by).clear();
        }
        $(by).sendKeys(text);
    }

    protected void waitForElementToBeActive(SelenideElement by) {
        $(by)
                .waitUntil(Condition.exist, GlobalTimeouts.MAX_PRESENCE_TIMEOUT_MILLIS.getTimeout())
                .scrollTo()
                .waitUntil(Condition.visible, GlobalTimeouts.MAX_VISIBILITY_TIMEOUT_MILLIS.getTimeout())
                .waitUntil(Condition.enabled, GlobalTimeouts.MAX_INTERACTIVITY_TIMEOUT_MILLIS.getTimeout());
    }

    public void selectSavedJobsFromMyJobSearchMenu() {
        logInfo("Selecting My Job Search > Saved Jobs");
        logInfo("Hovering mouse over My Job Search button");
        waitForElementToBeActive(btnMyJobSearch);
        btnMyJobSearch
                .hover();
        logInfo("Clicking Saved Jobs button");
        waitForElementToBeActive(btnSavedJobs);
        btnSavedJobs.click();
    }

    public void clickPhilipsJobsButton() {
        logInfo("Clickingg Philips Jobs button");
        waitForElementToBeActive(btnPhilipsJobs);
        btnPhilipsJobs.click();
    }

    protected void javaScriptExecutorScrollToElement(SelenideElement by) {
        try {
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", by);
        } catch (Exception e) {
            e.getCause();
        }
    }

    protected static WebDriverWait getWebDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(getWebDriver(), 10);
        }
        return wait;
    }
}
