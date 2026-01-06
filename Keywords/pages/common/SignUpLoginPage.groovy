package pages.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.helpers.LocatorHelper

public class SignUpLoginPage extends CommonPage {

    // ===== LOGIN LOCATOR =====
    private final TestObject lblLoginTitle = LocatorHelper.toByXpath("//h2[text() = 'Login to your account']")
    private final TestObject loginEmail = LocatorHelper.toByXpath("//input[@data-qa='login-email']")
    private final TestObject loginPass = LocatorHelper.toByXpath("//input[@data-qa='login-password']")
    private final TestObject loginBtn = LocatorHelper.toByXpath("//button[text()='Login']")
    private final TestObject loginErrMsg = LocatorHelper.toByXpath("//p[text() = 'Your email or password is incorrect!']")
    // ===== REGISTER LOCATOR =====
    private final TestObject signUpName = LocatorHelper.toByXpath("//input[@data-qa='signup-name']")
    private final TestObject signUpEmail = LocatorHelper.toByXpath("//input[@data-qa='signup-email']")
    private final TestObject signUpBtn = LocatorHelper.toByXpath("//button[text()='Signup']")

    // DETAIL REGISTER FORM LOCATOR
    private final TestObject radioMr = LocatorHelper.toByXpath("//input[@id='id_gender1']")
    private final TestObject radioMrs = LocatorHelper.toByXpath("//input[@id='id_gender2']")
    private final TestObject txtPassword = LocatorHelper.toByXpath("//input[@id='password']")

    private final TestObject ddDay   = LocatorHelper.toByXpath("//select[@id='days']")
    private final TestObject ddMonth = LocatorHelper.toByXpath("//select[@id='months']")
    private final TestObject ddYear  = LocatorHelper.toByXpath("//select[@id='years']")

    private final TestObject cbNewsletter = LocatorHelper.toByXpath("//input[@id='newsletter']")
    private final TestObject cbOffers     = LocatorHelper.toByXpath("//input[@id='optin']")

    // ===== ADDRESS INFORMATION LOCATOR =====
    private final TestObject txtFirstName = LocatorHelper.toByXpath("//input[@id='first_name']")
    private final TestObject txtLastName  = LocatorHelper.toByXpath("//input[@id='last_name']")
    private final TestObject txtCompany   = LocatorHelper.toByXpath("//input[@id='company']")
    private final TestObject txtAddress1  = LocatorHelper.toByXpath("//input[@id='address1']")
    private final TestObject txtAddress2  = LocatorHelper.toByXpath("//input[@id='address2']")

    private final TestObject ddCountry    = LocatorHelper.toByXpath("//select[@id='country']")
    private final TestObject txtState     = LocatorHelper.toByXpath("//input[@id='state']")
    private final TestObject txtCity      = LocatorHelper.toByXpath("//input[@id='city']")
    private final TestObject txtZipcode   = LocatorHelper.toByXpath("//input[@id='zipcode']")
    private final TestObject txtMobile    = LocatorHelper.toByXpath("//input[@id='mobile_number']")

    private final TestObject btnCreateAccount = LocatorHelper.toByXpath("//button[text()='Create Account']")

    private final TestObject accountCreatedTitle = LocatorHelper.toByXpath("//h2[normalize-space()='Account Created!']")
    private final TestObject accountCreatedBtn = LocatorHelper.toByXpath("//a[text()='Continue']")

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
        String title = (data.title ?: 'Mr').toString()
        if (title.equalsIgnoreCase('Mrs')) click(radioMrs) else click(radioMr)

        // Password
        setText(txtPassword, (data.password ?: '').toString())

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
        setText(txtFirstName, (data.firstName ?: '').toString())
        setText(txtLastName,  (data.lastName  ?: '').toString())
        setText(txtCompany,   (data.company   ?: '').toString())
        setText(txtAddress1,  (data.address1  ?: '').toString())
        setText(txtAddress2,  (data.address2  ?: '').toString())

        // Country
        if (data.country) selectByLabel(ddCountry, data.country.toString())

        setText(txtState,   (data.state   ?: '').toString())
        setText(txtCity,    (data.city    ?: '').toString())
        setText(txtZipcode, (data.zipcode ?: '').toString())
        setText(txtMobile,  (data.mobile  ?: '').toString())
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
