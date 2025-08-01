package com.andersen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class LoginTestParameters extends BaseTest {
    private final String baseUrl = "https://qa-course-01.andersenlab.com/";

    @Parameters({"email", "password"})
    @org.testng.annotations.Test
    public void testLoginValidUser(String email, String password) {
        driver.get(baseUrl + "login");

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signOutElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Sign Out')]"))
        );

        Assert.assertTrue(signOutElement.isDisplayed(), "Sign Out button is not displayed, login might have failed.");
    }
}
