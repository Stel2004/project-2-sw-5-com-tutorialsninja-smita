package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.excelutility.ExcelUtility;
import com.tutorialsninja.demo.pages.DesktopPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Jay Vaghani
 */
public class DesktopsTestWithExcelData extends TestBase {

    public static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/java/resources/testdata/ExcelData.xlsx";

    HomePage homePage = new HomePage();
    DesktopPage desktopPage = new DesktopPage();

    @BeforeClass
    public void setExcel() {
        // Tell the code about the location of the Excel file
        try {
            ExcelUtility.setExcelFile(FILE_PATH, "LoginTests");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "dataFromExcel")
    public Object[][] getData(){
        return ExcelUtility.getTestData("Data_Set");
    }

    @Test(dataProvider = "dataFromExcel")
    public void verifyProductAddedToShoppingCartSuccessFully(String product, String qty, String successMessage, String productName, String model, String total){
        //2.1 Mouse hover on Currency Dropdown and click
        homePage.clickOnElement(desktopPage.currencyDropdown);
        //2.2 Mouse hover on £Pound Sterling and click
        homePage.clickOnElement(desktopPage.poundSterling);

        //2.3 Mouse hover on Desktops Tab.
        homePage.mouseHoverOnDesktopsLinkAndClick();

        // 2.4 Click on “Show All Desktops”
        homePage.selectMenu("Show AllDesktops");

        //2.5 Select Sort By position "Name: A to Z"
        desktopPage.sortByProducts(desktopPage.ascOrderText);

        //2.6 Select product “HP LP3065”
        desktopPage.clickOnElementByWebElements(desktopPage.selectOptionHP);
        String actualHpText = desktopPage.getVerificationText(desktopPage.HPLPText);
        Assert.assertEquals( actualHpText, "HP LP3065", "Option Matched");

        //2.7 Enter Qty <qty> using Select class.
        desktopPage.clearTextFromTag(desktopPage.clearText);
        //Thread.sleep(1000);
        desktopPage.sendTextToElement(desktopPage.clearText, "1");

        //2.8 Click on “Add to Cart” button
        desktopPage.clickOnElementByWebElements(desktopPage.addToCartPath);

        //2.9 Verify the Message <successMessage>
        String HPLPActualMessage = getTextFromElement(desktopPage.HPLPSuccessText);
        String[] actualMsg = HPLPActualMessage.split("×");
        actualMsg[0].trim();
        String finalMessage = actualMsg[0];
        String HPLPExpectedMessage = finalMessage + "×";
        Assert.assertEquals(HPLPActualMessage, HPLPExpectedMessage, "Message");

    }
}
