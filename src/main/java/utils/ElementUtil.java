package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ElementUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement waitForElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LogUtils.error("Element not visible: " + locator);
            return null;
        }
    }

    public List<WebElement> waitForElementsVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            LogUtils.error("Elements not visible: " + locator);
            return java.util.Collections.emptyList();
        }
    }

    public WebElement waitForElementClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LogUtils.error("Element not clickable: " + locator);
            return null;
        }
    }

    public boolean click(By locator) {
        try {
            WebElement element = waitForElementClickable(locator);
            if (element != null) {
                element.click();
                return true;
            }
        } catch (Exception e) {
            LogUtils.error("Unable to click: " + locator + " - " + e.getMessage());
        }
        return false;
    }

    public boolean isElementDisplayed(By locator) {
        WebElement element = waitForElementVisible(locator);
        return element != null && element.isDisplayed();
    }

    public void scrollToElement(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            if (element != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            }
        } catch (Exception e) {
            LogUtils.error("Unable to scroll: " + locator + " - " + e.getMessage());
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            WebElement element = waitForElementVisible(locator);
            if (element != null) {
                element.clear();
                element.sendKeys(text);
            }
        } catch (Exception e) {
            LogUtils.error("Unable to send keys to: " + locator + " - " + e.getMessage());
        }
    }
}
