package com.tutorialsninja.demo.pages;

import com.aventstack.extentreports.Status;
import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.List;

public class ProductPage extends Utility {
    @CacheLookup
    @FindBy(xpath = "//div[@id = 'content']//h1")
    WebElement productText;
    @CacheLookup
    @FindBy(xpath = "//div[@class = 'input-group date']//button")
    WebElement calendarButton;
    @CacheLookup
    @FindBy(xpath = "//div[@class = 'datepicker']/div[1]//th[@class='picker-switch']")
    WebElement monthAndYearText;
    @CacheLookup
    @FindBy(xpath = "//div[@class = 'datepicker']/div[1]//th[@class='next']")
    WebElement nextButton;
    @CacheLookup
    @FindBy(xpath = "//div[@class = 'datepicker']/div[1]//tbody/tr/td[@class = 'day']")
    List<WebElement> dateList;
    @CacheLookup
    @FindBy(css = "#input-quantity")
    WebElement qtyField;
    @CacheLookup
    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement addToCartButton;
    @CacheLookup
    @FindBy(css = "body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible")
    WebElement successMessage;
    @CacheLookup
    @FindBy(xpath = "//a[contains(text(),'shopping cart')]")
    WebElement shoppingCartLink;
    @CacheLookup
    @FindBy(xpath = "//div[@class = 'input-group date']//button")
    WebElement datePicker;

    public String getProductText() {
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Get text " + productText);
        Reporter.log("Get text" + productText.toString());
        return getTextFromElement(productText);
    }

    public void selectDeliveryDate(String day, String month, String year) {
        clickOnElement(datePicker);
        while (true) {
            String monthAndYear = getTextFromElement(monthAndYearText);
            String[] arr = monthAndYear.split(" ");
            String mon = arr[0];
            String yer = arr[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(nextButton);
            }
        }
        List<WebElement> allDates = getListOfElements(dateList);
        for (WebElement e : allDates) {
            if (e.getText().equalsIgnoreCase(day)) {
                e.click();
                break;
            }
        }
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Select date from " + dateList);
        Reporter.log("select date " + dateList.toString());
    }

    public void clickOnAddToCartButton() {
        clickOnElement(addToCartButton);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Click on " + addToCartButton);
        Reporter.log("Click on " + addToCartButton.toString());
    }

    public String getProductAddedSuccessMessage() {
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Get success message " + successMessage);
        Reporter.log("Get success message " + successMessage.toString());
        return getTextFromElement(successMessage);
    }

    public void clickOnShoppingCartLinkIntoMessage() {
        clickOnElement(shoppingCartLink);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Click on shopping cart " + shoppingCartLink);
        Reporter.log("Click on shopping cart " + shoppingCartLink.toString());
    }

    public void enterQuantity(String qty) {
        //clearTextFromField(qtyField);
        sendTextToElement(qtyField, qty);
        // Reports and Log
        CustomListeners.test.log(Status.PASS,"Enter " + qty + qtyField);
        Reporter.log("Enter " + qty + qtyField.toString());
    }
}
