package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Every test class extends this. Handles browser lifecycle (Task 1: framework setup)
 * so individual test classes only contain test logic, not boilerplate.
 */
public class BaseTest {

    protected WebDriver driver;
    protected ActionUtils actions;

    public static final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Uncomment the next line to run headless (e.g. in CI pipelines)
        // options.addArguments("--headless=new");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        actions = new ActionUtils(driver);

        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
