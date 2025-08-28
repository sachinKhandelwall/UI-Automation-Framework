package base;

import drivers.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.*;
import utils.LogUtils;
import utils.PropertyUtils;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected ProductsPage productsPage;
    protected DocumentationPage documentationPage;

    private final String cloudbeesUrl = PropertyUtils.getProperty("url");
    private final String browser = PropertyUtils.getProperty("browser");

    @BeforeClass
    public void setupDriver() {
        driver = DriverFactory.initDriver(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        LogUtils.info("Launching browser: " + browser);
        Allure.step("Launching browser: " + browser);

        // init page objects
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        documentationPage = new DocumentationPage(driver);
    }

    @BeforeMethod
    public void openApplication() {
        LogUtils.info("Opening CloudBees web app");
        Allure.step("Opening CloudBees web app");
        driver.get(cloudbeesUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUpAfterEachTest() {
        LogUtils.info("Clearing cookies after test");
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        DriverFactory.quitDriver();
    }
}
