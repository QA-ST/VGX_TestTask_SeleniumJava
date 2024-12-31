package Pages.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebDriverWait waitForElement;
    protected PageFactory pageFactory;
    public static Actions action;

    @BeforeClass
    public void setupBrowserAndNavigateToWebApp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920,1080");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.navigate().to("http://cloudtwo.cloud-vms.com/sign-in");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForElement = new WebDriverWait(driver, Duration.ofSeconds(4));
        pageFactory = new PageFactory();
        action = new Actions(driver);
    }

    @AfterClass
    public void closeBrowserContext() {
        driver.quit();
    }
}