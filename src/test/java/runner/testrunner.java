package runner;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/test.feature",
        glue = "seleniumgluecode",
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        monochrome = true,
        //tags = "@Register,@Login,@NewSavingsAccount,@TransferAndLogout"
        tags = "@TransferAndLogout"
)
public class testrunner {
    public WebDriver driver;
    @After
    public void afterTest() {
        driver.manage().deleteAllCookies();
    }
    @AfterClass
    public static void writeExtendReport(){
        Reporter.loadXMLConfig(new File("src/test/java/config/report.xml"));
    }
}