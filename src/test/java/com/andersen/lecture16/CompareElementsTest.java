package com.andersen.lecture16;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CompareElementsTest extends BaseTest {

    @Test
    public void testCompareTwoElements() {
        driver.get("https://www.w3schools.com/");

        WebElement element1 = driver.findElement(By.cssSelector("a.w3-button"));
        WebElement element2 = driver.findElement(By.cssSelector("div.w3-bar"));

        ElementUtils.compareElements(element1, element2);
    }
}
