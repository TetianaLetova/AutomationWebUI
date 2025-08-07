package com.andersen.lecture18.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectPage extends BasePage {

    @FindBy(css = "select[title='Select country']")
    private WebElement countrySelect;

    @FindBy(id = "SelectLanguage")
    private WebElement languageSelect;

    @FindBy(css = "select[title='Select type']")
    private WebElement typeSelect;

    @FindBy(css = "input[type='date'][title='Start date']")
    private WebElement startDateField;

    @FindBy(css = "input[type='date'][title='End date']")
    private WebElement endDateField;

    @FindBy(id = "MultipleSelect")
    private WebElement multipleSelectElement;

    @FindBy(css = "button[name='SelectPageSearchButton']")
    private WebElement searchButton;

    public SelectPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectCountry(String country) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(countrySelect)));
        select.selectByVisibleText(country);
    }

    public void selectLanguage(String language) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(languageSelect)));
        select.selectByVisibleText(language);
    }

    public void selectType(String type) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(typeSelect)));
        select.selectByVisibleText(type);
    }

    public void setStartDate(String date) {
        wait.until(ExpectedConditions.elementToBeClickable(startDateField));
        startDateField.clear();
        startDateField.sendKeys(date);
    }

    public void setEndDate(String date) {
        wait.until(ExpectedConditions.elementToBeClickable(endDateField));
        endDateField.clear();
        endDateField.sendKeys(date);
        endDateField.sendKeys(Keys.TAB);
    }

    public void selectMultipleCourses(String... courses) {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(multipleSelectElement));

        actions.keyDown(Keys.CONTROL);
        for (String course : courses) {
            WebElement option = multipleSelectElement.findElement(
                    org.openqa.selenium.By.xpath(".//option[text()='" + course + "']"));
            actions.click(option);
        }
        actions.keyUp(Keys.CONTROL).build().perform();
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}