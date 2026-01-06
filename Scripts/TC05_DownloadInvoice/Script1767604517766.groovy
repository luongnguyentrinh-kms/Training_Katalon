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

import common.helpers.DownloadHelper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import core.Browser
import pages.CheckoutPage
import pages.HomePage
import pages.PaymentPage
import pages.ViewCartPage
import pages.common.SignUpLoginPage

HomePage homePage = new HomePage()
ViewCartPage viewCartPage = new ViewCartPage()
SignUpLoginPage signUpLoginPage = new SignUpLoginPage()
CheckoutPage checkoutPage = new CheckoutPage()
PaymentPage paymentPage = new PaymentPage()

"PREPARATION"
"Added product => Product name: Quantity"
Map<String, Integer> cartCounter = [:].withDefault { 0 }

"Register Form"
String random = UUID.randomUUID().toString().substring(0, 8)
String name = "user" + random;
String email = "user" + random + "@gmail.com";

Map data = [
	title      : "Mr",
	password   : "abc123",
	day        : "10",
	month      : "5",
	year       : "1998",
	newsletter : true,
	offers     : true,
	firstName  : "Luong",
	lastName   : "Trinh",
	company    : "KMS",
	address1   : "123 ABC Street",
	address2   : "Block D",
	country    : "India",
	state      : "State",
	city       : "City",
	zipcode    : "700000",
	mobile     : "0999999999"
]

String commentContent = "Test comment"

Map dataPayment = [
	nameOnCard   : "Luong Trinh",
	cardNumber   : "4111111111111111",
	cvc          : "123",
	expiryMonth  : "12",
	expiryYear   : "2030"
]

"TEST STEP"
"Step 1: Launch browser"
"Step 2: Navigate to url 'http://automationexercise.com'"
Browser.open(GlobalVariable.baseUrl)

"Step 3: Verify that home page is visible successfully"
WebUI.verifyEqual(homePage.isHomePageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 4: Add products to cart"
homePage.scrollToBottom()
"VP: Verify 'RECOMMENDED ITEMS' are visible"
WebUI.verifyEqual(homePage.isRecommendedItemsVisible(), true, FailureHandling.STOP_ON_FAILURE)
String recommendedItemNameAdded = homePage.addFirstRecommendedItemToCart()
cartCounter[recommendedItemNameAdded]++
"VP: Verify the item is added"
WebUI.verifyEqual(homePage.isRecommendedItemAdded(), true, FailureHandling.STOP_ON_FAILURE)

"Step 5: Click 'Cart' button"
homePage.clickCntShopping()
homePage.scrollToHeader()
homePage.clickViewCart()

"Step 6: Verify that cart page is displayed"
WebUI.verifyEqual(viewCartPage.isCartPageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 7: Click Proceed To Checkout"
viewCartPage.clickProceedToCheckOut()
"VP: Verify proceed to checkout popup is displayed"
WebUI.verifyEqual(viewCartPage.isProceedPopUpVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 8: Click 'Register / Login' button"
viewCartPage.clickRegisterLogin()

"Step 9: Fill all details in Signup and create account"
signUpLoginPage.signUpWith(name, email)
signUpLoginPage.fillDetailRegisterForm(data)
signUpLoginPage.submitCreateAccount()

"Step 10: Verify 'ACCOUNT CREATED!' and click 'Continue' button"
WebUI.verifyEqual(signUpLoginPage.isAccountCreated(), true, FailureHandling.STOP_ON_FAILURE)
signUpLoginPage.clickAccountCreatedBtn()

"Step 11: Verify 'Logged in as username' at top"
WebUI.verifyEqual(homePage.isLoggedInAsVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 12: Click 'Cart' button"
homePage.clickViewCart()

"Step 13: Click 'Proceed To Checkout' button"
viewCartPage.clickProceedToCheckOut()

"Step 14: Verify Address Details and Review Your Order"
WebUI.verifyEqual(checkoutPage.isAddressDetailsTrue(data), true, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyEqual(checkoutPage.isCartMatchedExpected(cartCounter), true, FailureHandling.STOP_ON_FAILURE)

"Step 15: Enter description in comment text area and click 'Place Order'"
checkoutPage.addComment(commentContent)
checkoutPage.clickPlaceOrder()

"Step 16: Enter payment details: Name on Card, Card Number, CVC, Expiration date"
paymentPage.enterPaymentDetail(dataPayment)

"Step 17: Click 'Pay and Confirm Order' button"
paymentPage.clickPayAndConfirm()

"Step 18: Verify success message 'Your order has been placed successfully!'"
paymentPage.isPlacedOrderMsgVisible()

"Step 19: Click 'Download Invoice' button and verify invoice is downloaded successfully"
// Delete file in Download folder before downloading new file
DownloadHelper.cleanDownloadFolder()
paymentPage.clickDownloadInvoice()
"VP: Verify invoice is downloaded successfully"
File invoiceFile = DownloadHelper.waitInvoiceDownloaded(30)
WebUI.verifyNotEqual(invoiceFile, null, FailureHandling.STOP_ON_FAILURE)

"Step 20: Click 'Continue' button"
paymentPage.clickContinue()
"VP: Verify homepage is visible"
WebUI.verifyEqual(homePage.isHomePageVisible(), true, FailureHandling.STOP_ON_FAILURE)

"Step 21: Click 'Delete Account' button"
homePage.clickDeleteAccount()

"Step 22: Verify 'ACCOUNT DELETED!' and click 'Continue' button"
homePage.clickContinue()
WebUI.verifyEqual(homePage.isHomePageVisible(), true, FailureHandling.STOP_ON_FAILURE)

Browser.close()