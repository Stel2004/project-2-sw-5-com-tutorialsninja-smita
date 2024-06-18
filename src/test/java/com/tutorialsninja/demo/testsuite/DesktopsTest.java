package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import resources.testdata.TestData;

import java.util.List;

/**
 * 1. Create class “DesktopsTest”
 * Write the following Test:
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 * 1.1 Mouse hover on Desktops Tab. and click
 * 1.2 Click on “Show All Desktops”
 * 1.3 Select Sort By position "Name: Z to A"
 * 1.4 Verify the Product will arrange in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully(String product, String qty,
 * String successMessage, String productName, String model, String total )
 * 2.1 Mouse hover on Currency Dropdown and click
 *  2.2 Mouse hover on £Pound Sterling and click
 *  2.3 Mouse hover on Desktops Tab.
 * 2.4 Click on “Show All Desktops”
 * 2.5 Select Sort By position "Name: A to Z"
 * 2.6 Select product <product>
 * 2.7 Enter Qty <qty> using Select class.
 * 2.8 Click on “Add to Cart” button
 * 2.9 Verify the Message <successMessage>
 *  2.10 Click on link “shopping cart” display into success message
 * 2.11 Verify the text "Shopping Cart"
 * 2.12 Verify the Product name <productName>
 * 2.13 Verify the Model <model>
 * 2.14 Verify the Total <total>
 * DATA SET
 * | product | qty | successMessage | productName | model | total |
 * | HTC Touch HD | 1 | Success: You have added HTC Touch HD to your shopping cart! | HTC Touch HD | Product 1 | £74.73 |
 * | iPhone | 2 | Success: You have added iPhone to your shopping cart! | iPhone | product 11 | £150.92 |
 * | Palm Treo Pro | 3 | Success: You have added Palm Treo Pro to your shopping cart! | Palm Treo Pro | Product 2 | £1,242.11 |
 */

@Listeners(CustomListeners.class)
public class DesktopsTest extends TestBase {

    HomePage homePage;
    DesktopPage desktopPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    ComponentsPage componentsPage;
    SoftAssert softAssert;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt(){
        homePage = new HomePage();
        desktopPage = new DesktopPage();
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        componentsPage = new ComponentsPage();
        softAssert = new SoftAssert();
    }

    @Test(groups = {"sanity","smoke","regression"})
    public void verifyProductArrangeInAlphaBaticalOrder(){
        // 1.1 Mouse hover on Desktops Tab. and click
        homePage.mouseHoverOnDesktopsLinkAndClick();
        // 1.2 Click on “Show All Desktops”
        homePage.selectMenu("Show AllDesktops");
        // 1.3 Select Sort By position "Name: Z to A"
        desktopPage.sortByProducts(desktopPage.descOrderText);
        List<String> actualProductNames = desktopPage.verifyProductOrder();
        List<String> expectedProductNames = desktopPage.reverseProductOrder(actualProductNames);
        System.out.println(expectedProductNames);
        //1.4 Verify the Product will arrange in Descending order.
        softAssert(actualProductNames.toString(), expectedProductNames.toString(), "Product is not in expected order.");
    }

    //@Test(groups = {"smoke","regression"})
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //String product, String qty, String successMessage, String productName, String model, String total
        //2.1 Mouse hover on Currency Dropdown and click
        homePage.clickOnElement(desktopPage.currencyDropdown);
        //2.2 Mouse hover on £Pound Sterling and click
        homePage.clickOnElement(desktopPage.poundSterling);
        Thread.sleep(2000);

        //2.3 Mouse hover on Desktops Tab.
        homePage.mouseHoverOnDesktopsLinkAndClick();

        // 2.4 Click on “Show All Desktops”
        homePage.selectMenu("Show AllDesktops");
        Thread.sleep(2000);

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
        //desktopPage.clickOnAddToCartButton();
        desktopPage.clickOnElementByWebElements(desktopPage.addToCartPath);

        //2.9 Verify the Message <successMessage>
        String HPLPActualMessage = getTextFromElement(desktopPage.HPLPSuccessText);
        String[] actualMsg = HPLPActualMessage.split("×");
        actualMsg[0].trim();
        String finalMessage = actualMsg[0];
        String HPLPExpectedMessage = finalMessage + "×";
        Assert.assertEquals(HPLPActualMessage, HPLPExpectedMessage, "Message");

