package com.andersen;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.nio.file.Paths;
import java.time.Duration;

public class ChangeAvatarTest {

    @Test
    public void testChangeAvatar() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-course-01.andersenlab.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement uploadIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img.w-[95px]")));
        uploadIcon.click();

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible'; arguments[0].style.opacity = 1;", fileInput);

        String avatarPath = Paths.get("src/test/resources/f.jpg").toAbsolutePath().toString();

        fileInput.sendKeys(avatarPath);

        driver.quit();
    }
}
