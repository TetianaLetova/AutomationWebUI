package com.andersen.lecture21;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MobileTestDef {

    private final AppiumDriver driver;

    public MobileTestDef(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @Given("the ApiDemos application is opened")
    public void theApiDemosApplicationIsOpened() {
        WebElement title = driver.findElement(By.id("android:id/action_bar"));
        Assert.assertTrue(title.isDisplayed(), "ApiDemos app did not open");
    }

    @Then("I should see {int} navigation buttons on the screen")
    public void iShouldSeeNavigationButtonsOnTheScreen(int expectedCount) {
        List<WebElement> buttons = driver.findElements(By.id("android:id/list"));
        long displayedCount = buttons.stream().filter(WebElement::isDisplayed).count();
        Assert.assertEquals(displayedCount, expectedCount, "Unexpected number of navigation buttons");
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String menuItem) {
        driver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc=\"%s\"]", menuItem))).click();
    }

    @And("I set the date to tomorrow")
    public void iSetTheDateToTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int day = tomorrow.getDayOfMonth();

        driver.findElement(By.id("io.appium.android.apis:id/pickDate")).click();
        driver.findElement(By.xpath("//android.view.View[@text='" + day + "']")).click();
        driver.findElement(By.id("android:id/button1")).click();
    }

    @And("I set the time to {string}")
    public void iSetTheTimeTo(String timeString) {
        driver.findElement(By.id("io.appium.android.apis:id/pickTime")).click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        var localTime = java.time.LocalTime.parse(timeString, formatter);

        WebElement hourInput = driver.findElement(By.id("android:id/input_hour"));
        hourInput.clear();
        hourInput.sendKeys(String.valueOf(localTime.getHour() % 12));

        WebElement minuteInput = driver.findElement(By.id("android:id/input_minute"));
        minuteInput.clear();
        minuteInput.sendKeys(String.format("%02d", localTime.getMinute()));

        String amPm = localTime.getHour() >= 12 ? "PM" : "AM";
        WebElement amPmInput = driver.findElement(By.id("android:id/am_pm_spinner"));
        amPmInput.sendKeys(amPm);

        driver.findElement(By.id("android:id/button1")).click();
    }

    @Then("the selected date should be tomorrow")
    public void theSelectedDateShouldBeTomorrow() {
        String actual = driver.findElement(By.id("io.appium.android.apis:id/dateDisplay")).getText();
        String expectedDay = String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());
        Assert.assertTrue(actual.contains(expectedDay), "Date is not set to tomorrow. Found: " + actual);
    }

    @And("the selected time should be {string}")
    public void theSelectedTimeShouldBe(String expected) {
        String actual = driver.findElement(By.id("io.appium.android.apis:id/timeDisplay")).getText();
        Assert.assertEquals(actual, expected, "Time was not set correctly");
    }

    @And("I press the {string} button {int} times")
    public void iPressTheButtonTimes(String buttonText, int times) {
        for (int i = 0; i < times; i++) {
            driver.findElement(By.xpath("//android.widget.Button[@text='" + buttonText + "']")).click();
        }
    }

    @Then("the text field should display {string}")
    public void theTextFieldShouldDisplay(String expectedText) {
        String actual = driver.findElement(By.id("io.appium.android.apis:id/switcher")).getText();
        Assert.assertEquals(actual, expectedText, "TextSwitcher value mismatch");
    }
}
