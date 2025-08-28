package utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ScreenshotUtil {

    public static File takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        File destFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(srcFile, destFile); // copies and creates folders if needed
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destFile;
    }


    public static void attachScreenshotToAllure(File screenshotFile) {
        try (FileInputStream fis = new FileInputStream(screenshotFile)) {
            Allure.addAttachment("Failure Screenshot", fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
