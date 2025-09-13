package com.spg.ip.stepdefs;

import com.spg.ip.pages.HomePage;
import com.spg.ip.utils.CommonUtils;
import com.spg.ip.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageStepDefs {

    private WebDriver webDriver = DriverFactory.getDriver();
    private HomePage homePage;

    public HomePageStepDefs() {
        homePage = new HomePage(webDriver);
    }


    @When("I open the url")
    public void i_open_the_url() {
        homePage.openTheUrl();
    }

    @And("validate the welcome text {string}")
    public void validate_the_welcome_text(String expectedText) {
        String actualText = homePage.getWelcomeText();
        Assertions.assertEquals(expectedText, actualText);
    }


    @And("enter the basic auth credentials {string} and {string}")
    public void enter_the_basic_auth_credentials_and(String username, String password) throws InterruptedException {
        homePage.enterBasicAuthCredentials(username, password);
    }

    @Then("validate the success message {string}")
    public void validate_the_success_message(String expectedText) {
        String successMessage = homePage.getSuccessMessage();
        Assertions.assertEquals(expectedText, successMessage);
    }

    @Then("click on back")
    public void click_On_Back() {
        CommonUtils.clickOnBrowserBackButton();
    }

    @Then("click on Broken Images link")
    public void click_On_Broken_Images_Link() {
        homePage.clickOnBrokenImagesLink();
    }


    @Then("check if the images loaded or not")
    public void check_If_The_Images_Loaded_Or_Not() {
        homePage.checkIfTheImagesLoadedOrNot();
    }


    @Then("click on Challenging DOM")
    public void click_On_Challenging_DOM() {
        homePage.clickOnChallengingDOMLink();
    }

    @Then("validate the table header")
    public void validate_The_Table_Header() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/table.csv"));
        List<String> expectedHeaderValues = new ArrayList<>(List.of(bufferedReader.readLine().split(",")));
        List<WebElement> actualTableHeaderValues = homePage.getTableHeaderValues();
        for (int i = 0; i < actualTableHeaderValues.size(); i++) {
            Assertions.assertEquals(expectedHeaderValues.get(i), actualTableHeaderValues.get(i).getText());
        }
    }

    @Then("validate the table rows")
    public void validateTheTableRows() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/table.csv"));
        bufferedReader.readLine();//skipping the first line
        List<WebElement> rows = webDriver.findElements(By.cssSelector("table tbody tr"));
        for (int i = 0; i < rows.size(); i++) {
            String expectedValues = bufferedReader.readLine().replace(",", " ");
            Assertions.assertEquals(expectedValues, rows.get(i).getText());
        }
    }

    @Then("click on Checkboxes link")
    public void click_On_Checkboxes_Link() {
        homePage.clickOnCheckboxesLink();
    }

    @Then("check a box")
    public void check_A_Box() throws InterruptedException {
        homePage.checkABox();
    }

}
