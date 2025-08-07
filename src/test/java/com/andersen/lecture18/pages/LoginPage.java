package com.andersen.lecture18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(css = "input[name='email']")
    private WebElement emailField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @FindBy(linkText = "Registration")
    private WebElement registrationLink;

    @FindBy(xpath = "//div[contains(text(),'Invalid email or password')]")
    private WebElement invalidCredentialsError;

    @FindBy(xpath = "//div[contains(text(),'Email is required')]")
    private WebElement emailRequiredError;

    @FindBy(xpath = "//div[contains(text(),'Password is required')]")
    private WebElement passwordRequiredError;

    @FindBy(xpath = "//div[contains(text(),'Invalid email format')]")
    private WebElement invalidEmailFormatError;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickRegistrationLink() {
        registrationLink.click();
    }

    public boolean isInvalidCredentialsErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(invalidCredentialsError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailRequiredErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(emailRequiredError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordRequiredErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordRequiredError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInvalidEmailFormatErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(invalidEmailFormatError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public boolean areAllElementsVisible() {
        try {
            return emailField.isDisplayed() &&
                    passwordField.isDisplayed() &&
                    signInButton.isDisplayed() &&
                    registrationLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}