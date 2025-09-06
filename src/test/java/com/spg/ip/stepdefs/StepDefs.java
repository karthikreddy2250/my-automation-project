package com.spg.ip.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefs {

    public static WebDriver webDriver;

    public StepDefs(){}

    @Before
    public static void setUp() {
        System.out.println("I am before");
        webDriver = new ChromeDriver();
    }

    @When("I open the url")
    public void i_open_the_url() {
        System.out.println("Hi, I am the first step");
        webDriver.get("https://the-internet.herokuapp.com/");
        System.out.println("Hi, I am the first step done");
    }

    @And("validate the welcome text")
    public void validate_the_welcome_text() {
        String welcomeText = webDriver.findElement(By.className("heading")).getText();
        Assertions.assertEquals("Welcome to the-internet", welcomeText);
        System.out.println("Test Successful");
    }


    @After
    public static void tearDown() {
        System.out.println("I am after");
        webDriver.quit();
    }
}
