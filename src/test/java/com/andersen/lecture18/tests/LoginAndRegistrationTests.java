package com.andersen.lecture18.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

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
                "Error message 'Email or password is not valid");
    }

    @Test(description = "TC03: Verify login with empty fields")
    public void testLoginWithEmptyFields() {
        navigateToLogin();

        loginPage.clickSignIn();

        Assert.assertTrue(loginPage.isEmailRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed");
        Assert.assertTrue(loginPage.isPasswordRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed");
    }

    @Test(description = "TC04: Verify login with invalid email format")
    public void testLoginWithInvalidEmailFormat() {
        navigateToLogin();

        loginPage.login("abc123", "Pass123!");

        Assert.assertTrue(loginPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email address' should be displayed");
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

    @Test(description = "TC07: Verify registration attempt with already registered email does not proceed")
    public void testRegistrationWithExistingEmail() {
        navigateToRegistration();

        String existingEmail = "tetianaletova@gmail.com";

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                existingEmail,
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(driver.getCurrentUrl().contains("/register"),
                "User should remain on the registration page");
    }

    @Test(description = "TC08: Verify registration with password mismatch")
    public void testRegistrationWithPasswordMismatch() {
        navigateToRegistration();

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
    }


    @Test(description = "TC09: Verify registration with empty mandatory fields")
    public void testRegistrationWithEmptyMandatoryFields() {
        navigateToRegistration();

        registrationPage.enterFirstName("");
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
                "abc123",
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isInvalidEmailFormatErrorDisplayed(),
                "Error message 'Invalid email format' should be displayed");
    }
}