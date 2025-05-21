package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloudBeesTest extends BaseTest {

    @Test
    @Description("Open cloudbees web page and perform multiple validations by navigating till Documentation page")
    public void testCloudBeesFlow() throws InterruptedException {

        Allure.step("Click the Products link on top of the page");
        homePage.clickProducts();

        Allure.step("Click CloudBees CD/RO under Other Products");
        homePage.clickCdRo();

        Allure.step("Scroll till cost savings section");
        productsPage.scrollToCostSavings();

        Allure.step("Verify that Cost Savings has a value of $2m");
        Assert.assertTrue(productsPage.verifyCostSavingsValueIs$2m(), "Cost savings $2m not found");

        Allure.step("Click Auditors / Security tab");
        productsPage.clickAuditorsSecurity();

        Allure.step("Scroll till Release Governance section of Auditors / Security");
        productsPage.scrollToReleaseGovernance();

        Allure.step("Verify that the text under Release Governance is 'Generate single-click audit reports'.");
        Assert.assertTrue(productsPage.verifyGenerateSingleClickAuditReportsText(), "Generate single-click audit reports text under Release Governance not found");

        Allure.step("Click the Resources link on top of the page");
        homePage.clickResources();

        Allure.step("Click the Documentation link under Support & Documentation of the Resources");
        homePage.clickDocumentation();

        Allure.step("Verify that user is switched to new tab after clicking the Documentation link");
        Assert.assertTrue(documentationPage.verifySwitchedToNewTab(), "Could not switch to new tab:Documentation");

        Allure.step("Click on the Search all CloudBees Resources input search field");
        documentationPage.clickAllCloudbeesResourcesSearchBox();

        Allure.step("Verify that a full page modal opens after clicking the Search all CloudBees Resources input field");
        Assert.assertTrue(documentationPage.isFullPageModalOpened(), "No modal opened after clicking search box");

        Allure.step("Search for the 'Installation' keyword in the search box");
        documentationPage.searchInstallation();

        Allure.step("Verify that the Pagination options exists at bottom on search result");
        Assert.assertTrue(documentationPage.isPaginationDisplayedOnBottom(), "Pagination not visible on search result");
    }
}
