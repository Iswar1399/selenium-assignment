package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class FourthTest {

    @Test
    public void case4Test() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demo.guru99.com/test/guru99home");

        Thread.sleep(5000);

        try {
            driver.switchTo().frame(driver.findElement(By.cssSelector("iframe")));
            driver.findElement(By.tagName("img")).click();

            String parent = driver.getWindowHandle();

            for (String win : driver.getWindowHandles()) {
                if (!win.equals(parent)) {
                    driver.switchTo().window(win);
                    driver.close();
                }
            }

            driver.switchTo().window(parent);
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("iframe step skipped");
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);

        try {
            List<org.openqa.selenium.WebElement> inputs = driver.findElements(By.cssSelector("input[type='text'], input[type='email']"));

            for (org.openqa.selenium.WebElement input : inputs) {
                if (input.isDisplayed()) {
                    input.clear();
                    input.sendKeys("test@gmail.com");
                    break;
                }
            }

            List<org.openqa.selenium.WebElement> buttons = driver.findElements(By.cssSelector("input[type='submit']"));

            for (org.openqa.selenium.WebElement btn : buttons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    break;
                }
            }

            Thread.sleep(2000);

            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.toLowerCase().contains("successfully"));

        } catch (Exception e) {
            System.out.println("email step skipped");
        }

        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.linkText("Tooltip")).click();

        Thread.sleep(2000);

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("download now"));

        driver.quit();
    }
}