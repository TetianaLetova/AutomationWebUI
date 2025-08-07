package com.andersen.lecture18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends BasePage {

    @FindBy(tagName = "h2")
    private WebElement resultMessage;

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getResultMessage() {
        return wait.until(ExpectedConditions.visibilityOf(resultMessage)).getText();
    }

    public boolean isResultMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(resultMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}