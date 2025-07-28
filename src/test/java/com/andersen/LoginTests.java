package com.andersen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginTests extends BaseTest {

    private final String baseUrl = "https://qa-course-01.andersenlab.com/";

    @Test
    public void testLoginEmptyFields_TC03() {
        driver.get(baseUrl + "login");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        List<WebElement> errors = driver.findElements(By.cssSelector("span.text-rose-500.text-sm"));
        Assertions.assertEquals(2, errors.size(), "Expected two error messages");
        Assertions.assertEquals("Required", errors.get(0).getText(), "Email error mismatch");
        Assertions.assertEquals("Required", errors.get(1).getText(), "Password error mismatch");
    }

    @Test
    public void testLoginInvalidEmail_TC04() {
        driver.get(baseUrl + "login");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("abc123");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Pass123!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.text-rose-500.text-sm")));
        Assertions.assertEquals("Invalid email address", emailError.getText(), "Email error mismatch");
    }

}
