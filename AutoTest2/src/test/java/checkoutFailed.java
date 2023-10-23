import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class checkoutFailed {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://www.saucedemo.com";

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        driver.findElement(By.partialLinkText("Bolt T-Shirt")).click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@class='btn btn_primary btn_small btn_inventory']")).click();

        String actualText_cartAmount= driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText();
        String expectedResult_cartAmount = "1";

        Assert.assertEquals(actualText_cartAmount, expectedResult_cartAmount);

        driver.findElement(By.xpath("//*[@class='shopping_cart_link']")).click();
        Thread.sleep(500);

        String actualText_prodName = driver.findElement(By.xpath("//*[@class='inventory_item_name']")).getText();
        String expectedResult_prodName = "Sauce Labs Bolt T-Shirt";

        Assert.assertEquals(actualText_prodName, expectedResult_prodName);

        driver.findElement(By.xpath("//*[@class='btn btn_secondary btn_small cart_button']")).click();

        driver.findElement(By.xpath("//*[@id='checkout']")).click();

        driver.findElement(By.xpath("//*[@id='continue']")).click();

        String actualText_errorCheckout = driver.findElement(By.xpath("//*[@class='error-message-container error']")).getText();
        String expectedResult_errorCheckout = "Error: First Name is required";
        Assert.assertEquals(actualText_errorCheckout, expectedResult_errorCheckout);

        driver.quit();

    }
}
