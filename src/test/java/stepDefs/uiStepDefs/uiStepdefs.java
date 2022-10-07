package stepDefs.uiStepDefs;

import API.dataProviders.ConfigReader;
import UI.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;;
import stepDefs.BaseTest;




public class uiStepdefs extends BaseTest {

    @Given("login with given credentials as admin")
    public void loginWithGivenCredentialsAsAdmin() {
        Driver.getDriver().get(ConfigReader.getProperty("environment"));
        loginPage.loginToSystem(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"));
    }

    @Then("admin should see admin-index page")
    public void adminShouldSeeAdminIndexPage() {
        assertClass.assertTextWithText(ConfigReader.getProperty("indexPageUrl"), Driver.getDriver().getCurrentUrl());
        indexPage.waitPageToBeLoaded();
    }

    @When("admin clicks on players button")
    public void adminClicksOnPlayersButton() {
        indexPage.clickButtonUsers().clickButtonPlayers();
    }

    @Then("admin should see Player Management page")
    public void adminShouldSeePlayerManagementPage() {
        assertClass.assertTextWithGetText("Player management", playersPage.playersPageText);
    }

    int index;

    @When("admin click to order button")
    public void adminClickToOrderButton() {
        index = playersPage.clickOrderButton();
    }


    @Then("order by column and check the sorting")
    public void orderByColumnAndCheckTheSorting() {
        assertClass.assertTextWithText("---------","---------");
        assertClass.assertTextWithText("10015e6f7e109a9cd","10015e6f7e109a9cd");
         assertClass.assertTextWithText(" "," ");
        assertClass.assertTextWithText(" "," ");
         assertClass.assertTextWithText(" "," ");
         assertClass.assertTextWithText(" "," ");
         assertClass.assertTextWithText("[#2] Hall","[#2] Hall");
        assertClass.assertTextWithText("2019-05-13 10:00:52","2019-05-13 10:00:52");


        Driver.closeDriver();
    }
}

