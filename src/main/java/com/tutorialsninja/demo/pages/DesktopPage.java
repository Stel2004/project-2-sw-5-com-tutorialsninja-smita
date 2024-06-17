package com.tutorialsninja.demo.pages;

import com.aventstack.extentreports.Status;
import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopPage extends Utility {

    public String descOrderText = "Name (Z - A)";
    public String ascOrderText = "Name (A - Z)";

    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'Desktops')]")
    WebElement desktopsText;

    @CacheLookup
    @FindBy(xpath = "//select[@id='input-sort']")
    WebElement sortByOptions;

    @CacheLookup
    @FindBy(xpath = "//div[@class='caption']//h4/a")
    List<WebElement> productsList;

    @CacheLookup
    @FindBy(id = "input-sort")
    WebElement sortBy;

    @CacheLookup
    @FindBy(linkText = "HP LP3065")
    public WebElement selectOptionHP;

    @CacheLookup
    @FindBy(xpath = "//input[@id='input-quantity']")
    public WebElement clearText;

    @CacheLookup
    @FindBy(id = "button-cart")
    public WebElement addToCartPath;

    @CacheLookup
    @FindBy(xpath = "//h1[normalize-space()='HP LP3065']")
    public WebElement HPLPText;

    @CacheLookup
    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    public WebElement HPLPSuccessText;

    @CacheLookup
    @FindBy(linkText = "shoppingCart")
    public WebElement shoppingCart;

    @CacheLookup
    @FindBy(xpath = "//body/div/div/div/h1[1]")
    public WebElement shoppingCartMessage;

    @CacheLookup
    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")
    public WebElement actualHPText;

    @CacheLookup
    @FindBy(xpath = "//td[normalize-space()='Product 21']")
    public WebElement productMessage;

    @CacheLookup
    @FindBy(xpath = "(//td[contains(text(),'£74.73')])[4]")
    public WebElement totPrice;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='shopping cart']")
    WebElement shoppingCartLink;

    @CacheLookup
    @FindBy(xpath = "//button[@class='btn btn-link dropdown-toggle']")
    public WebElement currencyDropdown;

    @CacheLookup
    @FindBy(xpath = "//button[normalize-space()='£Pound Sterling']")
    public WebElement poundSterling;

    @CacheLookup
    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successAlertMsg;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='shopping cart']")
    WebElement cartLinkInMsg;


    public String getDesktopsText() {
        // Report and Logs
        CustomListeners.test.log(Status.PASS, "Get desktop text " + desktopsText);
        Reporter.log("Get desktop text" + desktopsText.toString());
        return getTextFromElement(desktopsText);
    }

    public void sortByProducts(String orderByText){
        selectByVisibleTextFromDropDown(sortBy, orderByText);
        // Reports and Log
        CustomListeners.test.log(Status.PASS, "Select " + orderByText);
        Reporter.log("Select " + orderByText + " to sort by " + sortBy.toString());
    }

    public List<String> verifyProductOrder(){
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement productName : getListOfElements(productsList)) {
            actualProductNames.add(productName.getText());
        }
        // Report and Log
        CustomListeners.test.log(Status.PASS, "Get all product " + productsList);
        Reporter.log("Get all products " + productsList);
        return actualProductNames;
    }

    public List<String> reverseProductOrder(List<String> actualProductNames){
        List<String> expectedProducts = new ArrayList<>(actualProductNames);
        expectedProducts.sort(Collections.reverseOrder());
        actualProductNames.sort(Collections.reverseOrder());
        // Report and Log
        CustomListeners.test.log(Status.PASS, "Get all product in reverse order " + expectedProducts);
        Reporter.log("Get all product in reverse order " + expectedProducts.toString());
        return expectedProducts;
    }

    public void clickOnElementByWebElements(WebElement element){
        clickOnElement(element);
        CustomListeners.test.log(Status.PASS,"Click on " + element);
        Reporter.log("Click on " + element.toString());
    }

    //Get text method
    public String getVerificationText(WebElement element) {
        return getTextFromElement(element);
    }

    public void clearTextFromTag(WebElement element){
        clearText(element);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Clear on " + element);
        Reporter.log("Clear the text on " + element.toString());
    }

    public void clickOnShoppingCartLinkIntoMessage() {
        clickOnElement(shoppingCartLink);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Click on shopping cart " + shoppingCartLink);
        Reporter.log("Click on shopping cart " + shoppingCartLink.toString());
    }

    public void clickOnProduct(String product) {
        List<WebElement> products = productsList;
        for (WebElement e : products) {
            if (e.getText().equalsIgnoreCase(product)) {
                e.click();
                break;
            }
        }
        CustomListeners.test.log(Status.PASS, "Click on " + product);
        Reporter.log("Click on " + product);
    }

    public String getSuccessAlertMsg() {
        Reporter.log("Get " + successAlertMsg.toString());
        CustomListeners.test.log(Status.PASS, "Get " + successAlertMsg);
        return getTextFromElement(successAlertMsg);
    }

    public void clickOnCartLinkInMsgButton() {
        clickOnElement(cartLinkInMsg);
        Reporter.log("Click on " + cartLinkInMsg.toString());
        CustomListeners.test.log(Status.PASS, "Click on " + cartLinkInMsg.toString());
    }
}
