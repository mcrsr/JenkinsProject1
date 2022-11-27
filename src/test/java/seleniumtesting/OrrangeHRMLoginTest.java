package seleniumtesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrrangeHRMLoginTest {

    @Parameters("Browser")
    @Test
    public void LoginTest(String browserName) throws InterruptedException {
        WebDriver driver = null;
        if(browserName.equalsIgnoreCase("chrome")){
            System.out.println("Provided Browser: "+browserName);
            WebDriverManager.chromedriver().setup();
            ChromeOptions options =new ChromeOptions();
            options.setHeadless(true);
            driver = new ChromeDriver(options);
        }else if (browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("oxd-userdropdown-tab")))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='oxd-userdropdown-link' and text()='Logout']")).click();
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void Test2(){
        Assert.assertTrue(true);
    }
}