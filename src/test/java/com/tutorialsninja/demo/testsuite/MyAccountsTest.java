package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyAccountsTest extends TestBase {

    HomePage homePage;
    DesktopPage desktopPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    MyAccountsPage myAccountsPage;
    ShoppingCartPage shoppingCartPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        shoppingCartPage = new ShoppingCartPage();
        desktopPage = new DesktopPage();
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        myAccountsPage = new MyAccountsPage();
        softAssert = new SoftAssert();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() throws InterruptedException {
        //1.1 Click on My Account Link.
        myAccountsPage.clickOnMyAccountLink();
        Thread.sleep(1000);
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter
        //“Register”
        myAccountsPage.selectMyAccountOptions(myAccountsPage.register);
        Thread.sleep(1000);
        //1.3 Verify the text “Register Account”.
        String expectedText = "Register Account";
        String actualText = desktopPage.getVerificationText(myAccountsPage.registerAccountText);
        softAssert.assertEquals(actualText, expectedText, "Not register account");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        //2.1 Click on My Account Link
        myAccountsPage.clickOnMyAccountLink();
        Thread.sleep(1000);
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Login”
        myAccountsPage.selectMyAccountOptions(myAccountsPage.login);
        Thread.sleep(1000);
        //2.3 Verify the text “Returning Customer”
        String expectedText = "Register Account";
        String actualText = desktopPage.getVerificationText(myAccountsPage.returningCustomerText);
        softAssert.assertEquals(actualText, expectedText, "Not register account");
    }

    @Test(groups = {"regression"})
    public void verifyThatUserRegisterAccountSuccessfully() throws InterruptedException {
        //3.1 Click on My Account Link
        myAccountsPage.clickOnMyAccountLink();
        Thread.sleep(1000);
        // 3.2 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Register”
        myAccountsPage.selectMyAccountOptions(myAccountsPage.register);
        Thread.sleep(1000);
        //3.3 Enter First Name
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.firstName, "Smita");
        //3.4 Enter Last Name
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.lastName, "Vaja" );
        //3.5 Enter Email
        int num = generateRandomNumber();
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.email, "smita+"+num+"@gmail.com");
        //3.6 Enter Telephone
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.mobile, "07438939393993");
        //3.7 Enter Password
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.password,"Admin@123");
        //3.8 Enter Password Confirm
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.confirmPassword, "Admin@123");
        //3.9 Select Subscribe Yes radio button
        myAccountsPage.selectYesRadioButton();
        //3.10 Click on Privacy Policy check box
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.termsAndAgree, "United Kingdom");
        Thread.sleep(1000);
        //3.11 Click on Continue button
        myAccountsPage.clickContinue();
        Thread.sleep(2000);
        //3.12 Verify the message “Your Account Has Been Created!”
        String expectedCreation = "Your Account Has Been Created!";
        //actual text
        String actualCreation = desktopPage.getVerificationText(myAccountsPage.registrationConfirmMsg);
        //3.12 Verify the message “Your Account Has Been Created!”
        softAssert.assertEquals(actualCreation, expectedCreation,"account not created");
        Thread.sleep(2000);
        //3.13 Click on Continue button
        myAccountsPage.clickingContinue();
        Thread.sleep(3000);
        //3.14 Click on My Account Link.
        myAccountsPage.clickOnMyAccountLinkReuse();
        Thread.sleep(3000);
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter
        //“Logout”
        myAccountsPage.selectMyAccountOptionsAfterLogin();
        Thread.sleep(2000);
        //3.16 Verify the text “Account Logout”
        String expectedLogoutText = "Account Logout";
        String actualLogoutText = myAccountsPage.accountLogoutText();
        softAssert.assertEquals(actualLogoutText, expectedLogoutText, "Logout not success");

        Thread.sleep(1000);
        //3.17 Click on Continue button
        myAccountsPage.clickOnFinalContinue();

        softAssert.assertAll();
    }

    @Test(groups = "regression")
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        //4.1 Click on My Account Link.
        myAccountsPage.clickOnMyAccountLink();
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter
        //“Login”
        myAccountsPage.selectMyAccountOptions(myAccountsPage.login);
        //4.3 Enter Email address
        int num = generateRandomNumber();
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.email, "smita+3665@gmail.com");
        //4.5 Enter Password
        laptopsAndNotebooksPage.enterTheMandatoryField(myAccountsPage.password,"Admin@123");

        //4.6 Click on Login button
        myAccountsPage.clickOnLogin();
        Thread.sleep(2000);
        //4.7 Verify text “My Account”
        String expectedMyAccountText = "My Account";
        String actualMyAccountText = myAccountsPage.myAccountText();
        Assert.assertEquals(actualMyAccountText, expectedMyAccountText, "Not on my account");

        //4.8 Click on My Account Link.
        myAccountsPage.clickOnMyAccountLinkReuse();
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter
        //“Logout”
        myAccountsPage.selectMyAccountOptionsAfterLogin();
        Thread.sleep(2000);
        //4.10 Verify the text “Account Logout”
        String expectedAccountLogoutText = "Account Logout";
        String actualAccountLogoutText = myAccountsPage.accountLogoutText();
        Assert.assertEquals(actualAccountLogoutText, expectedAccountLogoutText, "Not logged out");

        Thread.sleep(2000);
        //4.11 Click on Continue button
        myAccountsPage.clickOnFinalContinue();
    }


}
