package com.andersen.lecture18.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginAndRegistrationTests extends BaseTest {

    @Test(description = "TC01: Verify login with valid credentials")
    public void testLoginWithValidCredentials() {
        navigateToLogin();

        loginPage.login("testuser@mail.com", "Pass123!");

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "User should be logged in and redirected to dashboard");
    }

    @Test(description = "TC02: Verify login with invalid password")
    public void testLoginWithInvalidPassword() {
        navigateToLogin();

        loginPage.login("testuser@mail.com", "wrongPass");

        Assert.assertTrue(loginPage.isInvalidCredentialsErrorDisplayed(),
                "Error message 'Invalid email or password' should be displayed");
    }

    @Test(description = "TC03: Verify login with empty fields")
    public void testLoginWithEmptyFields() {
        navigateToLogin();

        loginPage.clickSignIn(); // Click without entering any data

        Assert.assertTrue(loginPage.isEmailRequiredErrorDisplayed(),
                "Error message 'Email is required' should be displayed");
        Assert.assertTrue(loginPage.isPasswordRequiredErrorDisplayed(),
                "Error message 'Password is required' should be displayed");
    }

    @Test(description = "TC04: Verify login with invalid email format")
    public void testLoginWithInvalidEmailFormat() {
        navigateToLogin();

        loginPage.login("abc123", "Pass123!");

        Assert.assertTrue(loginPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email format' should be displayed");
    }

    @Test(description = "TC05: Verify navigation to Registration page")
    public void testNavigationToRegistrationPage() {
        navigateToLogin();

        loginPage.clickRegistrationLink();

        wait.until(ExpectedConditions.urlContains("/registration"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/registration"),
                "User should be redirected to Registration page");
        Assert.assertTrue(registrationPage.areAllElementsVisible(),
                "Registration page elements should be visible");
    }

    @Test(description = "TC06: Verify registration with valid data")
    public void testRegistrationWithValidData() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Anna",
                "Smith",
                "01/01/1995",
                "anna" + System.currentTimeMillis() + "@mail.com", // Unique email
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(
                registrationPage.isSuccessMessageDisplayed() ||
                        wait.until(ExpectedConditions.urlContains("/login")).booleanValue(),
                "Registration should be successful with redirect to Sign In or success message"
        );
    }

    @Test(description = "TC07: Verify registration with already registered email")
    public void testRegistrationWithExistingEmail() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "testuser@mail.com", // Already existing email
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isEmailExistsErrorDisplayed(),
                "Error message 'Email already exists' should be displayed");
    }

    @Test(description = "TC08: Verify registration with password mismatch")
    public void testRegistrationWithPasswordMismatch() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "test" + System.currentTimeMillis() + "@mail.com",
                "Pass123!",
                "Different123"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isPasswordMismatchErrorDisplayed(),
                "Error message 'Passwords do not match' should be displayed");
    }

    @Test(description = "TC09: Verify registration with empty mandatory fields")
    public void testRegistrationWithEmptyMandatoryFields() {
        navigateToRegistration();

        // Fill all fields except First Name
        registrationPage.enterLastName("Smith");
        registrationPage.enterDateOfBirth("01/01/1995");
        registrationPage.enterEmail("test@mail.com");
        registrationPage.enterPassword("Pass123!");
        registrationPage.enterConfirmPassword("Pass123!");
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isFirstNameRequiredErrorDisplayed(),
                "Error message 'First Name is required' should be displayed");
    }

    @Test(description = "TC10: Verify registration with invalid email format")
    public void testRegistrationWithInvalidEmailFormat() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "abc123", // Invalid email format
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email format' should be displayed");
    }
}