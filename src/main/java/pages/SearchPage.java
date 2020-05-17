package pages;

import com.codeborne.selenide.SelenideElement;
import enums.URLs;
import models.JobOffer;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static utils.Reporter.logInfo;

public class SearchPage extends BasePage{
    public static final String BASE_URL = URLs.BROWSE_MONSTER_SEARCH_URL.getUrl();

    private SelenideElement scrollResults = $(By.className("mux-search-results"));
    private List<SelenideElement> resultSectionsList = $$(By.xpath("//div[@id= 'SearchResults']/section"));
    private SelenideElement btnSave = $(By.id("SaveJob"));
    private SelenideElement txtSelectedJobTitle = $(By.xpath("//section[@class = 'card-content is-active']//h2[@class = 'title']"));
    private SelenideElement txtSelectedJobCompanyName = $(By.xpath("//section[@class = 'card-content is-active']//div[@class= 'company']/*[@class = 'name']"));
    private SelenideElement txtSelectedJobLocation = $(By.xpath("//section[@class = 'card-content is-active']//div[@class= 'location']/*[@class = 'name']"));

    public int getResultSectionsListSize(){
        waitForElementToBeActive(scrollResults);
        return Integer.parseInt(scrollResults.getAttribute("data-total-search-results"));
    }

    //TODO add mechanism to avoid potential endless loop
    public void scrollToOfferNumberOnTheList(int indexToSelect) throws InterruptedException {
        logInfo("Scrolling to job offer with number: " + indexToSelect);
        indexToSelect--;
        waitForElementToBeActive(scrollResults);
        int currentIndex = 0;
        while(currentIndex <= indexToSelect){
            javaScriptExecutorScrollToElement(resultSectionsList.get(currentIndex));
            Thread.sleep(300);
            currentIndex += 3;
        }
            resultSectionsList
                    .get(indexToSelect)
                    .click();
    }

    //this method i workaround. It seems that that number jobs on the list is not equal to written value. It is written that there are 272 jobs but there are 252 on the list
    //after fix this method should be removed and scrollToOfferNumberOnTheList should be used
    public void scrollToTheLastOfferOnTheList() throws InterruptedException {
        logInfo("Scrolling to the last job offer");
        waitForElementToBeActive(scrollResults);
        int indexToSelect = getResultSectionsListSize() - 1;
        waitForElementToBeActive(scrollResults);
        int currentIndex = 0;
        try{
            while(currentIndex <= indexToSelect){
                javaScriptExecutorScrollToElement(resultSectionsList.get(currentIndex));
                Thread.sleep(300);
                currentIndex += 3;
            }
        }
        catch (Exception ignored){

        }
        resultSectionsList
                .get(resultSectionsList.size() - 1)
                .click();
    }

    public JobOffer getSelectedJobOfferDetails(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.US);
        LocalDateTime now = LocalDateTime.now();

        JobOffer jobOffer = new JobOffer();
        logInfo("Selected job company name is: " + txtSelectedJobCompanyName.getText());
        jobOffer.setCompanyName(txtSelectedJobCompanyName.getText());
        logInfo("Selected job title is: " + txtSelectedJobTitle.getText());
        jobOffer.setJobName(txtSelectedJobTitle.getText());
        logInfo("Selected job location is: " + txtSelectedJobLocation.getText());
        jobOffer.setLocation(txtSelectedJobLocation.getText());
        logInfo("Selected job save date is: " + dtf.format(now));
        jobOffer.setSaveDate(dtf.format(now));
        return jobOffer;
    }

    public void clickSaveButton(){
        logInfo("Clicking save button");
        click(btnSave);
    }
}
