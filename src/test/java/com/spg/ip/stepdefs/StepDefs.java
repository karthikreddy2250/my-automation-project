package com.spg.ip.stepdefs;

import com.spg.ip.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StepDefs {

    private static WebDriver webDriver;
    private HomePage homePage;

    public StepDefs() {
    }


    @Before
    public void setUp() {
        System.out.println("I am before");
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        System.out.println("Timeout done");
    }

    @When("I open the url")
    public void i_open_the_url() {
        homePage.validateWelcomeText();
    }

    @And("validate the welcome text {string}")
    public void validate_the_welcome_text(String expectedText) {
        String welcomeText = webDriver.findElement(By.className("heading")).getText();
        Assertions.assertEquals(expectedText, welcomeText);
        homePage.validateWelcomeText();
    }


    @And("enter the basic auth credentials {string} and {string}")
    public void enter_the_basic_auth_credentials_and(String username, String password) throws InterruptedException {
        webDriver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
    }

    @Then("validate the success message {string}")
    public void validate_the_success_message(String expectedText) {
        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/p")));
        String successMessage = element.getText();
        Assertions.assertEquals(expectedText, successMessage);
    }

    @Then("click on back")
    public void clickOnBack() {
        webDriver.navigate().back();
    }

    @Then("click on Broken Images link")
    public void clickOnBrokenImagesLink() {
        webDriver.findElement(By.partialLinkText("Broken")).click();
    }


    @Then("check if the images loaded or not")
    public void checkIfTheImagesLoadedOrNot() {
        List<WebElement> images = webDriver.findElements(By.tagName("img"));

        for (WebElement img : images) {
            String url = img.getAttribute("src");

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println(url + " is a broken image");
                } else {
                    System.out.println(url + " is valid");
                }
            } catch (Exception e) {
                System.out.println(url + " is a broken image (exception: " + e.getMessage() + ")");
            }
        }
    }


    @Then("click on Challenging DOM")
    public void clickOnChallengingDOM() {
        webDriver.findElement(By.linkText("Challenging DOM")).click();
    }

    @Then("validate the table header")
    public void validateTheTableHeader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/table.csv"));
        List<String> actualHeaderValues = new ArrayList<>(List.of(bufferedReader.readLine().split(",")));
        List<WebElement> tableHeaders = webDriver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table/thead/tr/th"));
        for (int i = 0; i < tableHeaders.size(); i++) {
            Assertions.assertEquals(actualHeaderValues.get(i), tableHeaders.get(i).getText());
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
    public void clickOnCheckboxesLink() {
        webDriver.findElement(By.linkText("Checkboxes")).click();
    }

    @Then("check a box")
    public void checkABox() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
        Thread.sleep(5000);
    }

    @After
    public static void tearDown() {
        System.out.println("I am after");
        webDriver.quit();
    }
}
