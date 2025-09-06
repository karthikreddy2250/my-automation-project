package com.spg.ip.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HomePage {

    private WebDriver webDriver;

    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/");
    }

    public void validateWelcomeText() {
        System.out.println("Entered the first step def");
        String welcomeText = webDriver.findElement(By.className("heading")).getText();
//        Assert.assertEquals("Welcome to the-internet", welcomeText);
    }

    public void tearDown() {
        webDriver.quit();
    }
}
