package com.andersen.lecture18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDropPage extends BasePage {

    @FindBy(id = "manual1")
    private WebElement manual1;

    @FindBy(id = "target-manual1")
    private WebElement manual1Target;

    @FindBy(id = "manual2")
    private WebElement manual2;

    @FindBy(id = "target-manual2")
    private WebElement manual2Target;

    @FindBy(id = "auto1")
    private WebElement auto1;

    @FindBy(id = "target-auto1")
    private WebElement auto1Target;

    @FindBy(id = "auto2")
    private WebElement auto2;

    @FindBy(id = "target-auto2")
    private WebElement auto2Target;

    @FindBy(xpath = "//div[contains(text(),\"Congratulations! Let's test for the best!\")]")
    private WebElement successMessage;

    public DragAndDropPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void performAllDragAndDropActions() {
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(manual1));

        actions.dragAndDrop(manual1, manual1Target).perform();
        actions.dragAndDrop(manual2, manual2Target).perform();
        actions.dragAndDrop(auto1, auto1Target).perform();
        actions.dragAndDrop(auto2, auto2Target).perform();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}