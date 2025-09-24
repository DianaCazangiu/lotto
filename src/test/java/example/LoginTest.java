package example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;


public  class LoginTest  {
    private WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;


    @BeforeClass
    public void setupReport() {
        String workspace = System.getProperty("user.dir"); // Jenkins workspace
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(workspace + "/extent-report.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setUp() {
        // ✅ WebDriverManager automatically downloads and sets up ChromeDriver
        WebDriverManager.chromedriver().setup();
        // Chrome headless pentru Jenkins
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080"); // important for some page elementsgit add.
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testValidLogin() {
        test = extent.createTest("LoginTest", "Test pentru pagina de login");
        driver.get("https://the-internet.herokuapp.com/login");

        // introducem credentialele
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.tagName("button")).click();


        // verificăm mesajul de succes
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"),
                "Login-ul ar trebui să fie reușit.");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void flushReport() {
        extent.flush();
    }



}

