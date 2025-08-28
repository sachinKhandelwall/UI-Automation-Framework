package listeners;

import drivers.DriverFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        // name of the failure method
        String testName = result.getMethod().getMethodName();

        // take screenshot of the failure method
        File screenshot = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), testName);

        // attach the same screenshot of the failure method on Allure report
        ScreenshotUtil.attachScreenshotToAllure(screenshot);
    }

    // Other ITestListener methods can remain empty or used for logging
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
