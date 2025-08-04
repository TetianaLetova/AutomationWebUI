package lecture17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "https://qa-course-01.andersenlab.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login();
    }

    public void login() {
        driver.get(baseUrl + "login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")))
                .sendKeys("tetianaletova@gmail.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("adfdtcfjgkhl557#G");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
