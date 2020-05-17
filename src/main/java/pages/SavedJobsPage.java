package pages;

import com.codeborne.selenide.SelenideElement;
import enums.URLs;
import models.JobOffer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.Reporter.logInfo;

public class SavedJobsPage extends BasePage{

    public static final String URL = URLs.MONSTER_SAVED_JOBS_URL.getUrl();
    private SelenideElement txtSavedJobsAlert = $(By.xpath("//div[@class = 'alert alert-info ng-binding']"));

    private By txtSavedJobsNamesBy = By.xpath("//a[@ng-click= 'getJob(job.id)']");
    private List<SelenideElement> txtSavedJobsNames = $$(txtSavedJobsNamesBy);
    private List<SelenideElement> txtSavedJobsCompanyNames = $$(By.xpath("//span[@title = 'Company']"));
    private List<SelenideElement> txtSavedJobsLocations = $$(By.xpath("//span[@title = 'Location']"));
    private List<SelenideElement> txtSavedJobsDates = $$(By.xpath("//span[contains(@data-ng-bind, 'msg(')]/following::span[1]"));

    //TODO refactor waiting policy
    public String getSavedJobsAlertText() throws InterruptedException {
        logInfo("Copping Saved Jobs Alert text");
        waitForElementToBeActive(txtSavedJobsAlert);
        //txtSavedJobsAlert text contains ? before the number of saved jobs is loaded
        if(txtSavedJobsAlert.getText().contains("?")){
            Thread.sleep(1000);
        }
        logInfo("Saved Saved Jobs Alert text is: " + txtSavedJobsAlert.getText());
        return txtSavedJobsAlert.getText();
    }

    public void waitForSavedJobListToLoad(int listSize){
        logInfo("Waiting for Saved Jobs List to load");
        getWebDriverWait().until(ExpectedConditions.numberOfElementsToBe(txtSavedJobsNamesBy, listSize));
    }

    public List<JobOffer> getSavedJobOffers() {
        logInfo("Copping Saved Job Offers details");
        List<JobOffer> savedJobOffers = new ArrayList<>();
        for(int i = 0 ; i < txtSavedJobsNames.size(); i++){
            JobOffer savedJobOffer = new JobOffer();
            savedJobOffer.setJobName(txtSavedJobsNames.get(i).getText());
            savedJobOffer.setCompanyName(txtSavedJobsCompanyNames.get(i).getText());
            savedJobOffer.setLocation(txtSavedJobsLocations.get(i).getText());
            savedJobOffer.setSaveDate(txtSavedJobsDates.get(i).getText());
            savedJobOffers.add(savedJobOffer);
        }
        Collections.reverse(savedJobOffers);
        return savedJobOffers;
    }
}
