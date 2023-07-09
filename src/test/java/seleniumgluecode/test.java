package seleniumgluecode;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.abstractMethods;

import java.util.concurrent.TimeUnit;

public class test extends abstractMethods {
    public static WebDriver driver;

    private static String user;
    String pass = "12345678";

    private static String savingAccount;


    @Given("^user is on homepage$")
    public void user_is_on_homepage() throws Throwable {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @When("^user navigates to Register Page$")
    public void user_navigates_to_Register_Page() throws Throwable {
        driver.findElement(By.linkText("Register")).click();
    }

    @When("^user enters the data required for registration$")
    public void user_enters_the_data_required_for_registration() throws Throwable {
        driver.findElement(By.id("customer.firstName")).sendKeys("Juan");
        driver.findElement(By.name("customer.lastName")).sendKeys("Pérez");
        driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("calle test 123");
        driver.findElement(By.id("customer.address.city")).sendKeys("TestCity");
        driver.findElement(By.id("customer.address.state")).sendKeys("TestState1");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("40601");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("202-555-0148");
        driver.findElement(By.id("customer.ssn")).sendKeys("532-01-3055");
        user = "Test_"+randomUserNumber();
        System.out.println(user);
        driver.findElement(By.id("customer.username")).sendKeys(user);
        driver.findElement(By.id("customer.password")).sendKeys(pass);
        driver.findElement(By.id("repeatedPassword")).sendKeys(pass);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='button' and @value='Register']")).click();
    }

    @Then("^success message is deplayed$")
    public void success_message_is_deplayed() throws Throwable {
        String exp_message = "Your account was created successfully. You are now logged in.";
        String actual = driver.findElement(By.xpath("//div[@id='rightPanel']/p")).getText();
        Assert.assertEquals(exp_message, actual);
    }

    @Then("^valid if the user is the one who has registered\\.$")
    public void valid_if_the_user_is_the_one_who_has_registered() throws Throwable {
        //String exp_name = "Welcome "+ user+"asd";
        String exp_name = "Welcome "+ user;
        String userActual = driver.findElement(By.xpath("//div[@id='rightPanel']/h1")).getText();
        String filteredName= getUserNameRegister(userActual);
        System.out.println(filteredName);
        Assert.assertEquals(exp_name, userActual);
        Thread.sleep(2000);
        driver.quit();
    }
    @When("^user enters his login credentials$")
    public void user_enters_his_login_credentials() throws Throwable {
        System.out.println("The user is: "+user);
        System.out.println("The pass is: "+pass);
        user = "Test_20230709115349";
        driver.findElement(By.name("username")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @Then("^user enters the main dashboard$")
    public void user_enters_the_main_dashboard() throws Throwable {
        Thread.sleep(2000);
        String TextExpected = "Accounts Overview";
        String actualText = driver.findElement(By.xpath("//div[@class=\"ng-scope\"]/div/h1")).getText();
        Assert.assertEquals(TextExpected,actualText);
        driver.quit();
    }

    @When("^user clicks on the new account link$")
    public void user_clicks_on_the_new_account_link() throws Throwable {
        driver.findElement(By.linkText("Open New Account")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='title']")));
        WebElement selectTypeAccount = driver.findElement(By.id("type"));
        Select select = new Select(selectTypeAccount);
        select.selectByValue("1");
        WebElement selectAccountNumber = driver.findElement(By.id("fromAccountId"));
        Select select1 = new Select(selectAccountNumber);
        WebElement defaultOption = select.getFirstSelectedOption();
        System.out.println("Opción seleccionada por defecto: " + defaultOption.getText());
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("^message indicates open account$")
    public void message_indicates_open_account() throws Throwable{
        String TextExpected = "Account Opened!";
        String actualText = driver.findElement(By.xpath("//h1[contains(text(),'Opened')]")).getText();
        Assert.assertEquals(TextExpected,actualText);
        savingAccount = driver.findElement(By.id("newAccountId")).getText();
        System.out.println("the account number is: "+savingAccount);
        driver.close();

    }

    @When("^user clicks on the transfer link$")
    public void user_clicks_on_the_transfer_link() throws Throwable {
        driver.findElement(By.linkText("Transfer Funds")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Funds')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toAccountId")));
    }

    @When("^user enters transfer details$")
    public void user_enters_transfer_details() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toAccountId")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fromAccountId")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("toAccountId")));
        Thread.sleep(2000);
        driver.findElement(By.id("amount")).sendKeys("495");
       WebElement selectAccount2 = driver.findElement(By.id("toAccountId"));
       Select select = new Select(selectAccount2);
       int numberOptions = select.getOptions().size();
       if(numberOptions > 1) {
           WebElement secondOption = select.getOptions().get(1);
           select.selectByVisibleText(secondOption.getText());
       }
    }

    @And("^user click transfer button$")
    public void user_click_transfer_button() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Transfer']")).click();
    }

    @Then("^message indicates Transer complete$")
    public void message_indicates_Transer_complete() throws Throwable {
        String TextExpected = "Transfer Complete!";
        String actualText = driver.findElement(By.xpath("//h1[contains(text(),'Complete!')]")).getText();
        Assert.assertEquals(TextExpected,actualText);
        Thread.sleep(2000);
        driver.findElement(By.linkText("Log Out")).click();
        driver.quit();
    }

    @When("^click logout link$")
    public void click_logout_link() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.linkText("Log Out")).click();
        driver.quit();
    }


}
