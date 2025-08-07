package com.andersen.lecture18.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTests extends BaseTest {

    @Test(description = "TC11: Verify registration with invalid date format")
    public void testRegistrationWithInvalidDate() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "32/13/3000", // Invalid date
                "test" + System.currentTimeMillis() + "@mail.com",
                "Pass123!",
                "Pass123!"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(
                registrationPage.isInvalidDateErrorDisplayed() ||
                        registrationPage.isDateRequiredErrorDisplayed(),
                "Error message 'Invalid date' or 'Date of Birth is required' should be displayed"
        );
    }

    @Test(description = "TC12: Verify registration password minimum length validation")
    public void testRegistrationWithShortPassword() {
        navigateToRegistration();

        registrationPage.fillRegistrationForm(
                "Test",
                "User",
                "01/01/1995",
                "test" + System.currentTimeMillis() + "@mail.com",
                "123", // Too short password
                "123"
        );
        registrationPage.clickSubmit();

        Assert.assertTrue(registrationPage.isPasswordLengthErrorDisplayed(),
                "Error message 'Password must be at least 6 characters' should be displayed");
    }

    @Test(description = "TC13: Verify Sign In page elements visibility")
    public void testSignInPageElementsVisibility() {
        navigateToLogin();

        Assert.assertTrue(loginPage.areAllElementsVisible(),
                "Email, Password, 'Sign In' button, and 'Registration' link should be visible");
    }

    @Test(description = "TC14: Verify Registration page elements visibility")
    public void testRegistrationPageElementsVisibility() {
        navigateToRegistration();

        Assert.assertTrue(registrationPage.areAllElementsVisible(),
                "First Name, Last Name, Date of Birth, Email, Password, Confirm Password fields and Submit button should be visible");
    }

    @Test(description = "TC15: Verify navigation back to Sign In page from Registration")
    public void testNavigationBackToSignInFromRegistration() {
        navigateToRegistration();

        registrationPage.clickSignInLink();

        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "User should be redirected back to Sign In page");
        Assert.assertTrue(loginPage.areAllElementsVisible(),
                "Sign In page should load without errors");
    }
}