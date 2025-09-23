import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends base {
     //static WebDriver driver;
    static ExtentTest test;
    static ExtentReports extent;

    @BeforeClass
    public static void setup() {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        test = extent.createTest("Login Test");
        //extent = ExtentManager.getInstance();
        //test = ExtentManager.extent.createTest("Login Test");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Creăm un profil curat (gol)
        options.addArguments("user-data-dir=C:/temp/chrome-clean-profile");

        // Dezactivăm password manager și popup-urile de browser
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        // Pornim Chrome cu aceste opțiuni
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
            extent.flush(); // Salvează raportul
        }
    }

    @Test
    public void testValidLogin() {
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

    }




