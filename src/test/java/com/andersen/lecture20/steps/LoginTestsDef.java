package com.andersen.lecture20.steps;

import com.andersen.lecture18.tests.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTestsDef extends BaseTest {
    @Given("I open the Sign In page")
    public void iOpenTheSignInPage() {
        logger.info("Navigating to Login page");
        navigateToLogin();
    }

    @When("I enter a valid email")
    public void iEnterAValidEmail() {
    }

    @And("I enter a valid password")
    public void iEnterAValidPassword() {
    }

    @And("I click {string}")
    public void iClick(String arg0) {
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
    }

    @And("I enter an invalid password")
    public void iEnterAnInvalidPassword() {
    }

    @Then("I should see an error message about incorrect credentials")
    public void iShouldSeeAnErrorMessageAboutIncorrectCredentials() {
    }

    @When("I leave the email field empty")
    public void iLeaveTheEmailFieldEmpty() {
    }

    @And("I leave the password field empty")
    public void iLeaveThePasswordFieldEmpty() {
    }

    @Then("I should see an error message about required fields")
    public void iShouldSeeAnErrorMessageAboutRequiredFields() {
    }
}
