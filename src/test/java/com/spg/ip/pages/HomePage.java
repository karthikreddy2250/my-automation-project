package com.spg.ip.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private static final String welcomeUrl = "https://the-internet.herokuapp.com/";


    public void openTheUrl() {
        webDriver.get(welcomeUrl);
    }

    public String getWelcomeText() {
        return webDriver.findElement(By.className("heading")).getText();
    }

    public void enterBasicAuthCredentials(String username, String password) throws InterruptedException {
        webDriver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
    }


    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/p")));
        return element.getText();
    }

    public void clickOnBrokenImagesLink() {
        webDriver.findElement(By.partialLinkText("Broken")).click();
    }


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


    public void clickOnChallengingDOMLink() {
        webDriver.findElement(By.linkText("Challenging DOM")).click();
    }

    public List<WebElement> getTableHeaderValues() throws IOException {
        List<WebElement> tableHeaders = webDriver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table/thead/tr/th"));
        return tableHeaders;
    }


    public void clickOnCheckboxesLink() {
        webDriver.findElement(By.linkText("Checkboxes")).click();
    }


    public void checkABox() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
    }

}
