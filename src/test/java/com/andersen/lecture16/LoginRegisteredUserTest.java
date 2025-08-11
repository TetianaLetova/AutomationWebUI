package com.andersen.lecture16;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginRegisteredUserTest extends BaseTest {

    private final String baseUrl = "https://qa-course-01.andersenlab.com/";

    @Test
    public void testLoginRegisteredUser() {
        driver.get(baseUrl + "login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")))
                .sendKeys("tetianaletova@gmail.com");

        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("adfdtcfjgkhl557#G");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        System.out.println("Current URL after login: " + driver.getCurrentUrl());
        System.out.println("Page title after login: " + driver.getTitle());
    }
}
