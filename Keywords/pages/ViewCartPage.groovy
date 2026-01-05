package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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

public class ViewCartPage extends CommonPage{
	// VIEW CART PAGE LOCATOR
	private TestObject addedProductNameList = LocatorHelper.toByXpath("//table[@id='cart_info_table']//tr/td[@class='cart_description']/h4/a")
	
	// ACTION
	
	// VERIFY
	boolean isProductAddedToCart(String expectedProductName) {
		List<WebElement> productNameElements =
		WebUI.findWebElements(addedProductNameList, 10)
		
		if(productNameElements.isEmpty()) {
			WebUI.comment("Cart is empty")
			return false
		}
		
		for(WebElement productName: productNameElements) {
			String name = productName.getText().trim()
			
			if(name.equalsIgnoreCase(expectedProductName)) {
				WebUI.comment("Found added product in cart: " + expectedProductName)
				return true
			}
		}
		
		WebUI.comment("Added product NOT found in cart: " + expectedProductName)
		return false
	}
}
