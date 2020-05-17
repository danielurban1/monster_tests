package pages;

import com.codeborne.selenide.SelenideElement;
import enums.URLs;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static utils.Reporter.logInfo;

public class HomePage extends BasePage{

    public static final String URL = URLs.MONSTER_HOME_PAGE_URL.getUrl();
    private SelenideElement btnCreateAccount = $(By.xpath("//a[@href = '/account/account-lite']"));

    public void clickCreateAccountButton(){
        logInfo("Clicking Create Account Button");
        click(btnCreateAccount);
    }

}
