package saving_jobs_process_tests;

import enums.SavedJobsPageWordings;
import models.JobOffer;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.TestDataGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Reporter.createTest;
import static utils.Reporter.logInfo;
import static utils.UrlUtils.isCurrentUrlEqualTo;

public class SavingJobOffersProcessTests extends TestBase{
    private List<JobOffer> selectedJobOffers;

    @Test
    public void openHomePage(){
        createTest("Open home page");
        logInfo("Opening: " + HomePage.URL);
        open(HomePage.URL);
        logInfo("Validating that URL is: " + HomePage.URL);
        assertEquals(url(), HomePage.URL);
    }

    @Test(dependsOnMethods = "openHomePage")
    public void clickCreateAnAccountButton(){
        createTest("Select Create An Account Button");
        HomePage homePage = new HomePage();
        homePage.clickCreateAccountButton();
        logInfo("Validating that URL is: " + AccountLitePage.URL);
        assertTrue(isCurrentUrlEqualTo(AccountLitePage.URL));
    }

    @Test(dependsOnMethods = "clickCreateAnAccountButton")
    public void createNewAccount() {
        createTest("Create new account");
        TestDataGenerator generator = new TestDataGenerator();
        String email = generator.generateRandomUniqueEmail();
        String password = generator.generateRandomPassword();
        AccountLitePage accountLitePage = new AccountLitePage();
        accountLitePage.inputEmail(email);
        accountLitePage.inputPassword(password);
        accountLitePage.reEnterPassword(password);
        accountLitePage.selectRandomJobCorpsCenter();
        accountLitePage.acceptTermsAndConditions();
        accountLitePage.clickCreateAnAccountButton();
        logInfo("Validating that URL is: " + DashboardPage.URL);
        assertTrue(isCurrentUrlEqualTo(DashboardPage.URL));
    }

    @Test(dependsOnMethods = "createNewAccount")
    public void selectPhilipsJobsFromDashboardPage(){
        createTest("Select Philips Jobs from Dashboard page");
        String url = SearchPage.BASE_URL + "Philips";
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPhilipsJobsButton();
        logInfo("Validating that URL is: " + url);
        assertTrue(isCurrentUrlEqualTo(url));
    }

    @Test(dependsOnMethods = "selectPhilipsJobsFromDashboardPage")
    public void saveSelectedJobOffers() throws InterruptedException {
        createTest("Save selected jobs");
        SearchPage searchPage = new SearchPage();
        selectedJobOffers = new ArrayList<>();
        searchPage.scrollToOfferNumberOnTheList(2);
        selectedJobOffers.add(searchPage.getSelectedJobOfferDetails());
        searchPage.clickSaveButton();
        searchPage.scrollToTheLastOfferOnTheList();
        selectedJobOffers.add(searchPage.getSelectedJobOfferDetails());
        searchPage.clickSaveButton();
        searchPage.selectSavedJobsFromMyJobSearchMenu();
        logInfo("Validating that URL is: " + SavedJobsPage.URL);
        assertTrue(isCurrentUrlEqualTo(SavedJobsPage.URL));
    }

    @Test(dependsOnMethods = "saveSelectedJobOffers")
    public void validateSavedJobs() throws InterruptedException {
        createTest("Validate Saved Jobs");
        SavedJobsPage savedJobsPage = new SavedJobsPage();
        savedJobsPage.waitForSavedJobListToLoad(selectedJobOffers.size());
        List<JobOffer> savedJobOffers = savedJobsPage.getSavedJobOffers();
        SoftAssert softAssert = new SoftAssert();
        logInfo("Validating Saved Jobs Alert text");
        softAssert.assertEquals(savedJobsPage.getSavedJobsAlertText(), SavedJobsPageWordings.AMOUNT_OF_SAVED_JOBS.getWording(selectedJobOffers.size()));

        logInfo("Validating Saved Jobs Offers details");
        softAssert.assertEquals(savedJobOffers.get(0).getJobName(), selectedJobOffers.get(0).getJobName());
        softAssert.assertEquals(savedJobOffers.get(0).getCompanyName(), selectedJobOffers.get(0).getCompanyName());
        softAssert.assertEquals(savedJobOffers.get(0).getLocation(), selectedJobOffers.get(0).getLocation());
        softAssert.assertEquals(savedJobOffers.get(0).getSaveDate(), selectedJobOffers.get(0).getSaveDate());

        softAssert.assertEquals(savedJobOffers.get(1).getJobName(), selectedJobOffers.get(1).getJobName());
        softAssert.assertEquals(savedJobOffers.get(1).getCompanyName(), selectedJobOffers.get(1).getCompanyName());
        softAssert.assertEquals(savedJobOffers.get(1).getLocation(), selectedJobOffers.get(1).getLocation());
        softAssert.assertEquals(savedJobOffers.get(1).getSaveDate(), selectedJobOffers.get(1).getSaveDate());
        softAssert.assertAll();
    }

}
