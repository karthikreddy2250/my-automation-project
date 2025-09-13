package com.spg.ip.pages;


import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    private static final String welcomeUrl = "https://the-internet.herokuapp.com/";

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/");
    }

    public void validateWelcomeText() {
        webDriver.get(welcomeUrl);
    }

    public void tearDown() {
        webDriver.quit();
    }
}
