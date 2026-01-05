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
	// ===== LOGIN LOCATOR =====
	private TestObject lblLoginTitle = LocatorHelper.toByXpath("//h2[text() = 'Login to your account']")
	private TestObject loginEmail = LocatorHelper.toByXpath("//input[@data-qa='login-email']")
	private TestObject loginPass = LocatorHelper.toByXpath("//input[@data-qa='login-password']")
	private TestObject loginBtn = LocatorHelper.toByXpath("//button[text()='Login']")
	private TestObject loginErrMsg = LocatorHelper.toByXpath("//p[text() = 'Your email or password is incorrect!']")
	
	// ===== REGISTER LOCATOR =====
	private TestObject signUpName = LocatorHelper.toByXpath("//input[@data-qa='signup-name']")
	private TestObject signUpEmail = LocatorHelper.toByXpath("//input[@data-qa='signup-email']")
	private TestObject signUpBtn = LocatorHelper.toByXpath("//button[text()='Signup']")
	
	// DETAIL REGISTER FORM LOCATOR
	private TestObject radioMr = LocatorHelper.toByXpath("//input[@id='id_gender1']")
	private TestObject radioMrs = LocatorHelper.toByXpath("//input[@id='id_gender2']")
	private TestObject txtPassword = LocatorHelper.toByXpath("//input[@id='password']")
	
	private TestObject ddDay   = LocatorHelper.toByXpath("//select[@id='days']")
	private TestObject ddMonth = LocatorHelper.toByXpath("//select[@id='months']")
	private TestObject ddYear  = LocatorHelper.toByXpath("//select[@id='years']")
	
	private TestObject cbNewsletter = LocatorHelper.toByXpath("//input[@id='newsletter']")
	private TestObject cbOffers     = LocatorHelper.toByXpath("//input[@id='optin']")
	

	// ===== ADDRESS INFORMATION LOCATOR =====
	private TestObject txtFirstName = LocatorHelper.toByXpath("//input[@id='first_name']")
	private TestObject txtLastName  = LocatorHelper.toByXpath("//input[@id='last_name']")
	private TestObject txtCompany   = LocatorHelper.toByXpath("//input[@id='company']")
	private TestObject txtAddress1  = LocatorHelper.toByXpath("//input[@id='address1']")
	private TestObject txtAddress2  = LocatorHelper.toByXpath("//input[@id='address2']")

	private TestObject ddCountry    = LocatorHelper.toByXpath("//select[@id='country']")
	private TestObject txtState     = LocatorHelper.toByXpath("//input[@id='state']")
	private TestObject txtCity      = LocatorHelper.toByXpath("//input[@id='city']")
	private TestObject txtZipcode   = LocatorHelper.toByXpath("//input[@id='zipcode']")
	private TestObject txtMobile    = LocatorHelper.toByXpath("//input[@id='mobile_number']")

	private TestObject btnCreateAccount = LocatorHelper.toByXpath("//button[text()='Create Account']")
	
	private TestObject accountCreatedTitle = LocatorHelper.toByXpath("//h2[normalize-space()='Account Created!']")
	private TestObject accountCreatedBtn = LocatorHelper.toByXpath("//a[text()='Continue']")
		
	// ACTION
	void loginWith(String email, String password) {
		setText(loginEmail, email)
		setText(loginPass, password)
		click(loginBtn)
	}
	
	void signUpWith(String name, String email) {
		setText(signUpName, name)
		setText(signUpEmail, email)
		click(signUpBtn)
	}
	
	void fillDetailRegisterForm(Map data) {
		 // Title
        String title = (data.title ?: "Mr").toString()
        if (title.equalsIgnoreCase("Mrs")) click(radioMrs) else click(radioMr)

        // Password
        setText(txtPassword, (data.password ?: "").toString())

        // DOB 
        if (data.day)   selectByValue(ddDay, data.day.toString())
        if (data.month) selectByValue(ddMonth, data.month.toString())
        if (data.year)  selectByValue(ddYear, data.year.toString())

        // Checkbox
        if (data.newsletter == true) {
			check(cbNewsletter)
        }
		
        if (data.offers == true) {
			check(cbOffers)
		}

        // Address info
        setText(txtFirstName, (data.firstName ?: "").toString())
        setText(txtLastName,  (data.lastName  ?: "").toString())
        setText(txtCompany,   (data.company   ?: "").toString())
        setText(txtAddress1,  (data.address1  ?: "").toString())
        setText(txtAddress2,  (data.address2  ?: "").toString())

        // Country
        if (data.country) selectByLabel(ddCountry, data.country.toString())

        setText(txtState,   (data.state   ?: "").toString())
        setText(txtCity,    (data.city    ?: "").toString())
        setText(txtZipcode, (data.zipcode ?: "").toString())
        setText(txtMobile,  (data.mobile  ?: "").toString())
	}
	
	void submitCreateAccount() {
		WebUI.scrollToElement(btnCreateAccount, DEFAULT_TIMEOUT)
		click(btnCreateAccount)
	}
	
	void clickAccountCreatedBtn() {
		click(accountCreatedBtn)
	}
	
	// VERIFY
	boolean isLoginFormVisible() {
		return isDisplayed(lblLoginTitle)
	}
	
	boolean isInvalidCredErrorVisible() {
		return isDisplayed(loginErrMsg)
	}
	
	boolean isAccountCreated() {
		return isDisplayed(accountCreatedTitle)
	}
}
