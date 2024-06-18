package com.tutorialsninja.demo.pages;

import com.aventstack.extentreports.Status;
import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Reporter;

public class ShoppingCartPage extends Utility {
    @CacheLookup
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement shoppingCartText;

    @CacheLookup
    @FindBy(xpath = "//h1[contains(text(),'Shopping Cart')]")
    WebElement shoppingCartHeading;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a")
    WebElement productNames;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody/tr/td[2]/small[1]")
    WebElement deliveryDate;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody/tr/td[3]")
    WebElement model;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody/tr/td[6]")
    WebElement total;

    @CacheLookup
    @FindBy(xpath = "//input[contains(@name, 'quantity')]")
    WebElement qtyField;

    @CacheLookup
    @FindBy(xpath = "//button[contains(@data-original-title, 'Update')]")
    WebElement qtyUpdateBtn;

    @CacheLookup
    @FindBy(xpath = "//div[@id='checkout-cart']/div[1]")
    WebElement successModifiedMessage;

    @CacheLookup
    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")
    public WebElement productName;

    @CacheLookup
    @FindBy(xpath = "//tbody//tr//td[@class='text-left'][2]")
    WebElement modelName;

    @CacheLookup
    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]")
    WebElement totalPrice;

    public String getShoppingCartHeading() {
        Reporter.log("Get " + shoppingCartHeading.toString());
        CustomListeners.test.log(Status.PASS, "Get " + shoppingCartHeading.toString());
        return getTextFromElement(shoppingCartHeading);
    }
    public String getProductName() {
        Reporter.log("Get " + productName.toString());
        CustomListeners.test.log(Status.PASS, "Get " + productName.toString());
        return getTextFromElement(productName);
    }

    public String getModelName() {
        Reporter.log("Get " + modelName.toString());
        CustomListeners.test.log(Status.PASS, "Get " + modelName.toString());
        return getTextFromElement(modelName);
    }

    public String getTotalPrice() {
        Reporter.log("Get " + totalPrice.toString());
        CustomListeners.test.log(Status.PASS, "Get " + totalPrice.toString());
        WebElement toRightOf = driver.findElement(RelativeLocator.with(By.xpath("//td[@class='text-right']")).toRightOf(By.xpath("//strong[normalize-space()='Total:']")));

        return getTextFromElement(toRightOf);
    }

    public String getShoppingCartText() {
        CustomListeners.test.log(Status.PASS,"Get text " + shoppingCartText);
        Reporter.log("Get text " + shoppingCartText.toString());
        return getTextFromElement(shoppingCartText);
    }

    public String getProductsName() {
        CustomListeners.test.log(Status.PASS,"Get product name " + productName);
        Reporter.log("Get product name" + productName.toString());
        return getTextFromElement(productName);
    }

    public String getDeliveryDate() {
        CustomListeners.test.log(Status.PASS,"Get delivery date " + deliveryDate);
        Reporter.log("Get delivery date" + deliveryDate.toString());
        return getTextFromElement(deliveryDate);
    }

    public String getModel() {
        CustomListeners.test.log(Status.PASS,"Get Model name " + model);
        Reporter.log("Get Model name" + model.toString());
        return getTextFromElement(model);
    }

    public String getTotal() {
        CustomListeners.test.log(Status.PASS,"Get total " + total);
        Reporter.log("Get total" + total.toString());
        return getTextFromElement(total);
    }

    public void changeQuantity(String qty) {
        clearText(qtyField);
        sendTextToElement(qtyField, qty);
        CustomListeners.test.log(Status.PASS,"Change quantity " + qty);
        Reporter.log("Change quantity" + qty.toString());
    }

    public void clickOnQtyUpdateButton() {
        clickOnElement(qtyUpdateBtn);
        CustomListeners.test.log(Status.PASS,"Click on update " + qtyUpdateBtn);
        Reporter.log("Click on update" + qtyUpdateBtn.toString());
    }

    public String getSuccessModifiedMessage() {
        CustomListeners.test.log(Status.PASS,"Get success message " + successModifiedMessage);
        Reporter.log("Get success message" + successModifiedMessage.toString());
        return getTextFromElement(successModifiedMessage);
    }
}
