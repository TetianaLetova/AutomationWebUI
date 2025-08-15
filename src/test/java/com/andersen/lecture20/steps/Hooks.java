package com.andersen.lecture20.steps;

import com.andersen.lecture18.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({AllureTestNg.class})
public class Hooks {

    public WebDriver driver;
    public WebDriverWait wait;
    public final String baseUrl = "https://qa-course-01.andersenlab.com/";

    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public DashboardPage dashboardPage;
    public ActionsAlertsIframesPage actionsAlertsIframesPage;
    public DragAndDropPage dragAndDropPage;
    public SelectPage selectPage;
    public SearchResultsPage searchResultsPage;

    public static final Logger logger = LoggerFactory.getLogger(com.andersen.lecture18.tests.BaseTest.class);

    @Before
    public void setUp() {
        logger.info("Setting up WebDriver and test environment...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        initializePageObjects();
        logger.info("Test environment setup completed.");
    }

    @Step("Initialize page objects")
    public void initializePageObjects() {
        loginPage = new LoginPage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
        actionsAlertsIframesPage = new ActionsAlertsIframesPage(driver, wait);
        dragAndDropPage = new DragAndDropPage(driver, wait);
        selectPage = new SelectPage(driver, wait);
        searchResultsPage = new SearchResultsPage(driver, wait);
        logger.debug("Page objects initialized.");
    }

    @Step("Navigate to Login page")
    public void navigateToLogin() {
        logger.info("Navigating to Login page...");
        driver.get(baseUrl + "login");
    }

    @Step("Navigate to Registration page")
    public void navigateToRegistration() {
        logger.info("Navigating to Registration page...");
        driver.get(baseUrl + "registration");
    }

    @Step("Login as a valid user")
    public void loginAsValidUser() {
        logger.info("Logging in as a valid user...");
        navigateToLogin();
        loginPage.login("tetianaletova@gmail.com", "adfdtcfjgkhl557#G");
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        logger.info("Login successful.");
    }

    @After
    public void tearDown() {
        logger.info("Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        logger.info("Test environment closed.");
    }
}
