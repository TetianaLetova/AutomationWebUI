package com.andersen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ChangeAvatarTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testChangeAvatar() {
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='email']")));
        emailField.sendKeys("tetianaletova@gmail.com");

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys("adfdtcfjgkhl557#G");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        List<WebElement> uploadIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("img[class*='w-[95px]']")));
        uploadIcons.get(1).click();

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.visibility='visible'; arguments[0].style.opacity=1;",
                fileInput
        );

//        String avatarPath = "C:\\\\Users\\\\06375\\\\Desktop\\\\f.jpg";
//        fileInput.sendKeys(avatarPath);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//p[@class='mt-12' and text()='Your photo has been updated']")));

        driver.quit();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
