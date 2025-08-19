package com.andersen.lecture21;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MobileTestDef {

    private final AppiumDriver driver;

    public MobileTestDef(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @Given("the ApiDemos application is opened")
    public void theApiDemosApplicationIsOpened() {
        Assert.assertNotNull(driver);
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String menuItem) {
        try {
            driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"" + menuItem + "\" or @text=\"" + menuItem + "\"]")).click();
        } catch (NoSuchElementException e) {
            scrollToElement(menuItem);
        }
    }

    @And("I set the date to tomorrow")
    public void iSetTheDateToTomorrow() {
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"change the date\"]")).click();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        String tomorrowContentDesc = tomorrow.format(formatter);
        if (tomorrow.getMonthValue() != today.getMonthValue()) {
            try {
                driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Next month\"]")).click();
                Thread.sleep(500);
            } catch (Exception ignored) {}
        }
        try {
            driver.findElement(By.xpath("//android.view.View[@content-desc=\"" + tomorrowContentDesc + "\"]")).click();
        } catch (NoSuchElementException e1) {
            String dayText = String.valueOf(tomorrow.getDayOfMonth());
            driver.findElement(By.xpath("//*[contains(@content-desc, '" + dayText + "') or @text='" + dayText + "']")).click();
        }
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
    }

    @And("I set the time to {string}")
    public void iSetTheTimeTo(String time) {
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"change the time (spinner)\"]")).click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        String[] parts = time.split(" ");
        String[] hm = parts[0].split(":");
        String hour = hm[0];
        String minute = hm[1];
        String ampm = parts[1];
        try {
            WebElement hourField = driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'hour')]"));
            hourField.clear();
            hourField.sendKeys(hour);
            WebElement minuteField = driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'minute')]"));
            minuteField.clear();
            minuteField.sendKeys(minute);
            driver.findElement(By.xpath("//*[@content-desc=\"" + ampm + "\"] | //android.widget.TextView[@text=\"" + ampm + "\"]")).click();
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
        } catch (Exception ignored) {}
    }

    @And("I return to {string}")
    public void iReturnTo(String menuItem) {
        while (true) {
            driver.navigate().back();
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            List<WebElement> topMenu = driver.findElements(By.xpath("//android.widget.TextView[@text='" + menuItem + "' or @content-desc='" + menuItem + "']"));
            if (!topMenu.isEmpty()) {
                topMenu.get(0).click();
                break;
            }
        }
    }

    @And("I press the {string} button {int} times")
    public void iPressTheButtonTimes(String buttonName, int times) {
        By locator = By.xpath("//android.widget.Button[@content-desc=\"" + buttonName + "\"]");
        for (int i = 0; i < times; i++) {
            try {
                driver.findElement(locator).click();
                Thread.sleep(200);
            } catch (Exception ignored) {}
        }
    }

    @Then("the text field should display {string}")
    public void theTextFieldShouldDisplay(String expectedText) {
        try {
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + expectedText + "\"]"));
            Assert.assertEquals(element.getText(), expectedText);
        } catch (NoSuchElementException e) {
            List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView"));
            boolean found = elements.stream().anyMatch(el -> expectedText.equals(el.getText()));
            Assert.assertTrue(found, "Expected text '" + expectedText + "' not found on screen");
        }
    }

    private void scrollToElement(String elementText) {
        boolean found = false;
        int maxScrollAttempts = 5;
        WebElement scrollArea = driver.findElement(By.id("android:id/decor_content_parent"));

        for (int i = 0; i < maxScrollAttempts; i++) {
            List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView[@content-desc='" + elementText + "']"));
            if (!elements.isEmpty()) {
                elements.get(0).click();
                found = true;
                break;
            } else {
                Map<String, Object> args = Map.of(
                        "elementId", ((RemoteWebElement) scrollArea).getId(),
                        "direction", "down",
                        "percent", 0.7
                );
                driver.executeScript("mobile: scrollGesture", args);
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }

        if (!found) {
            throw new RuntimeException("Element '" + elementText + "' not found after scrolling");
        }
    }
}
