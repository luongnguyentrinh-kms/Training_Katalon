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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import common.helpers.LocatorHelper
import internal.GlobalVariable

public class SignUpLoginPage extends CommonPage{
	// LOGIN LOCATOR
	private TestObject lblLoginTitle = LocatorHelper.toByXpath("//h2[text() = 'Login to your account']")
	private TestObject loginEmail = LocatorHelper.toByXpath("//input[@data-qa='login-email']")
	private TestObject loginPass = LocatorHelper.toByXpath("//input[@data-qa='login-password']")
	private TestObject loginBtn = LocatorHelper.toByXpath("//button[text()='Login']")
	private TestObject loginErrMsg = LocatorHelper.toByXpath("//p[text() = 'Your email or password is incorrect!']")
	
	// ACTION
	void loginWith(String email, String password) {
		setText(loginEmail, email)
		setText(loginPass, password)
		click(loginBtn)
	}
	
	// VERIFY
	boolean isLoginFormVisible() {
		return isDisplayed(lblLoginTitle)
	}
	
	boolean isInvalidCredErrorVisible() {
		return isDisplayed(loginErrMsg)
	}
}
