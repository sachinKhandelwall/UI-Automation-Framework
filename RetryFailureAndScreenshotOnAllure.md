## 🔁 BaseTest Class where driver is initialized

```java
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
```

## 🔁 Retry Failed Tests & Attach Screenshot to Allure Report (Ultra Simplified with Inheritance)

This setup:
- Retries failed test cases
- Captures screenshots on failure
- Attaches to Allure
- Uses clean inheritance (`extends BaseTest`)

---

### 📁 `listeners/RetryOnFailureWithAllureScreenshotListener.java`

```java
package listeners;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.IAnnotationTransformer;
import utils.RetryAnalyzer;

import java.io.*;

public class RetryOnFailureWithAllureScreenshotListener extends BaseTest implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try (FileInputStream file = new FileInputStream(src)) {
                    Allure.addAttachment("Failure Screenshot", file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Optional: other unused methods
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
```

---

### 📁 `utils/RetryAnalyzer.java`

```java
package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempt = 0;
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < MAX_RETRY) {
            attempt++;
            return true;
        }
        return false;
    }
}
```

---

### ⚙️ `testng.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="CloudBeesSuite">
    <listeners>
        <listener class-name="listeners.RetryOnFailureWithAllureScreenshotListener"/>
    </listeners>
    <test name="CloudBeesTests">
        <packages>
            <package name="your.test.package.name"/>
        </packages>
    </test>
</suite>
```

---



