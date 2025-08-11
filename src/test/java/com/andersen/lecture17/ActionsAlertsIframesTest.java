package com.andersen.lecture17;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsAlertsIframesTest extends BaseTest {

    @Test
    public void testActionsAlertsIframes() {
        login();

        WebElement expandIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Expand']"))
        );
        expandIcon.click();

        WebElement actionsAlertsIframes = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Actions, Alerts & Iframes')]"))
        );
        actionsAlertsIframes.click();

        wait.until(ExpectedConditions.urlContains("/actions"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement iframe = driver.findElement(By.cssSelector("iframe[title='Finish your registration']"));
        driver.switchTo().frame(iframe);
        WebElement alertButton = driver.findElement(By.cssSelector("#AlertButton"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement discountButton = driver.findElement(By.xpath("//button[text()='Get Discount']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(discountButton).perform();

        Alert secondAlert = driver.switchTo().alert();
        secondAlert.accept();

        WebElement cancelCourseButton = driver.findElement(By.xpath("//button[text()='Cancel course']"));
        actions.contextClick(cancelCourseButton).perform();
        Alert thirdAlert = driver.switchTo().alert();
        thirdAlert.sendKeys("Test");
        thirdAlert.accept();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Your course application has been cancelled. Reason: Test')]")));

        Assert.assertTrue(successMessage.isDisplayed(),
                "The cancellation message with Test reason should be visible");

    }
}