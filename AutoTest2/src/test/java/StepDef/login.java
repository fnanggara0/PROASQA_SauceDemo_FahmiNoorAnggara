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

public class login {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User is in the login page")
    public void user_is_in_the_login_page() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(baseUrl);
    }
    @And("User entered standard_user in username")
    public void user_entered_standard_user_in_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }
    @And("User entered secret_sauce in password")
    public void user_entered_secret_sauce_in_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }
    @When("User click the login button")
    public void user_click_the_login_button() {
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }
    @Then("User will be able to login")
    public void user_will_be_able_to_login() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        driver.quit();
    }
}
