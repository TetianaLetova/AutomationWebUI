package com.andersen.lecture20.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginTestsDef {

    private final Hooks hooks;

    public LoginTestsDef(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("I open the Sign In page")
    public void iOpenTheSignInPage() {
        hooks.logger.info("Navigating to Login page");
        hooks.navigateToLogin();
    }

    @When("I enter a valid email")
    public void iEnterAValidEmail() {
        hooks.loginPage.enterEmail("testuser@mail.com");
    }

    @And("I enter a valid password")
    public void iEnterAValidPassword() {
        hooks.loginPage.enterPassword("Pass123!");
    }

    @And("I click {string}")
    public void iClick(String button) {
        if (button.equalsIgnoreCase("Sign In")) {
            hooks.loginPage.clickSignIn();
        } else {
            throw new IllegalArgumentException("Unknown button: " + button);
        }
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        hooks.wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        Assert.assertTrue(hooks.dashboardPage.isDashboardLoaded(),
                "User should be logged in and redirected to dashboard");
        hooks.logger.info("Login successful, dashboard loaded");
    }

    @And("I enter an invalid password")
    public void iEnterAnInvalidPassword() {
        hooks.loginPage.enterPassword("wrongPass");
    }

    @Then("I should see an error message about incorrect credentials")
    public void iShouldSeeAnErrorMessageAboutIncorrectCredentials() {
        Assert.assertTrue(hooks.loginPage.isInvalidCredentialsErrorDisplayed(),
                "Error message 'Email or password is not valid' should be displayed");
        hooks.logger.info("Invalid credentials error displayed");
    }

    @When("I leave the email field empty")
    public void iLeaveTheEmailFieldEmpty() {
        hooks.loginPage.enterEmail("");
    }

    @And("I leave the password field empty")
    public void iLeaveThePasswordFieldEmpty() {
        hooks.loginPage.enterPassword("");
    }

    @Then("I should see an error message about required fields")
    public void iShouldSeeAnErrorMessageAboutRequiredFields() {
        Assert.assertTrue(hooks.loginPage.isEmailRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed for email");
        Assert.assertTrue(hooks.loginPage.isPasswordRequiredErrorDisplayed(),
                "Error message 'Required' should be displayed for password");
        hooks.logger.info("Required field errors displayed");
    }
}
