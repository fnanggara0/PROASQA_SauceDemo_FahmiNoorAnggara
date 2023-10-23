import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class login {
    public static void main(String[] args){
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://www.saucedemo.com";

        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        //driver.quit();
    }
}