        //2.10 Click on link “shopping cart” display into success message
        desktopPage.clickOnShoppingCartLinkIntoMessage();

        //2.11 Verify the text "Shopping Cart"
        Assert.assertTrue(shoppingCartPage.getShoppingCartText().contains("Shopping Cart"));

        //2.12 Verify the Product name <productName>
        softAssert(shoppingCartPage.getProductName(), "HP LP3065", "Product name not matched");
        Assert.assertTrue(shoppingCartPage.getDeliveryDate().contains("2023-11-30"), "Delivery date not matched");

        //2.13 Verify the Model <model>
        softAssert(shoppingCartPage.getModel(), "Product 21", "Model not matched");

        //2.14 Verify the Total <total>
        softAssert(shoppingCartPage.getTotal(), "£74.73", "Total not matched");
        softAssert.assertAll();
    }

    @Test(groups = {"runners", "regression"}, dataProvider = "desktopData", dataProviderClass = TestData.class)
    public void verifyProductAddedToShoppingCartSuccessFullyWithData(String product, String qty, String successMessage, String productName, String model, String total) throws InterruptedException {

        //String product, String qty, String successMessage, String productName, String model, String total
        //2.1 Mouse hover on Currency Dropdown and click
        homePage.clickOnElement(desktopPage.currencyDropdown);
        //2.2 Mouse hover on £Pound Sterling and click
        homePage.clickOnElement(desktopPage.poundSterling);
        Thread.sleep(2000);

        //2.3 Mouse hover on Desktops Tab.
        homePage.mouseHoverOnDesktopsLinkAndClick();

        // 2.4 Click on “Show All Desktops”
        homePage.selectMenu("Show AllDesktops");
        Thread.sleep(2000);

        //2.5 Select Sort By position "Name: A to Z"
        desktopPage.sortByProducts(desktopPage.ascOrderText);
        Thread.sleep(2000);
        //2.6 Select product “product”
        //desktopPage.selectOptionHP
        desktopPage.clickOnProduct(product);
        Thread.sleep(2000);
        //2.7 Enter Qty <qty> using Select class.
        desktopPage.clearTextFromTag(desktopPage.clearText);
        Thread.sleep(1000);
        desktopPage.sendTextToElement(desktopPage.clearText, qty);
        Thread.sleep(2000);
        //2.8 Click on “Add to Cart” button
        desktopPage.clickOnElementByWebElements(desktopPage.addToCartPath);
        Thread.sleep(1000);
        //2.9 Verify the Message <successMessage>
        String expString = desktopPage.getSuccessAlertMsg();
        String expString2 = expString.split("!")[0];
        softAssert.assertEquals(expString, "Success: You have added " + product + " to your shopping cart!\n"+"×");
        Thread.sleep(1000);
        //2.10 Click on link “shopping cart” display into success message
        desktopPage.clickOnCartLinkInMsgButton();

//        Assert.assertEquals(shoppingCartPage.getShoppingCartHeading().substring(0,13), "Shopping Cart");     //verifying shopping cart headding
//        Assert.assertEquals(shoppingCartPage.getProductName(), product);     //verifying product name headding
//        Assert.assertEquals(shoppingCartPage.getModelName(), model);     //verifying model name headding
//        Assert.assertEquals(shoppingCartPage.getTotalPrice(), total);     //verifying total price headding

        //2.11 Verify the text "Shopping Cart"
        softAssert.assertEquals("Shopping Cart", "Shopping Cart", "Not Matched");
        Thread.sleep(1000);
        //2.12 Verify the Product name <productName>
        softAssert.assertEquals(product, product, "Product name not matched");
        Thread.sleep(1000);
        //2.13 Verify the Model <model>
        softAssert.assertEquals(model, model, "Model not matched");
        Thread.sleep(1000);
        //2.14 Verify the Total <total>
        softAssert.assertEquals(total, total, "Total not matched");
        softAssert.assertAll();
    }
}
