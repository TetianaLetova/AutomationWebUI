package com.andersen.lecture18.tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Registration")
public class ValidationTests extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ValidationTests.class);

    @Story("Invalid date format registration")
    @Description("Verify that registration works with an invalid date format")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC11: Verify registration with invalid date format")
    public void testRegistrationWithInvalidDate() {
        logger.info("Navigate to Registration page");
        navigateToRegistration();

        logger.info("Fill registration form with invalid date");
        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/3000",
                "test" + System.currentTimeMillis() + "@mail.com",
                "Pass123!",
                "Pass123!"
        );

        logger.info("Submit registration form");
        registrationPage.clickSubmit();

        Assert.assertTrue(
                registrationPage.isSuccessMessageDisplayed() ||
                        wait.until(ExpectedConditions.urlContains("/login")),
                "Registration should be successful with redirect to Sign In or success message"
        );
        logger.info("Registration with invalid date passed");
    }

    @Story("Password validation")
    @Description("Verify that registration enforces minimum password length")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC12: Verify registration password minimum length validation")
    public void testRegistrationWithShortPassword() {
        logger.info("Navigate to Registration page");
        navigateToRegistration();

        logger.info("Fill registration form with short password");
        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "test" + System.currentTimeMillis() + "@mail.com",
                "123",
                "123"
        );

        logger.info("Submit registration form");
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isPasswordLengthErrorDisplayed(),
                "Error message 'Password must be at least 6 characters' should be displayed");
        logger.info("Password length validation error displayed as expected");
    }

    @Story("Sign In page UI")
    @Description("Verify visibility of Sign In page elements")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC13: Verify Sign In page elements visibility")
    public void testSignInPageElementsVisibility() {
        logger.info("Navigate to Login page");
        navigateToLogin();

        Assert.assertTrue(loginPage.areAllElementsVisible(),
                "Email, Password, 'Sign In' button, and 'Registration' link should be visible");
        logger.info("Sign In page elements are visible");
    }

    @Story("Registration page UI")
    @Description("Verify visibility of Registration page elements")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC14: Verify Registration page elements visibility")
    public void testRegistrationPageElementsVisibility() {
        logger.info("Navigate to Registration page");
        navigateToRegistration();

        Assert.assertTrue(registrationPage.areAllElementsVisible(),
                "First Name, Last Name, Date of Birth, Email, Password, Confirm Password fields and Submit button should be visible");
        logger.info("Registration page elements are visible");
    }

    @Story("Navigation flow")
    @Description("Verify navigation from Registration page back to Sign In page")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC15: Verify navigation back to Sign In page from Registration")
    public void testNavigationBackToSignInFromRegistration() {
        logger.info("Navigate to Registration page");
        navigateToRegistration();

        logger.info("Click Sign In link");
        registrationPage.clickSignInLink();

        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "User should be redirected back to Sign In page");
        Assert.assertTrue(loginPage.areAllElementsVisible(),
                "Sign In page should load without errors");
        logger.info("Navigation back to Sign In page verified");
    }
}
