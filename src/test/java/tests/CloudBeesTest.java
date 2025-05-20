package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloudBeesTest extends BaseTest {

    @Test
    public void testCloudBeesFlow() throws InterruptedException {
        homePage.clickProducts();
        homePage.clickCdRo();

        productsPage.scrollToCostSavings();
        Assert.assertTrue(productsPage.verifyCostSavingsValueIs$2m(), "Cost savings $2m not found");

        //productsPage.scrollToAuditorsSecurity();
        productsPage.clickAuditorsSecurity();
        productsPage.scrollToReleaseGovernance();
        Assert.assertTrue(productsPage.verifyGenerateSingleClickAuditReportsText(), "Generate single-click audit reports text under Release Governance not found");

        homePage.clickResources();
        homePage.clickDocumentation();
        Assert.assertTrue(documentationPage.verifySwitchedToNewTab(), "Could not switch to new tab:Documentation");

        documentationPage.clickSearchBox();
        Assert.assertTrue(documentationPage.isFullPageModalOpened(), "No modal opened after clicking search box");

        documentationPage.searchInstallation();
        Assert.assertTrue(documentationPage.isPaginationDisplayedOnBottom(), "Pagination not visible on search result");
    }
}
