package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import common.helpers.LocatorHelper
import internal.GlobalVariable
import pages.common.CommonPage

public class HomePage extends CommonPage{
	// HOME PAGE LOCATOR
	private TestObject homeSlider = LocatorHelper.toById("slider")
	private TestObject signUpLoginBtn = LocatorHelper.toByXpath("//a[normalize-space()='Signup / Login']")
	private TestObject testCasesBtn = LocatorHelper.toByXpath("//ul[contains(@class, 'navbar-nav')]//a[normalize-space()='Test Cases']")
	private TestObject productsBtn = findTestObject("Object Repository/HomePage/ProductBtn")
	private TestObject recommendedItemsLabel = findTestObject("Object Repository/HomePage/RecommendedItemsLabel")
	private TestObject footer = LocatorHelper.toByXpath("//div[@class='footer-bottom']")
	private TestObject firstItemsInRecommendedItems = LocatorHelper.toByXpath("(//div[@class='recommended_items']//div[contains(@class,'item active')]//a[contains(@class,'add-to-cart')])[1]")
	private TestObject firstItemNameInRecommendedItems = LocatorHelper.toByXpath("(//div[@class='recommended_items']//div[contains(@class,'item active')]//div[contains(@class,'productinfo')]//p)[1]")
	private TestObject addedPopUp = LocatorHelper.toByXpath("//div[@class='modal-dialog modal-confirm']//h4[text()='Added!']")
	private TestObject cntShoppingBtn = LocatorHelper.toByXpath("//button[text()='Continue Shopping']")
	private TestObject header = LocatorHelper.toById("header")
	private TestObject viewCartBtn = LocatorHelper.toByXpath("//a[normalize-space()='Cart']")
	private TestObject loggedInAsBtn = LocatorHelper.toByXpath("//a[contains(normalize-space(),'Logged in as')]")
	private TestObject deleteAccountBtn = LocatorHelper.toByXpath("//a[normalize-space()='Delete Account']")
	private TestObject deleteAccountLabel = LocatorHelper.toByXpath("//h2[normalize-space()='Account Deleted!']")
	private TestObject continueBtn = LocatorHelper.toByXpath("//a[text()='Continue']")
	
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
