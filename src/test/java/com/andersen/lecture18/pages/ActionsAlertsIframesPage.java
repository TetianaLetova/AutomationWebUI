package com.andersen.lecture18.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsAlertsIframesPage extends BasePage {

    @FindBy(css = "iframe[title='Finish your registration']")
    private WebElement iframe;

    @FindBy(css = "#AlertButton")
    private WebElement alertButton;

    @FindBy(xpath = "//button[text()='Get Discount']")
    private WebElement discountButton;

    @FindBy(xpath = "//button[text()='Cancel course']")
    private WebElement cancelCourseButton;

    @FindBy(xpath = "//span[contains(text(), 'Your course application has been cancelled. Reason:')]")
    private WebElement successMessage;

    public ActionsAlertsIframesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void switchToIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }

    public void clickAlertButton() {
        alertButton.click();
    }

    public void handleAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void doubleClickDiscountButton() {
        Actions actions = new Actions(driver);
        actions.doubleClick(discountButton).perform();
    }

    public void rightClickCancelCourseButton() {
        Actions actions = new Actions(driver);
        actions.contextClick(cancelCourseButton).perform();
    }

    public void handlePromptAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }
}