package com.andersen.lecture18.tests;

import io.qameta.allure.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Login and Registration")
public class LoginAndRegistrationTests extends BaseTest {

    @Story("Login with valid credentials")
    @Description("Verify that user can login successfully with valid email and password")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "TC01: Verify login with valid credentials")
    public void testLoginWithValidCredentials() {
        navigateToLogin();

        logger.info("Logging in with valid credentials");
        loginPage.login("testuser@mail.com", "Pass123!");

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "User should be logged in and redirected to dashboard");
        logger.info("Login successful, dashboard loaded");
    }

    @Story("Login with invalid credentials")
    @Description("Verify error is shown when login with invalid password")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC02: Verify login with invalid password")
    public void testLoginWithInvalidPassword() {
        logger.info("Navigating to Login page");
        navigateToLogin();

        logger.info("Logging in with invalid password");
        loginPage.login("testuser@mail.com", "wrongPass");

        Assert.assertTrue(loginPage.isInvalidCredentialsErrorDisplayed(),
                "Error message 'Email or password is not valid' should be displayed");
        logger.info("Invalid credentials error displayed");
    }

    @Story("Login form validation")
    @Description("Verify error messages when login fields are empty")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC03: Verify login with empty fields")
    public void testLoginWithEmptyFields() {
        logger.info("Navigating to Login page");
        navigateToLogin();

        logger.info("Clicking Sign In with empty fields");
        loginPage.clickSignIn();

        Assert.assertTrue(loginPage.isEmailRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed for email");
        Assert.assertTrue(loginPage.isPasswordRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed for password");
        logger.info("Required field errors displayed");
    }

    @Story("Login form validation")
    @Description("Verify error message when login with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC04: Verify login with invalid email format")
    public void testLoginWithInvalidEmailFormat() {
        logger.info("Navigating to Login page");
        navigateToLogin();

        logger.info("Logging in with invalid email format");
        loginPage.login("abc123", "Pass123!");

        Assert.assertTrue(loginPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email address' should be displayed");
        logger.info("Invalid email format error displayed");
    }

    @Story("Navigation flow")
    @Description("Verify navigation from Login to Registration page")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC05: Verify navigation to Registration page")
    public void testNavigationToRegistrationPage() {
        logger.info("Navigating to Login page");
        navigateToLogin();

        logger.info("Clicking Registration link");
        loginPage.clickRegistrationLink();

        wait.until(ExpectedConditions.urlContains("/registration"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/registration"),
                "User should be redirected to Registration page");
        Assert.assertTrue(registrationPage.areAllElementsVisible(),
                "Registration page elements should be visible");
        logger.info("Registration page loaded successfully");
    }

    @Story("Registration with valid data")
    @Description("Verify user can register with valid data")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "TC06: Verify registration with valid data")
    public void testRegistrationWithValidData() {
        logger.info("Navigating to Registration page");
        navigateToRegistration();

        logger.info("Filling registration form with valid data");
        registrationPage.fillRegistrationForm(
                "Anna",
                "Smith",
                "01/01/1995",
                "anna" + System.currentTimeMillis() + "@mail.com",
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "User should be logged in and redirected to dashboard");
        logger.info("Registration successful, dashboard loaded");
    }

    @Story("Registration with existing email")
    @Description("Verify registration is blocked if email is already registered")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC07: Verify registration attempt with already registered email does not proceed")
    public void testRegistrationWithExistingEmail() {
        logger.info("Navigating to Registration page");
        navigateToRegistration();

        String existingEmail = "tetianaletova@gmail.com";

        logger.info("Filling registration form with existing email: {}", existingEmail);
        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                existingEmail,
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(driver.getCurrentUrl().contains("/registration"),
                "User should remain on the registration page");
        logger.info("Registration blocked for existing email");
    }

    @Story("Registration password validation")
    @Description("Verify error message when password and confirm password do not match")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC08: Verify registration with password mismatch")
    public void testRegistrationWithPasswordMismatch() {
        logger.info("Navigating to Registration page");
        navigateToRegistration();

        logger.info("Filling registration form with mismatched passwords");
        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "test" + System.currentTimeMillis() + "@mail.com",
                "Pass123!!!",
                "Different123"
        );

        registrationPage.clickSubmit();

        boolean isErrorDisplayed = registrationPage.isPasswordMismatchErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message 'Passwords must match' should be displayed");
        logger.info("Password mismatch error displayed");
    }

    @Story("Registration form validation")
    @Description("Verify error message when mandatory fields are empty")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC09: Verify registration with empty mandatory fields")
    public void testRegistrationWithEmptyMandatoryFields() {
        logger.info("Navigating to Registration page");
        navigateToRegistration();

        logger.info("Filling registration form with empty first name");
        registrationPage.enterFirstName("");
        registrationPage.enterLastName("Smith");
        registrationPage.enterDateOfBirth("01/01/1995");
        registrationPage.enterEmail("test@mail.com");
        registrationPage.enterPassword("Pass123!");
        registrationPage.enterConfirmPassword("Pass123!");
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isFirstNameRequiredErrorDisplayed(),
                "Error message 'First Name is required' should be displayed");
        logger.info("First name required error displayed");
    }

    @Story("Registration form validation")
    @Description("Verify error message when email format is invalid")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TC10: Verify registration with invalid email format")
    public void testRegistrationWithInvalidEmailFormat() {
        logger.info("Navigating to Registration page");
        navigateToRegistration();

        logger.info("Filling registration form with invalid email");
        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "abc123",
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email format' should be displayed");
        logger.info("Invalid email format error displayed");
    }
}
