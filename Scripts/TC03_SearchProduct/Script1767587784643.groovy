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
import pages.ProductsPage

HomePage homePage = new HomePage()
ProductsPage productsPage = new ProductsPage()

"PREPARATION"
String keyword = "Blue"

"TEST STEP"
"Step 1: Launch browser"
"Step 2: Navigate to url 'http://automationexercise.com'"
Browser.open(GlobalVariable.baseUrl)

"Step 3: Verify that home page is visible successfully"
WebUI.verifyEqual(homePage.isHomePageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 4: Click on 'Products' button"
homePage.clickProductsBtn()

"Step 5: Verify user is navigated to ALL PRODUCTS page successfully"
WebUI.verifyEqual(productsPage.isAllProductsPageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 6: Enter product name in search input and click search button"
productsPage.searchProduct(keyword)
 
"Step 7: Verify 'SEARCHED PRODUCTS' is visible"
WebUI.verifyEqual(productsPage.isSearchedProductsVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 8: Verify all the products related to search are visible"
WebUI.verifyEqual(productsPage.areAllProductsRelatedAnVisible(keyword), true, FailureHandling.STOP_ON_FAILURE)
