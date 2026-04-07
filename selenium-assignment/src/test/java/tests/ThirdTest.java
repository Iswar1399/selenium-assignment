package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ThirdTest {

    @Test
    public void case3Test() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://onlinehtmleditor.dev/");

        Thread.sleep(4000);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("let popup = document.querySelector('.ch2-dialog'); if(popup){popup.remove();}");

        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".ck-editor__editable")).click();

        driver.findElement(By.cssSelector(".ck-editor__editable"))
                .sendKeys(Keys.chord(Keys.CONTROL, "b"), "Automation");

        driver.findElement(By.cssSelector(".ck-editor__editable"))
                .sendKeys(" ");

        driver.findElement(By.cssSelector(".ck-editor__editable"))
                .sendKeys(Keys.chord(Keys.CONTROL, "u"), "Test");

        driver.findElement(By.cssSelector(".ck-editor__editable"))
                .sendKeys(" Example");

        String text = driver.findElement(By.cssSelector(".ck-editor__editable")).getText();

        System.out.println("Editor text: " + text);

        Assert.assertTrue(text.contains("Automation"));
        Assert.assertTrue(text.contains("Test"));
        Assert.assertTrue(text.contains("Example"));

        driver.quit();
    }
}