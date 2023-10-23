package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class checkoutFailed {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User is already in checkout page")
    public void user_is_already_in_checkout_page() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();

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

    }
    @When("User try to tap on the continue button")
    public void user_try_to_tap_on_the_continue_button(){
        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }
    @Then("User wont be able to checkout")
    public void user_wont_be_able_to_checkout(){
        String actualText_errorCheckout = driver.findElement(By.xpath("//*[@class='error-message-container error']")).getText();
        String expectedResult_errorCheckout = "Error: First Name is required";
        Assert.assertEquals(actualText_errorCheckout, expectedResult_errorCheckout);

        driver.quit();
    }
}
