import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import core.Browser
import pages.HomePage
import pages.ViewCartPage

HomePage homePage = new HomePage()
ViewCartPage viewCartPage = new ViewCartPage()

"PREPARATION"
// Count quality product added to cart
Map<String, Integer> cartCounter = [:].withDefault { 0 }

"TEST STEP"
"Step 1: Launch browser"
"Step 2: Navigate to url 'http://automationexercise.com'"
Browser.open(GlobalVariable.baseUrl)
"VP: Verify that home page is visible successfully"
WebUI.verifyEqual(homePage.isHomePageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 3: Scroll to bottom of page"
homePage.scrollToBottom()

"Step 4: Verify 'RECOMMENDED ITEMS' are visible"
WebUI.verifyEqual(homePage.isRecommendedItemsVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 5: Click on 'Add To Cart' on Recommended product"
String recommendedItemNameAdded = homePage.addFirstRecommendedItemToCart()
cartCounter[recommendedItemNameAdded]++
"VP: Verify the item is added"
WebUI.verifyEqual(homePage.isRecommendedItemAdded(), true, FailureHandling.STOP_ON_FAILURE)

"Step 6: Click on 'View Cart' button"
homePage.clickCntShopping()
homePage.scrollToHeader()
homePage.clickViewCart()

"Step 7: Verify that product is displayed in cart page"
WebUI.verifyEqual(viewCartPage.isCartMatchedExpected(cartCounter), true, FailureHandling.STOP_ON_FAILURE)