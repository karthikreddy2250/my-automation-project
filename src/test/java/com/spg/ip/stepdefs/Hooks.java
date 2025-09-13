package com.spg.ip.stepdefs;

import com.spg.ip.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before
    public void setUp() {
        System.setProperty("driver","Chrome");
        System.out.println("Setup - launching browser");
        DriverFactory.getDriver();
    }


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed_Screenshot");
        }
        System.out.println("TearDown - closing browser");
        DriverFactory.quitDriver();
    }
}
