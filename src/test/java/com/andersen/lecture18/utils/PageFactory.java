package com.andersen.lecture18.utils;

import com.andersen.lecture18.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {
    private WebDriver driver;
    private WebDriverWait wait;

    public PageFactory(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver, wait);
    }

    public RegistrationPage getRegistrationPage() {
        return new RegistrationPage(driver, wait);
    }

    public DashboardPage getDashboardPage() {
        return new DashboardPage(driver, wait);
    }

    public ActionsAlertsIframesPage getActionsAlertsIframesPage() {
        return new ActionsAlertsIframesPage(driver, wait);
    }

    public DragAndDropPage getDragAndDropPage() {
        return new DragAndDropPage(driver, wait);
    }

    public SelectPage getSelectPage() {
        return new SelectPage(driver, wait);
    }

    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(driver, wait);
    }
}