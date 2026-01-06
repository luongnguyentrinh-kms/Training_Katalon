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

public class PaymentPage extends CommonPage{
	// PAYMENT PAGE LOCATOR
	private TestObject txtNameOnCard = LocatorHelper.paymentFormLocator("Name on Card")
	private TestObject txtCardNumber = LocatorHelper.paymentFormLocator("Card Number")
	private TestObject txtCVC = LocatorHelper.paymentFormLocator("CVC")
	private TestObject txtMMExpiration = LocatorHelper.paymentFormLocator("Expiration", "expiry_month")
	private TestObject txtYYYYExpiration = LocatorHelper.toByXpath("//input[@name='expiry_year']")
	private TestObject btnPayAndConfirm = LocatorHelper.toByXpath("//button[contains(text(), 'Pay and Confirm Order')]")
	
	private TestObject btnDownloadInvoice =
	LocatorHelper.toByXpath("//a[contains(text(),'Download Invoice')]")
	private TestObject btnContinue = LocatorHelper.toByXpath("//a[text()='Continue']")
	
	// ACTION
	void enterPaymentDetail(Map dataPayment) {
		String nameOnCard  = (dataPayment.nameOnCard  ?: "").toString()
		String cardNumber  = (dataPayment.cardNumber  ?: "").toString()
		String cvc         = (dataPayment.cvc         ?: "").toString()
		String expMonth    = (dataPayment.expiryMonth ?: "").toString()
		String expYear     = (dataPayment.expiryYear  ?: "").toString()
	
		// Fill form
		setText(txtNameOnCard, nameOnCard)
		setText(txtCardNumber, cardNumber)
		setText(txtCVC, cvc)
		setText(txtMMExpiration, expMonth)
		setText(txtYYYYExpiration, expYear)
	}
	
	void clickPayAndConfirm() {
		click(btnPayAndConfirm)
	}
	
	void clickDownloadInvoice() {
		click(btnDownloadInvoice)
	}
	
	void clickContinue() {
		click(btnContinue)
	}
	
	// VERIFY
	
}
