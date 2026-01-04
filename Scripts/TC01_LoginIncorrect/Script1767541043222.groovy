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

import core.Browser
import internal.GlobalVariable
import pages.HomePage
import pages.common.SignUpLoginPage

import org.openqa.selenium.Keys as Keys

HomePage homePage = new HomePage()
SignUpLoginPage signUpLoginPage = new SignUpLoginPage()

"PREPARATION"
String email = "a@gmail.com"
String password = "abc123"
 
"TEST STEP"
"Step 1: Launch browser"
"Step 2: Navigate to url 'http://automationexercise.com'"
Browser.open(GlobalVariable.baseUrl)
  
"Step 3: Verify that home page is visible successfully"
WebUI.verifyEqual(homePage.isHomeVisible(), true, FailureHandling.STOP_ON_FAILURE)
  
"Step 4: Click on 'Signup / Login' button"
homePage.clickSignUpLogin()
  
"Step 5: Verify 'Login to your account' is visible"
WebUI.verifyEqual(signUpLoginPage.isLoginFormVisible(), true, FailureHandling.CONTINUE_ON_FAILURE)
  
"Step 6: Enter incorrect email address and password"
"Step 7: Click 'login' button"
signUpLoginPage.loginWith(email, password)

"Step 8: Verify error 'Your email or password is incorrect!' is visible"
WebUI.verifyEqual(signUpLoginPage.isInvalidCredErrorVisible(), true, FailureHandling.STOP_ON_FAILURE)

Browser.close()
  