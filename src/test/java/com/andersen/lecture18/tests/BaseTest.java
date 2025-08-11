package com.andersen.lecture18.tests;

import com.andersen.lecture18.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        initializePageObjects();
    }

    @Step("Initialize page objects")
    protected void initializePageObjects() {
        loginPage = new LoginPage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
        actionsAlertsIframesPage = new ActionsAlertsIframesPage(driver, wait);
        dragAndDropPage = new DragAndDropPage(driver, wait);
        selectPage = new SelectPage(driver, wait);
        searchResultsPage = new SearchResultsPage(driver, wait);
    }

    @Step("Navigate to Login page")
    protected void navigateToLogin() {
        driver.get(baseUrl + "login");
        logger.info("Navigate to login page");
    }

    @Step("Navigate to Registration page")
    protected void navigateToRegistration() {
        driver.get(baseUrl + "registration");

    }

    @Step("Login as a valid user")
    protected void loginAsValidUser() {
        navigateToLogin();
        loginPage.login("tetianaletova@gmail.com", "adfdtcfjgkhl557#G");
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        logger.info("User successfully log in");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}