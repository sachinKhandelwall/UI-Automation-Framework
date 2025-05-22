package base;
import io.qameta.allure.Allure;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.LogUtils;
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
                LogUtils.info("Launching Firefox browser");
                driver = new FirefoxDriver();
                break;
            case "edge":
                LogUtils.info("Launching Edge browser");
                driver = new EdgeDriver();
                break;
            case "safari":
                LogUtils.info("Launching Safari browser");
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                LogUtils.info("Launching Chrome browser");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        LogUtils.info("Open the cloudbees web application");
        Allure.step("Open the cloudbees web application");
        driver.get(cloudbeesUrl);

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        documentationPage = new DocumentationPage(driver);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            LogUtils.info("Closing the browser");
            Allure.step("Closing the browser");
            driver.quit();
        }
    }
}
