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

public class addToCart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User is already login and in the inventory page")
    public void user_is_already_login_and_in_the_inventory_page() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

    }
    @When("User clicks on the add to cart button")
    public void user_click_on_the_add_to_cart_button() {
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
    }
    @Then("User will be able to add product to cart")
    public void user_will_be_able_to_add_product_to_cart() throws InterruptedException {
        String actualText_cartAmount= driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText();
        String expectedResult_cartAmount = "1";

        Assert.assertEquals(actualText_cartAmount, expectedResult_cartAmount);

        driver.findElement(By.xpath("//*[@class='shopping_cart_link']")).click();
        Thread.sleep(500);

        String actualText_prodName = driver.findElement(By.xpath("//*[@class='inventory_item_name']")).getText();
        String expectedResult_prodName = "Sauce Labs Bolt T-Shirt";

        Assert.assertEquals(actualText_prodName, expectedResult_prodName);

        driver.findElement(By.xpath("//*[@class='btn btn_secondary btn_small cart_button']")).click();

        driver.quit();
    }
}
