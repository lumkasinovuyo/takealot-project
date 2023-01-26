package tests;

import config.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.TakeAlotPage;

import java.time.Duration;

public class AddToCartTest {

    static WebDriver driver = new ChromeDriver();
    static JavascriptExecutor js = (JavascriptExecutor) driver;
    static Config config = new Config();
    static WebDriverWait wait = null;


    @BeforeClass
    public static void launchBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(config.url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(testName = "Add Item To Cart")
    public void addToCart() throws InterruptedException {
        TakeAlotPage page = new TakeAlotPage(driver);
        //Click on the load shedding tab
        page.load_shedding();
        Assert.assertEquals(driver.getTitle(), "Load Shedding Solutions | Buy all your Load Shedding Essentials at takealot.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Click on the GOT IT! cookies button
        page.got_it_btn();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //scroll down a bit so that the items are visible enough.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        page.load_shedding_item();
        //clicking on the add to cart button.
        page.add_to_cart();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        page.view_cart();
        Assert.assertEquals(driver.getTitle(),"Cart - TAKEALOT<");
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
