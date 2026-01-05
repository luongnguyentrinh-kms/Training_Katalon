package pages.common

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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CommonPage {

    protected int DEFAULT_TIMEOUT = 10

	// ACTION
	
	// MOUSE CLICK
    void click(TestObject to) {
        WebUI.waitForElementClickable(to,  DEFAULT_TIMEOUT)
        WebUI.click(to)
    }
	
	void enhancedClick(TestObject to) {
		WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
		WebUI.enhancedClick(to)
	}

	// TEXT
    void setText(TestObject to, String text) {
        WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
        WebUI.setText(to, text)
    }

    String getText(TestObject to) {
        WebUI.waitForElementVisible(to, DEFAULT_TIMEOUT)
        return WebUI.getText(to)
    }
	
	// DROPDOWN
	void selectByValue(TestObject dropdown, String value) {
		WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
		WebUI.selectOptionByValue(dropdown, value, false)
	}
	
	void selectByLabel(TestObject dropdown, String label) {
		WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
		WebUI.selectOptionByLabel(dropdown, label, false)
	}
	
	void selectByIndex(TestObject dropdown, int index) {
		WebUI.waitForElementVisible(dropdown, DEFAULT_TIMEOUT)
		WebUI.selectOptionByIndex(dropdown, index)
	}
	
	// CHECKBOX/RADIO
	void check(TestObject to) {
		WebUI.waitForElementClickable(to, DEFAULT_TIMEOUT)
		if (!WebUI.verifyElementChecked(to, 1, FailureHandling.OPTIONAL)) {
			WebUI.click(to)
		}
	}

	// VERIFY
    boolean isDisplayed(TestObject to) {
        return WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)
    }
	
	boolean isChecked(TestObject to) {
		return WebUI.verifyElementChecked(to, 1, FailureHandling.OPTIONAL)
	}
}
