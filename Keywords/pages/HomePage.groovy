package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.helpers.LocatorHelper
import pages.common.CommonPage

public class HomePage extends CommonPage {

    // HOME PAGE LOCATOR
    private final TestObject header = LocatorHelper.toById('header')
    private final TestObject homeSlider = LocatorHelper.toById('slider')
    private final TestObject signUpLoginBtn = LocatorHelper.toByXpath("//a[normalize-space()='Signup / Login']")
    private final TestObject viewCartBtn = LocatorHelper.toByXpath("//a[normalize-space()='Cart']")
    private final TestObject deleteAccountBtn = LocatorHelper.toByXpath("//a[normalize-space()='Delete Account']")
    private final TestObject loggedInAsBtn = LocatorHelper.toByXpath("//a[contains(normalize-space(),'Logged in as')]")
    private final TestObject testCasesBtn = LocatorHelper.toByXpath("//ul[contains(@class, 'navbar-nav')]//a[normalize-space()='Test Cases']")
    private final TestObject productsBtn = findTestObject('Object Repository/HomePage/ProductBtn')
    private final TestObject recommendedItemsLabel = findTestObject('Object Repository/HomePage/RecommendedItemsLabel')
    private final TestObject deleteAccountLabel = LocatorHelper.toByXpath("//h2[normalize-space()='Account Deleted!']")
    private final TestObject firstItemsInRecommendedItems = LocatorHelper.toByXpath("(//div[@class='recommended_items']//div[contains(@class,'item active')]//a[contains(@class,'add-to-cart')])[1]")
    private final TestObject firstItemNameInRecommendedItems = LocatorHelper.toByXpath("(//div[@class='recommended_items']//div[contains(@class,'item active')]//div[contains(@class,'productinfo')]//p)[1]")
    private final TestObject addedPopUp = LocatorHelper.toByXpath("//div[@class='modal-dialog modal-confirm']//h4[text()='Added!']")
    private final TestObject cntShoppingBtn = LocatorHelper.toByXpath("//button[text()='Continue Shopping']")
    private final TestObject continueBtn = LocatorHelper.toByXpath("//a[text()='Continue']")

    // ACTION
    void clickSignUpLogin() {
        click(signUpLoginBtn)
    }

    void clickTestCasesBtn() {
        click(testCasesBtn)
    }

    void clickProductsBtn() {
        click(productsBtn)
    }

    void scrollToBottom() {
        WebUI.scrollToElement(recommendedItemsLabel, DEFAULT_TIMEOUT)
    }

    String addFirstRecommendedItemToCart() {
        return addItemToCart(firstItemNameInRecommendedItems, firstItemsInRecommendedItems)
    }

    void clickCntShopping() {
        click(cntShoppingBtn)
    }

    void scrollToHeader() {
        WebUI.scrollToElement(header, DEFAULT_TIMEOUT)
    }

    void clickViewCart() {
        click(viewCartBtn)
    }

    void clickDeleteAccount() {
        click(deleteAccountBtn)
    }

    void clickContinue() {
        click(continueBtn)
    }

    // VERIFY
    boolean isHomePageVisible() {
        return isDisplayed(homeSlider)
    }

    boolean isRecommendedItemsVisible() {
        return isDisplayed(recommendedItemsLabel)
    }

    boolean isRecommendedItemAdded() {
        return isDisplayed(addedPopUp)
    }

    boolean isLoggedInAsVisible() {
        return isDisplayed(loggedInAsBtn)
    }

    boolean isAccountDeleted() {
        return isDisplayed(deleteAccountLabel)
    }

}
