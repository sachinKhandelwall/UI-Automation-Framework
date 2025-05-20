package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;

public class ProductsPage {

    private WebDriver driver;
    private ElementUtil util;

    private final By costSavingsValue = By.xpath("//div[normalize-space()='$2m'][ancestor::div[p[normalize-space()='Cost Savings']]]");
    private final By auditorsSecurity = By.xpath("//span[normalize-space()='Auditors / Security']");
    private final By textUnderReleaseGovernance = By.xpath("//p[contains(text(),'Release Governance')]/following::h4[contains(text(),'Generate single-click audit reports')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.util = new ElementUtil(driver);
    }

    public void scrollToCostSavings() {
        util.scrollToElement(costSavingsValue); // Scroll into view first
    }

    public boolean verifyCostSavingsValueIs$2m() {
        return util.isElementDisplayed(costSavingsValue);
    }

    public void scrollToAuditorsSecurity() {
        util.scrollToElement(auditorsSecurity); // Scroll into view first
    }

    public void clickAuditorsSecurity() {
        util.click(auditorsSecurity);
    }

    public void scrollToReleaseGovernance() {
        util.scrollToElement(textUnderReleaseGovernance); // Scroll into view first
    }

    public boolean verifyGenerateSingleClickAuditReportsText() {
        return util.isElementDisplayed(textUnderReleaseGovernance);
    }
}
