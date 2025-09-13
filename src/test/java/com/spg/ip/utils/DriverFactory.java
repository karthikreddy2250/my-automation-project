package com.spg.ip.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverFactory {

    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        if (webDriver == null) {
            if (System.getProperty("driver").equalsIgnoreCase("chrome")) {
                webDriver = new ChromeDriver();
                return webDriver;
            }
        }
        if (System.getProperty("driver").equalsIgnoreCase("headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            webDriver = new ChromeDriver(options);
            return webDriver;
        }
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
