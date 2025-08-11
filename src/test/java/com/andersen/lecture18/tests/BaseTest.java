package com.andersen.lecture18.tests;

import com.andersen.lecture18.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "https://qa-course-01.andersenlab.com/";
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected DashboardPage dashboardPage;
    protected ActionsAlertsIframesPage actionsAlertsIframesPage;
    protected DragAndDropPage dragAndDropPage;
    protected SelectPage selectPage;
    protected SearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        initializePageObjects();
    }

    protected void initializePageObjects() {
        loginPage = new LoginPage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
        actionsAlertsIframesPage = new ActionsAlertsIframesPage(driver, wait);
        dragAndDropPage = new DragAndDropPage(driver, wait);
        selectPage = new SelectPage(driver, wait);
        searchResultsPage = new SearchResultsPage(driver, wait);
    }

    protected void navigateToLogin() {
        driver.get(baseUrl + "login");
    }

    protected void navigateToRegistration() {
        driver.get(baseUrl + "registration");
    }

    protected void loginAsValidUser() {
        navigateToLogin();
        loginPage.login("tetianaletova@gmail.com", "adfdtcfjgkhl557#G");
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}