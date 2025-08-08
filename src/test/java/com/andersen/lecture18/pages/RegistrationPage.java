package com.andersen.lecture18.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    @FindBy(name = "passwordConfirmation")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(linkText = "Sign In")
    private WebElement signInLink;

    @FindBy(xpath = "//a[contains(text(), 'Sign In')]")
    private WebElement alternativeSignInLink;

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

    private String monthName(int monthNumber) {
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        return months[monthNumber - 1];
    }

    public void enterDateOfBirth(String dateOfBirth) {
        try {
            dateOfBirthField.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker__month-container")));
            List<WebElement> selects = driver.findElements(By.cssSelector(".react-datepicker__month-container select[data-sharklabel='birthday']"));
            String[] parts = dateOfBirth.split("/");
            String day = String.valueOf(Integer.parseInt(parts[0]));
            String month = monthName(Integer.parseInt(parts[1]));
            String year = parts[2];
            if (selects.size() >= 2) {
                Select yearSelect = new Select(selects.get(0));
                Select monthSelect = new Select(selects.get(1));
                yearSelect.selectByValue(year);
                monthSelect.selectByVisibleText(month);
                By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') and normalize-space(text())='" + day + "' and not(contains(@class,'react-datepicker__day--outside-month'))]");
                wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".react-datepicker__month-container")));
                return;
            }
        } catch (Exception ignored) {
        }
        try {
            dateOfBirthField.clear();
            dateOfBirthField.sendKeys(dateOfBirth);
            dateOfBirthField.sendKeys(Keys.TAB);
        } catch (Exception ignored) {
        }
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
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".react-datepicker__month-container")));
        } catch (Exception ignored) {
        }
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }
    }

    public void clickSignInLink() {
        try {
            signInLink.click();
        } catch (Exception e) {
            alternativeSignInLink.click();
        }
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Passwords must match')]")
            ));
            return error.isDisplayed();
        } catch (TimeoutException e) {
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
            wait.until(ExpectedConditions.visibilityOf(firstNameField));
            boolean firstNameVisible = firstNameField.isDisplayed();
            boolean lastNameVisible = lastNameField.isDisplayed();
            boolean dateVisible = dateOfBirthField.isDisplayed();
            boolean emailVisible = emailField.isDisplayed();
            boolean passwordVisible = passwordField.isDisplayed();
            boolean confirmPasswordVisible = confirmPasswordField.isDisplayed();
            boolean submitVisible = submitButton.isDisplayed();
            return firstNameVisible && lastNameVisible && dateVisible &&
                    emailVisible && passwordVisible && confirmPasswordVisible && submitVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
