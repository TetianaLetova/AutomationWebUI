package com.andersen.lecture18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "dateOfBirth")
    private WebElement dateOfBirthField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(linkText = "Sign In")
    private WebElement signInLink;

    @FindBy(xpath = "//div[contains(text(),'Email already exists')]")
    private WebElement emailExistsError;

    @FindBy(xpath = "//div[contains(text(),'Passwords do not match')]")
    private WebElement passwordMismatchError;

    @FindBy(xpath = "//div[contains(text(),'First Name is required')]")
    private WebElement firstNameRequiredError;

    @FindBy(xpath = "//div[contains(text(),'Invalid email format')]")
    private WebElement invalidEmailFormatError;

    @FindBy(xpath = "//div[contains(text(),'Invalid date')]")
    private WebElement invalidDateError;

    @FindBy(xpath = "//div[contains(text(),'Date of Birth is required')]")
    private WebElement dateRequiredError;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 6 characters')]")
    private WebElement passwordLengthError;

    @FindBy(xpath = "//div[contains(text(),'Registration successful')]")
    private WebElement successMessage;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(dateOfBirth);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public void fillRegistrationForm(String firstName, String lastName, String dateOfBirth,
                                     String email, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDateOfBirth(dateOfBirth);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
    }

    public boolean isEmailExistsErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(emailExistsError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordMismatchErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordMismatchError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstNameRequiredErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(firstNameRequiredError)).isDisplayed();
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

    public boolean isInvalidDateErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(invalidDateError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDateRequiredErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(dateRequiredError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordLengthErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordLengthError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllElementsVisible() {
        try {
            return firstNameField.isDisplayed() &&
                    lastNameField.isDisplayed() &&
                    dateOfBirthField.isDisplayed() &&
                    emailField.isDisplayed() &&
                    passwordField.isDisplayed() &&
                    confirmPasswordField.isDisplayed() &&
                    submitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
