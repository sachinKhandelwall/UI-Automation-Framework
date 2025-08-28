package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil util;

    private final By productsLink = By.xpath("//button[normalize-space()='Product']");
    private final By releaseOrchestration = By.xpath("//a[@aria-label='Release Orchestration'][normalize-space()='Release Orchestration']");
    private final By resourcesLink = By.xpath("//button[contains(text(), 'Resources')]");
    private final By documentationLink = By.xpath("//a[contains(text(), 'Documentation')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.util = new ElementUtil(driver);
    }

    public void clickProducts() {
        util.click(productsLink);
    }

    public void clickReleaseOrchestration() {
        util.click(releaseOrchestration);
    }

    public void clickResources() {
        util.click(resourcesLink);
    }

    public void clickDocumentation() {
        util.click(documentationLink);
    }
}
