package StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class checkoutProduct {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User is already have product in cart")
    public void user_is_already_have_product_in_the_cart() throws InterruptedException {
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


    }
    @And("User tap on checkout button")
    public void user_tap_on_checkout_button(){
        driver.findElement(By.xpath("//*[@id='checkout']")).click();
    }
    @And("User input John in first name")
    public void user_input_john_in_first_name(){
        driver.findElement(By.name("firstName")).sendKeys("John");
    }
    @And("User input Doe in last name")
    public void user_input_doe_in_last_name(){
        driver.findElement(By.name("lastName")).sendKeys("Doe");
    }
    @And("User input 112233 in postal code")
    public void user_input_112233_in_postal_code(){
        driver.findElement(By.name("postalCode")).sendKeys("112233");
    }
    @And("User tap on the continue button")
    public void user_tap_on_the_continue_button(){
        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }
    @When("User tap on the finish button on the Checkout Overview")
    public void user_tap_on_the_finish_button_on_the_checkout_overview() {
        driver.findElement(By.xpath("//*[@id='finish']")).click();
    }
    @Then("User will be able to checkout")
    public void user_will_be_able_to_checkout(){
        String actualText_orderConfirm = driver.findElement(By.xpath("//*[@class='complete-header']")).getText();
        String expectedResult_orderConfirm = "Thank you for your order!";
        Assert.assertEquals(actualText_orderConfirm, expectedResult_orderConfirm);

        driver.findElement(By.xpath("//*[@name='back-to-products']")).click();

        driver.quit();
    }

}
