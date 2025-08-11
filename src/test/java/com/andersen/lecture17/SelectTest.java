package com.andersen.lecture17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class SelectTest extends BaseTest {

    @Test
    public void testSelectDropdowns() {
        WebElement expandIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Expand']"))
        );
        expandIcon.click();

        WebElement selectOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'justify-center') and text()='Select']")
                )
        );
        selectOption.click();

        wait.until(ExpectedConditions.urlContains("/select"));

        Select countrySelect = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[title='Select country']"))));
        countrySelect.selectByVisibleText("USA");

        Select languageSelect = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("SelectLanguage"))));
        languageSelect.selectByVisibleText("English");

        Select typeSelect = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[title='Select type']"))));
        typeSelect.selectByVisibleText("Testing");

        WebElement startDate = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='date'][title='Start date']")));
        WebElement endDate = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='date'][title='End date']")));

        startDate.clear();
        startDate.sendKeys("11-08-2025");
        endDate.clear();
        endDate.sendKeys("25-08-2025");

        endDate.sendKeys(Keys.TAB);

        WebElement coursesSelectElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("MultipleSelect"))
        );

        Actions actions = new Actions(driver);

        WebElement optionJava = coursesSelectElement.findElement(By.xpath(".//option[text()='AQA Java']"));
        WebElement optionPython = coursesSelectElement.findElement(By.xpath(".//option[text()='AQA Python']"));

        actions.keyDown(Keys.CONTROL)
                .click(optionJava)
                .click(optionPython)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();


        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='SelectPageSearchButton']")));
        searchButton.click();

        wait.until(ExpectedConditions.urlContains("/search_results"));
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        String expectedText = "Unfortunately, we did not find any courses matching your chosen criteria.";
        assert message.getText().contains(expectedText)
                : "Expected result" + expectedText + ", actual result : " + message.getText();
    }
}
