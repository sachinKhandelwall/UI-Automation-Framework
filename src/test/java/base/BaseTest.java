package base;
import io.qameta.allure.Allure;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.PropertyUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import pages.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected ProductsPage productsPage;
    protected DocumentationPage documentationPage;
    String cloudbeesUrl = PropertyUtils.getProperty("url");

    @BeforeClass
    public void setup() {
        String browser = PropertyUtils.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        browser = browser.toLowerCase();
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        Allure.step("Open the cloudeees web application");
        driver.get(cloudbeesUrl);

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        documentationPage = new DocumentationPage(driver);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            Allure.step("Closing the browser");
            driver.quit();
        }
    }
}
