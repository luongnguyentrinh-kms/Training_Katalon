package pages

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.helpers.LocatorHelper
import pages.common.CommonPage

public class ViewCartPage extends CommonPage {

    // VIEW CART PAGE LOCATOR
    private final TestObject cartTable = LocatorHelper.cartTableLocator()
    private final TestObject addedProductNameList = LocatorHelper.cartTableLocator("//tr/td[@class='cart_description']/h4/a")
    private final TestObject proceedCheckoutBtn = LocatorHelper.toByXpath("//a[normalize-space()='Proceed To Checkout']")
    private final TestObject proceedPopUp = LocatorHelper.toByXpath("//div[@class='modal-dialog modal-confirm']//h4[text()='Checkout']")
    private final TestObject registerLoginBtn = LocatorHelper.toByXpath("//a[normalize-space()='Register / Login']")
    // ACTION
    void clickProceedToCheckOut() {
        click(proceedCheckoutBtn)
    }

    void clickRegisterLogin() {
        click(registerLoginBtn)
    }

    // VERIFY
    boolean isProductAddedToCart(String expectedProductName) {
        List<WebElement> productNameElements =
        WebUI.findWebElements(addedProductNameList, 10)

        if (productNameElements.isEmpty()) {
            WebUI.comment('Cart is empty')
            return false
        }

        for (WebElement productName: productNameElements) {
            String name = productName.getText().trim()

            if (name.equalsIgnoreCase(expectedProductName)) {
                WebUI.comment('Found added product in cart: ' + expectedProductName)
                return true
            }
        }

        WebUI.comment('Added product NOT found in cart: ' + expectedProductName)
        return false
    }

    boolean isCartPageVisible() {
        return isDisplayed(cartTable)
    }

    boolean isProceedPopUpVisible() {
        return isDisplayed(proceedPopUp)
    }

}
