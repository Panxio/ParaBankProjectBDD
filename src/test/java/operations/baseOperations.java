package operations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class baseOperations {
    public static WebDriver driver;
    public static Properties properties = new Properties();
    private static WebDriverWait wait;

    @BeforeClass
    public static void setupClass() throws Exception {
        properties.load(Files.newInputStream(Paths.get("src/test/java/config/config.properties")));

        if (properties.getProperty("navegador").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.setCapability("unexpectedAlertBehaviour", UnexpectedAlertBehaviour.IGNORE);

            driver = new ChromeDriver(options);
        }

        else {
            throw new RuntimeException("Explorador no soportado...");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);
    }

    @Before()
    public void beforeTest() {
        System.out.println("Navegando a: " + properties.getProperty("urlInicio"));
        driver.get(properties.getProperty("urlInicio"));
    }

    @After
    public void afterTest() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void suiteTearDown() {
        driver.quit();
    }
}
