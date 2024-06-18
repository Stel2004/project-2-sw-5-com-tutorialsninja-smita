package com.tutorialsninja.demo.pages;

import com.aventstack.extentreports.Status;
import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create the class LaptopsAndNotebooksTest
 * Write the following test
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
 * 1.2 Click on “Show All Laptops & Notebooks”
 * 1.3 Select Sort By "Price (High > Low)"
 * 1.4 Verify the Product price will arrange in High to Low order.
 * 2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on Laptops & Notebooks Tab and click
 * 2.2 Click on “Show All Laptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on link “shopping cart” display into success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change Quantity "2"
 * 2.12 Click on “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on “Checkout” button
 * 2.16 Verify the text “Checkout”
 * 2.17 Verify the Text “New Customer”
 * 2.18 Click on “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */
public class LaptopsAndNotebooksPage extends Utility {

    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'Laptops & Notebooks')]")
    WebElement laptopsAndNotebooksText;

    @CacheLookup
    @FindBy(xpath = "//p[@class ='price']")
    List<WebElement> productsPrices;

    @CacheLookup
    @FindBy(xpath = "//h4/a")
    List<WebElement> productsList;

    @CacheLookup
    @FindBy(id = "input-sort")
    WebElement sortBy;

    @CacheLookup
    @FindBy(xpath = "//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//div[1]//div[2]//div[1]//p//span[@class='price-tax']")
    By productPrice;

    @CacheLookup
    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    public WebElement clickOnCheckout;

    @CacheLookup
    @FindBy(xpath = "//h1[normalize-space()='Checkout']")
    public WebElement verifyTheTextCheckout;

    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'New Customer')]")
    public WebElement verifyTheTxtNewCustomer;

    @CacheLookup
    @FindBy(xpath = "//input[@value='guest']")
    public WebElement clickOnGuestCheckout;

    @CacheLookup
    @FindBy(xpath = "//input[@id='button-account']")
    public WebElement ClickOnContinue;

    @CacheLookup
    @FindBy(id = "input-payment-firstname")
    public WebElement firstName;

    @CacheLookup
    @FindBy(id = "input-payment-lastname")
    public WebElement lastName;

    @CacheLookup
    @FindBy(id = "input-payment-email")
    public WebElement eMail;

    @CacheLookup
    @FindBy(id = "input-payment-telephone")
    public WebElement telephone;

    @CacheLookup
    @FindBy(id = "input-payment-address-1")
    public WebElement Address1;

    @CacheLookup
    @FindBy(id = "input-payment-city")
    public WebElement city;

    @CacheLookup
    @FindBy(id = "input-payment-postcode")
    public WebElement postcode;

    @CacheLookup
    @FindBy(id = "input-payment-zone")
    public WebElement state;

    @CacheLookup
    @FindBy(id = "button-guest")
    public WebElement clickOnCon;

    @CacheLookup
    @FindBy(id = "button-shipping-method")
    public WebElement continueButtonAfterComment;

    @CacheLookup
    @FindBy(name = "comment")
    public WebElement enterComment;

    @CacheLookup
    @FindBy(id = "button-account")
    WebElement checkoutOptContButton;

    @CacheLookup
    @FindBy(xpath = "//input[@name='agree']")
    WebElement agreeTermsConditionCheckbox;

    @CacheLookup
    @FindBy(id = "button-payment-method")
    WebElement paymentMethodContinueButton;

    @CacheLookup
    @FindBy(id = "button-confirm")
    WebElement confirmButton;

    @CacheLookup
    @FindBy(xpath = "//h1[text()='Your order has been placed!']")
    WebElement orderPlacedText;

    public String getLaptopsAndNotebooksText() {
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Get text " + laptopsAndNotebooksText);
        Reporter.log("Get text " + laptopsAndNotebooksText.toString());
        return getTextFromElement(laptopsAndNotebooksText);
    }

    public ArrayList<Double> getProductsPriceList() {
        List<WebElement> products = getListOfElements(productsPrices);
        ArrayList<Double> originalProductsPrice = new ArrayList<>();
        for (WebElement e : products) {
            //System.out.println(e.getText());
            String[] arr = e.getText().split("Ex Tax:");
            //System.out.println(arr[0]);
            originalProductsPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Get product prices " + productsPrices);
        Reporter.log("Get product prices " + productsPrices.toString());
        return originalProductsPrice;
    }

    public void selectSortByOption(String option) {
        selectByVisibleTextFromDropDown(sortBy, option);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Select option " + option);
        Reporter.log("Select option from " + sortBy.toString());
    }

    public void selectProductByName(String product) {
        List<WebElement> products = getListOfElements(productsList);
        for (WebElement e : products) {
            if (e.getText().equals(product)) {
                e.click();
                break;
            }
        }
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Select option " + product);
        Reporter.log("Select "+product+" from " + productsList.toString());
    }

    //Convert list to descending order
    public List<Double> getProductPricesInDefaultFilterAndSortByDescendingOrder() {
        return convertPriceListToReverse(productPrice);
    }

    //Get list after applying filter
    public List<Double> getProductPriceAfterFilterHighToLow() {
        return afterFilterPrice(productPrice);
    }

    public void enterTheMandatoryField(WebElement element, String value){
        sendTextToElement(element, value);
    }

    public void enterState() {
        selectByValueFromDropDown(state, "3553");
    }

    public void clickOnCheckoutOptionContinueButton() {
        Reporter.log("click on " + checkoutOptContButton.toString());
        CustomListeners.test.log(Status.PASS, "click on " + checkoutOptContButton);
        clickOnElement(checkoutOptContButton);
    }

    public void clickOnAgreeTermsConditionCheckbox() {
        Reporter.log("click on " + agreeTermsConditionCheckbox.toString());
        CustomListeners.test.log(Status.PASS, "click on " + agreeTermsConditionCheckbox);
        clickOnElement(agreeTermsConditionCheckbox);
    }

    public void clickOnPaymentMethodCont() {
        Reporter.log("click on " + paymentMethodContinueButton.toString());
        CustomListeners.test.log(Status.PASS, "click on " + paymentMethodContinueButton);
        clickOnElement(paymentMethodContinueButton);
    }

    public void clickOnContinueButton() {
        clickOnElement(clickOnCon);
    }

    public void clickOnContinueAfterComment() {
        clickOnElement(continueButtonAfterComment);
    }

    public void enterComment() {
        sendTextToElement(enterComment, "I want a macbook");
    }

    public void clickOnConfirmButton() {
        clickOnElement(confirmButton);
    }

    public String verifyOrderPlaced() {
        return getTextFromElement(orderPlacedText);
    }


}
