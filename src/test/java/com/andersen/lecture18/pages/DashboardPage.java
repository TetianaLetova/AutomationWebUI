package com.andersen.lecture18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//img[@alt='Expand']")
    private WebElement expandIcon;

    @FindBy(xpath = "//div[contains(text(),'Actions, Alerts & Iframes')]")
    private WebElement actionsAlertsIframesOption;

    @FindBy(xpath = "//div[contains(text(),'Drag & Drop')]")
    private WebElement dragAndDropOption;

    @FindBy(xpath = "//div[contains(@class, 'justify-center') and text()='Select']")
    private WebElement selectOption;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickExpandIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(expandIcon)).click();
    }

    public void clickActionsAlertsIframes() {
        wait.until(ExpectedConditions.elementToBeClickable(actionsAlertsIframesOption)).click();
    }

    public void clickDragAndDrop() {
        wait.until(ExpectedConditions.elementToBeClickable(dragAndDropOption)).click();
    }

    public void clickSelect() {
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
    }

    public boolean isDashboardLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(expandIcon)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}