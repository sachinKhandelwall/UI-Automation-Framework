package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LogUtils;

public class CloudBeesTest extends BaseTest {

    @Test
    @Description("Open cloudbees web page and perform multiple validations by navigating till Documentation page")
    public void testCloudBeesFlow() throws InterruptedException {

        LogUtils.info("Click the Products link on top of the page");
        Allure.step("Click the Products link on top of the page");
        homePage.clickProducts();

        LogUtils.info("Click CloudBees CD/RO under Other Products");
        Allure.step("Click CloudBees CD/RO under Other Products");
        homePage.clickCdRo();

        LogUtils.info("Scroll till cost savings section");
        Allure.step("Scroll till cost savings section");
        productsPage.scrollToCostSavings();

        LogUtils.info("Verify that Cost Savings has a value of $2m");
        Allure.step("Verify that Cost Savings has a value of $2m");
        Assert.assertTrue(productsPage.verifyCostSavingsValueIs$2m(), "Cost savings $2m not found");

        LogUtils.info("Click Auditors / Security tab");
        Allure.step("Click Auditors / Security tab");
        productsPage.clickAuditorsSecurity();

        LogUtils.info("Scroll till Release Governance section of Auditors / Security");
        Allure.step("Scroll till Release Governance section of Auditors / Security");
        productsPage.scrollToReleaseGovernance();

        LogUtils.info("Verify that the text under Release Governance is 'Generate single-click audit reports'.");
        Allure.step("Verify that the text under Release Governance is 'Generate single-click audit reports'.");
        Assert.assertTrue(productsPage.verifyGenerateSingleClickAuditReportsText(), "Generate single-click audit reports text under Release Governance not found");

        LogUtils.info("Click the Resources link on top of the page");
        Allure.step("Click the Resources link on top of the page");
        homePage.clickResources();

        LogUtils.info("Click the Documentation link under Support & Documentation of the Resources");
        Allure.step("Click the Documentation link under Support & Documentation of the Resources");
        homePage.clickDocumentation();

        LogUtils.info("Verify that user is switched to new tab after clicking the Documentation link");
        Allure.step("Verify that user is switched to new tab after clicking the Documentation link");
        Assert.assertTrue(documentationPage.verifySwitchedToNewTab(), "Could not switch to new tab:Documentation");

        LogUtils.info("Click on the Search all CloudBees Resources input search field");
        Allure.step("Click on the Search all CloudBees Resources input search field");
        documentationPage.clickAllCloudbeesResourcesSearchBox();

        LogUtils.info("Verify that a full page modal opens after clicking the Search all CloudBees Resources input field");
        Allure.step("Verify that a full page modal opens after clicking the Search all CloudBees Resources input field");
        Assert.assertTrue(documentationPage.isFullPageModalOpened(), "No modal opened after clicking search box");

        LogUtils.info("Search for the 'Installation' keyword in the search box");
        Allure.step("Search for the 'Installation' keyword in the search box");
        documentationPage.searchInstallation();

        LogUtils.info("Verify that the Pagination options exists at bottom on search result");
        Allure.step("Verify that the Pagination options exists at bottom on search result");
        Assert.assertTrue(documentationPage.isPaginationDisplayedOnBottom(), "Pagination not visible on search result");
    }
}
