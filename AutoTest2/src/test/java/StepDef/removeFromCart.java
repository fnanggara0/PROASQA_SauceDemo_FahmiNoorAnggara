package StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class removeFromCart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User is already login and have product in cart")
    public void user_is_already_login_and_have_product_in_cart() {
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

    }
    @When("User clicks on remove button")
    public void user_click_on_remove_button() {
        driver.findElement(By.xpath("//*[@id='remove-sauce-labs-bolt-t-shirt']")).click();
    }
    @Then("User will be able to remove product from cart")
    public void user_will_be_able_to_remove_product_from_cart(){

        String actualText_prodName = driver.findElement(By.xpath("//*[@id='cart_contents_container']/div")).getText();
        String expectedResult_prodName = "Sauce Labs Bolt T-Shirt";

        Assert.assertNotEquals(actualText_prodName, expectedResult_prodName);

        driver.quit();
    }
}
