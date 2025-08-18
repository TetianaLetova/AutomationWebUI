package com.andersen.lecture21;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {

    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .noReset();

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
