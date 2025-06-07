## 🔁 Retry Failed Test Cases & Attach Allure Screenshots on Failure (TestNG + Selenium)

This setup retries failed test cases and captures screenshots on failure, attaching them to the Allure report.

### ✅ Features
- Retries failed tests (max 2 attempts)
- Takes screenshot on final failure
- Attaches screenshot to Allure report
- Configured via TestNG `@Listeners`

---

### 📁 `listeners/RetryOnFailureWithAllureScreenshotListener.java`

```java
package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import utils.RetryAnalyzer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryOnFailureWithAllureScreenshotListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        WebDriver driver = extractDriver(testInstance);
        if (driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try (FileInputStream fis = new FileInputStream(screenshot)) {
                    Allure.addAttachment("Screenshot on Failure", fis);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Optional overrides
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

### 📁 `base/BaseTest.java`

```java
package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    public WebDriver driver; // Must be public for listener access

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // initialize WebDriver
    }

    @AfterMerhod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

---

### ⚙️ `testng.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="MySuite" verbose="1" parallel="false">
    <listeners>
        <listener class-name="listeners.RetryOnFailureWithAllureScreenshotListener"/>
    </listeners>

    <test name="MyTests">
        <packages>
            <package name="your.test.package.name"/>
        </packages>
    </test>
</suite>
```

---

### ✅ Notes
- Adjust `MAX_RETRY` in `RetryAnalyzer` if you need more/less retries.
- Allure screenshots are visible under **Attachments** in the HTML report.
