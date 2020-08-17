package stepdefs;

import base.BaseUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pa;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Pageawardlist;

import java.io.IOException;

public class StepDefinitions {
public Pageawardlist award=new Pageawardlist();

    public StepDefinitions() throws IOException {
    }


    @Given("^user opens the DBS URL$")
    public void UserOpensTheDBSURL()
    {
       award.setup();
    }

    @When("^user clicks on Learn More button$")
    public void userClicksOnLearnMoreButton() {
      award.learnmorebtn();
    }



    @Then("^verify table is copied to excel$")
    public void verifyTableIsCopiedToExcel() throws IOException {
     award.writetabletoExcel();
    award.ReadExcel();
    award.clearexcel();
    }



    @Then("^user clicks on About from menu$")
    public void userClicksOnAboutFromMenu() throws Throwable {
      award.clickmenu();
    }




    @And("^user validates awards")
    public void userValidates() throws Throwable {
    award.awards();
    }

    @And("^user clicks on Who we are from submenu$")
    public void userClicksOnWhoWeAreFromSubmenu() {
        award.clicksubmenu();
    }

    @And("^user scrolls down to select the Country$")
    public void userScrollsDownToSelectTheCountry() {
        award.selectcountry();
    }

   @And("^user verifies table with award names created in report$")
    public void userVerifiesTableWithAwardNamesCreatedInReport() throws IOException {
      award.reporttable();
    }
}
