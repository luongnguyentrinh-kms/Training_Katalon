package pages.common

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.helpers.LocatorHelper

public class CommonPage {

    protected int DEFAULT_TIMEOUT = 10

    // ACTION

    // MOUSE CLICK
    void click(TestObject to) {
        WebUI.waitForElementClickable(to,  DEFAULT_TIMEOUT)
        WebUI.click(to)
    }

    void enhancedClick(TestObject to) {
        WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
        WebUI.enhancedClick(to)
    }

    // TEXT
    void setText(TestObject to, String text) {
        WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
        WebUI.setText(to, text)
    }

    String getText(TestObject to) {
        WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
        return WebUI.getText(to)
    }

    // DROPDOWN
    void selectByValue(TestObject dropdown, String value) {
        WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
        WebUI.selectOptionByValue(dropdown, value, false)
    }

    void selectByLabel(TestObject dropdown, String label) {
        WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
        WebUI.selectOptionByLabel(dropdown, label, false)
    }

    void selectByIndex(TestObject dropdown, int index) {
        WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
        WebUI.selectOptionByIndex(dropdown, index)
    }

    // CHECKBOX/RADIO
    void check(TestObject to) {
        WebUI.waitForElementClickable(to, DEFAULT_TIMEOUT)
        if (!WebUI.verifyElementChecked(to, 1, FailureHandling.OPTIONAL)) {
            WebUI.click(to)
        }
    }

    String addItemToCart(TestObject productName, TestObject addBtn) {
        String name = getText(productName)
        click(addBtn)
        return name
    }

    boolean isCartMatchedExpected(Map<String, Integer> expectedMap) {
        // Get row from cart
        def rowTO = LocatorHelper.toByXpath("//table[@class='table table-condensed']//tr[starts-with(@id,'product-')]")
        List<WebElement> rows = WebUI.findWebElements(rowTO, DEFAULT_TIMEOUT)

        if (rows.isEmpty()) {
            WebUI.comment('Cart is empty')
            return false
        }

        // Build actual map from UI
        Map<String, Integer> actualMap = [:].withDefault { 0 }

        for (WebElement row: rows) {
            String name = row.findElement(By.xpath(".//td[@class='cart_description']//h4/a")).getText().trim()
            String qtyText = row.findElement(By.xpath(".//td[@class='cart_quantity']//button")).getText().trim()

            int qty = 0
            qty = Integer.parseInt(qtyText)

            actualMap[name] = qty
        }

        // Compare expected vs actual
        for (String expectedName: expectedMap.keySet()) {
            int expectedQty = expectedMap[expectedName] ?: 0

            String matchedKey = actualMap.keySet().find { it.equalsIgnoreCase(expectedName) }
            if (matchedKey == null) {
                WebUI.comment('Product NOT found in cart: ' + expectedName)
                return false
            }

            int actualQty = actualMap[matchedKey] ?: 0
            if (actualQty != expectedQty) {
                WebUI.comment("Quantity mismatch for '" + expectedName + "': expected=" + expectedQty + ', actual=' + actualQty)
                return false
            }
            WebUI.comment("Matched '" + expectedName + "': qty=" + actualQty)
        }
        return true
    }

    // VERIFY
    boolean isDisplayed(TestObject to) {
        return WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)
    }

    boolean isChecked(TestObject to) {
        return WebUI.verifyElementChecked(to, 1, FailureHandling.OPTIONAL)
    }

}
