package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.helpers.LocatorHelper
import pages.common.CommonPage

public class ProductsPage extends CommonPage {

    // PRODUCTS PAGE LOCATOR
    private final TestObject allProductsTitle = findTestObject('Object Repository/ProductsPage/AllProductsTitle')
    private final TestObject searchInput = LocatorHelper.toByXpath("//input[@placeholder='Search Product']")
    private final TestObject searchBtn = LocatorHelper.toByXpath("//button[@id='submit_search']")
    private final TestObject searchedProductLabel = findTestObject('Object Repository/ProductsPage/SearchProductLabel')
    private final TestObject productNameList = LocatorHelper.toByXpath("//div[normalize-space()='View Product']/preceding-sibling::div/div[contains(@class, 'productinfo')]/p")

    // ACTION
    void searchProduct(String productName) {
        setText(searchInput, productName)
        click(searchBtn)
    }

    // VERIFY
    boolean isAllProductsPageVisible() {
        return isDisplayed(allProductsTitle)
    }

    boolean isSearchedProductsVisible() {
        return isDisplayed(searchedProductLabel)
    }

    boolean areAllProductsRelatedAnVisible(String keyword) {
        List<WebElement> productNames = WebUI.findWebElements(productNameList, DEFAULT_TIMEOUT)

        if (productNames.isEmpty()) {
            WebUI.comment('Product is NOT visible')
            return false
        }

        for (def product: productNames) {
            // 1. Verify visible
            if (!product.isDisplayed()) {
                return false
            }

            // 2. Verify that searched products are related
            String productName = product.getText().toLowerCase()
            if (!productName.contains(keyword.toLowerCase())) {
                WebUI.comment('Product NOT related: ' + productName)
                return false
            }
        }
        return true
    }

}
