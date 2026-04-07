package tests;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class FirstTest {

    @Test
    public void purchaseFlow() throws Exception {

        String content = new String(Files.readAllBytes(
                Paths.get("src/test/resources/credential.json")));

        JSONObject json = new JSONObject(content);

        String username = json.getString("username");
        String password = json.getString("password");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();

        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "2");

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("600001");

        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        String message = driver.findElement(By.className("complete-header")).getText();

        Assert.assertTrue(message.contains("Thank you"));

        driver.quit();
    }
}