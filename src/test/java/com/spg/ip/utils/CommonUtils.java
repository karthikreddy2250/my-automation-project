package com.spg.ip.utils;

import org.openqa.selenium.WebDriver;

public class CommonUtils {

    private static WebDriver webDriver = DriverFactory.getDriver();

    public static void clickOnBrowserBackButton() {
        webDriver.navigate().back();
    }
}
