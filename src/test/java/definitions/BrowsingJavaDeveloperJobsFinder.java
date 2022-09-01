package definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;

public class BrowsingJavaDeveloperJobsFinder {
    WebDriver driver;

    @Before
    public void setupConfig(){
        //driver = new SafariDriver();

        String props = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"webdriver"+File.separator+"geckodriver";
        System.out.println(props);
        System.setProperty("webdriver.gecko.driver",props);

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("Open the browser")
    public void openTheBrowser() throws InterruptedException {
        driver.get("http://www.jobstreet.com");
        Thread.sleep(3000);
    }

    @And("Click Country {string}")
    public void browseTheJobstreet(String country) throws InterruptedException {
        String xpathSingapore = "/html/body/div/div/div/div[2]/div/div/div/div/div[2]/div/div[2]/a/div/div/div[2]/span";
        System.out.println(country);
        driver.findElement(By.xpath(xpathSingapore)).click();
        Thread.sleep(3000);
    }

    @When("search {string}")
    public void searchJobsByKeyword(String jobSearch) throws InterruptedException {
        driver.findElement(By.name("key")).sendKeys(jobSearch);
        driver.findElement(By.name("key")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @Then("showing the result jobs")
    public void showingResultSearchJobs(){
        String resultJobs[] = driver.findElement(By.xpath("//strong[contains(@class, '_18qlyvc3')]")).getText().split("-");
        //String resultJobs = driver.findElement(By.xpath("Expression\t/html/body/div[1]/div/div/div[2]/div[2]/div/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div/span/strong/following-sibling::text()")).getText().replace(" jobs ","").replace("of ", "").replace(",","");
        assert Integer.parseInt(resultJobs[1])>1;
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
