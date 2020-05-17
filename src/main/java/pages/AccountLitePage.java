package pages;

import static com.codeborne.selenide.Selenide.$;
import static utils.Reporter.logInfo;

import com.codeborne.selenide.SelenideElement;
import enums.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AccountLitePage extends BasePage {
    private Random random = new Random();

    public static final String URL = URLs.MONSTER_ACCOUNT_LITE_PAGE_URL.getUrl();

    private SelenideElement inputEmail = $(By.xpath("//input[@placeholder = 'Email Address' and not(contains(@class, 'hide'))]"));
    private SelenideElement inputPassword = $(By.id("a_elem_1"));
    private SelenideElement inputReEnterPassword = $(By.id("a_elem_2"));
    private Select selectJobsCorpCenter = new Select($(By.id("elem_3")));
    private SelenideElement checkBoxTermsAndConditions = $(By.xpath("//span[@class = 'checkbox-custom checkbox-toggle']"));
    private SelenideElement btnCreateAnAccount = $(By.xpath("//div[@class = 'col-sm-12']/button[@ng-hide]"));

    public void inputEmail(String email) {
        logInfo("Inputting email: " + email);
        sendText(inputEmail, email);
    }

    public void inputPassword(String password) {
        logInfo("Inputting password: " + password);
        sendText(inputPassword, password);
    }

    public void reEnterPassword(String password) {
        logInfo("Re-inputting password: " + password);
        sendText(inputReEnterPassword, password);
    }

    public void selectRandomJobCorpsCenter() {
        logInfo("Selecting random job coprs center");
        List<WebElement> optionsList = selectJobsCorpCenter.getOptions();
        List<WebElement> filteredOptionsList = optionsList.stream()
                .filter(we -> !we.getAttribute("value").equals("object:null"))
                .collect(Collectors.toList());
        int randomListIndex = random.nextInt(filteredOptionsList.size());
        logInfo("Selected: " + selectJobsCorpCenter.getOptions().get(randomListIndex).getText());
        selectJobsCorpCenter.selectByVisibleText(filteredOptionsList.get(randomListIndex).getText());
    }

    public void acceptTermsAndConditions() {
        logInfo("Clicking accept Terms and Conditions button");
        click(checkBoxTermsAndConditions);
    }


    public void clickCreateAnAccountButton() {
        logInfo("Clicking Create An Account button");
        click(btnCreateAnAccount);
    }
}
