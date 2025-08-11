package com.andersen.lecture17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {

    @Test
    public void dragAndDropTest() {
        WebElement expandIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Expand']"))
        );
        expandIcon.click();

        WebElement dragAndDropBtn = wait.until(driver -> driver.findElement(By.xpath("//div[contains(text(),'Drag & Drop')]")));
        dragAndDropBtn.click();

        wait.until(driver -> driver.getCurrentUrl().contains("/dragndrop"));

        Actions actions = new Actions(driver);

        WebElement manual1 = wait.until(driver -> driver.findElement(By.id("manual1")));
        WebElement manual1Target = driver.findElement(By.id("target-manual1"));

        WebElement manual2 = driver.findElement(By.id("manual2"));
        WebElement manual2Target = driver.findElement(By.id("target-manual2"));

        WebElement auto1 = driver.findElement(By.id("auto1"));
        WebElement auto1Target = driver.findElement(By.id("target-auto1"));

        WebElement auto2 = driver.findElement(By.id("auto2"));
        WebElement auto2Target = driver.findElement(By.id("target-auto2"));

        actions.dragAndDrop(manual1, manual1Target).perform();
        actions.dragAndDrop(manual2, manual2Target).perform();
        actions.dragAndDrop(auto1, auto1Target).perform();
        actions.dragAndDrop(auto2, auto2Target).perform();

        WebElement successMessage = wait.until(driver ->
                driver.findElement(By.xpath("//div[contains(text(),\"Congratulations! Let's test for the best!\")]")));

        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");
    }
}
