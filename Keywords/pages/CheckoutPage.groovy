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

public class CheckoutPage extends CommonPage{
	// CHECKOUT PAGE LOCATOR
	private TestObject deliveryAdressName = LocatorHelper.deliveryAddressByPostPath("[contains(@class, 'address_firstname')]")
	private TestObject deliveryAddressCompany = LocatorHelper.deliveryAddressByPostPath("[2]") // I get the index because it doesn't have signature 
	private TestObject deliveryAddress01 = LocatorHelper.deliveryAddressByPostPath("[3]") // I get the index because it doesn't have signature
	private TestObject deliveryAddress02 = LocatorHelper.deliveryAddressByPostPath("[4]") // I get the index because it doesn't have signature
	private TestObject deliveryAddressCity = LocatorHelper.deliveryAddressByPostPath("[contains(@class, 'address_city')]")
	private TestObject deliveryAddressCountry = LocatorHelper.deliveryAddressByPostPath("[contains(@class, 'address_country_name')]")
	private TestObject deliveryAddressPhone = LocatorHelper.deliveryAddressByPostPath("[@class='address_phone']")
	
	private TestObject cmtTextArea = LocatorHelper.toByXpath("//label[contains(text(), 'add a comment')]/following-sibling::textarea")
	private TestObject placeOrderBtn = LocatorHelper.toByXpath("//a[contains(text(), 'Place Order')]")
	
	// ACTION
	void addComment(String commentContent) {
		setText(cmtTextArea, commentContent)
	}
	
	void clickPlaceOrder() {
		click(placeOrderBtn)
	}
	
	// VERIFY
	boolean isAddressDetailsTrue(Map data) {
		// Expected
		String title = (data.title ?: "").toString().trim()
		String firstName = (data.firstName ?: "").toString().trim()
		String lastName  = (data.lastName ?: "").toString().trim()
		String expectedName = "${title}. ${firstName} ${lastName}".trim()

		String expectedCompany  = (data.company  ?: "").toString().trim()
		String expectedAddr1    = (data.address1 ?: "").toString().trim()
		String expectedAddr2    = (data.address2 ?: "").toString().trim()

		String expectedCityLine = "${(data.city ?: '').toString().trim()} ${(data.state ?: '').toString().trim()} ${(data.zipcode ?: '').toString().trim()}".trim()

		String expectedCountry  = (data.country ?: "").toString().trim()
		String expectedPhone    = (data.mobile  ?: "").toString().trim()

		// Actual (UI)
		String actualName     = getText(deliveryAdressName)
		String actualCompany  = getText(deliveryAddressCompany)
		String actualAddr1    = getText(deliveryAddress01)
		String actualAddr2    = getText(deliveryAddress02)
		String actualCityLine = getText(deliveryAddressCity)
		String actualCountry  = getText(deliveryAddressCountry)
		String actualPhone    = getText(deliveryAddressPhone)

		// Compare 
		if (!actualName.equalsIgnoreCase(expectedName)) {
			WebUI.comment("Name mismatch. Expected: '${expectedName}' | Actual: '${actualName}'")
			return false
		}

		if (!actualCompany.equalsIgnoreCase(expectedCompany)) {
			WebUI.comment("Company mismatch. Expected: '${expectedCompany}' | Actual: '${actualCompany}'")
			return false
		}

		if (!actualAddr1.equalsIgnoreCase(expectedAddr1)) {
			WebUI.comment("Address1 mismatch. Expected: '${expectedAddr1}' | Actual: '${actualAddr1}'")
			return false
		}

		if (!actualAddr2.equalsIgnoreCase(expectedAddr2)) {
			WebUI.comment("Address2 mismatch. Expected: '${expectedAddr2}' | Actual: '${actualAddr2}'")
			return false
		}

		if (!actualCityLine.equalsIgnoreCase(expectedCityLine)) {
			WebUI.comment("City/State/Zip mismatch. Expected: '${expectedCityLine}' | Actual: '${actualCityLine}'")
			return false
		}

		if (!actualCountry.equalsIgnoreCase(expectedCountry)) {
			WebUI.comment("Country mismatch. Expected: '${expectedCountry}' | Actual: '${actualCountry}'")
			return false
		}

		if (!actualPhone.equalsIgnoreCase(expectedPhone)) {
			WebUI.comment("Phone mismatch. Expected: '${expectedPhone}' | Actual: '${actualPhone}'")
			return false
		}

		WebUI.comment("Delivery Address matched expected data")
		return true
	}
}
