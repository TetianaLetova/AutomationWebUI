package com.andersen;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowTests extends BaseTest {

    @Test
    public void testSwitchBetweenWindows() {
        driver.get("http://www.automationpractice.pl/index.php");

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://zoo.waw.pl/");
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.w3schools.com/");
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.clickspeedtester.com/click-counter/");
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://andersenlab.com/");

        Set<String> handles = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(handles);

        for (String handle : windowList) {
            driver.switchTo().window(handle);
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            System.out.println("Page title: " + title);
            System.out.println("Page URL: " + url);

            if (title.toLowerCase().contains("zoo")) {
                driver.close();
                System.out.println("Closed Zoo window");
            }
        }

        if (!driver.getWindowHandles().isEmpty()) {
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
        }
    }
}
