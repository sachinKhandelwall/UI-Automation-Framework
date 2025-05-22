package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementUtil;
import utils.LogUtils;

import java.util.List;
import java.util.Set;

public class DocumentationPage {

    private WebDriver driver;
    private ElementUtil util;

    private final By searchAllCloudbeesResources = By.xpath("//input[@placeholder='Search all CloudBees Resources']");
    private final By searchBox = By.xpath("//input[@placeholder='Search']");
    private final String installationKeyword = "Installation";
    private final By paginationElements = By.cssSelector("ul.pagination li.page-item");
    private List<WebElement> paginationItems;

    public DocumentationPage(WebDriver driver) {
        this.driver = driver;
        this.util = new ElementUtil(driver);
    }

    public boolean verifySwitchedToNewTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        if (allWindows.size() <= 1) {
            return false; // No new tab found
        }

        for (String handle : allWindows) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                return true; // successfully switched to new tab
            }
        }

        return false; // fallback, should not be reached
    }


    public void clickAllCloudbeesResourcesSearchBox() {
        util.click(searchAllCloudbeesResources);
    }

    public boolean isFullPageModalOpened() {
        try {
            return driver.findElement(By.tagName("body")).getAttribute("class").contains("modal-open");
        } catch (Exception e) {
            LogUtils.error("Failed to verify modal open state: " + e.getMessage());
            return false;
        }
    }


    public void searchInstallation() {
        util.sendKeys(searchBox, installationKeyword + "\n");
    }

    public boolean isPaginationDisplayedOnBottom() {
        try {
             paginationItems = util.waitForElementsVisible(paginationElements);
            if (paginationItems.size() > 1) {
                LogUtils.info("Pagination exists with " + paginationItems.size() + " pages.");
                return true;
            } else {
                LogUtils.warn("Pagination not found or only one page exists.");
                return false;
            }
        } catch (Exception e) {
            LogUtils.error("Error while checking pagination: " + e.getMessage());
            return false;
        }
    }

}
