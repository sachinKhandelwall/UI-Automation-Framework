package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil util;

    private final By productsLink = By.xpath("//button[contains(text(), 'Products')]");
    private final By cdRoLink = By.xpath("//a[contains(text(), 'CloudBees CD/RO')]");
    private final By resourcesLink = By.xpath("//button[contains(text(), 'Resources')]");
    private final By documentationLink = By.xpath("//a[contains(text(), 'Documentation')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.util = new ElementUtil(driver);
    }

    public void clickProducts() {
        util.click(productsLink);
    }

    public void clickCdRo() {
        util.click(cdRoLink);
    }

    public void clickResources() {
        util.click(resourcesLink);
    }

    public void clickDocumentation() {
        util.click(documentationLink);
    }
}
