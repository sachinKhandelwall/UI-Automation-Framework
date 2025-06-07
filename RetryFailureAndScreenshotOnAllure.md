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
                try (InputStream is = new FileInputStream(src)) {
                    Allure.addAttachment("Failure Screenshot", is);
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


