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

public class loginFailed {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("User login using wrong password")
    public void user_using_wrong_password() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("abcdefgh");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }
    @Then("User wont be able to login")
    public void user_wont_be_able_to_login() {
        String resultText= driver.findElement(By.xpath("//*[@class='error-message-container error']")).getText();
        String expectedResult = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(resultText, expectedResult);

        driver.quit();
    }
}
