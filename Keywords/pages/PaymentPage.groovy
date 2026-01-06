package pages

import com.kms.katalon.core.testobject.TestObject

import common.helpers.LocatorHelper
import pages.common.CommonPage

public class PaymentPage extends CommonPage {

    // PAYMENT PAGE LOCATOR
    private final TestObject txtNameOnCard = LocatorHelper.paymentFormLocator('Name on Card')
    private final TestObject txtCardNumber = LocatorHelper.paymentFormLocator('Card Number')
    private final TestObject txtCVC = LocatorHelper.paymentFormLocator('CVC')
    private final TestObject txtMMExpiration = LocatorHelper.paymentFormLocator('Expiration', 'expiry_month')
    private final TestObject txtYYYYExpiration = LocatorHelper.toByXpath("//input[@name='expiry_year']")
    private final TestObject btnPayAndConfirm = LocatorHelper.toByXpath("//button[contains(text(), 'Pay and Confirm Order')]")
    private final TestObject placedOrderMsg = LocatorHelper.toByXpath("//div[contains(text(), 'placed successfully')]")

    private final TestObject btnDownloadInvoice = LocatorHelper.toByXpath("//a[contains(text(),'Download Invoice')]")
    private final TestObject btnContinue = LocatorHelper.toByXpath("//a[text()='Continue']")

    // ACTION
    void enterPaymentDetail(Map dataPayment) {
        String nameOnCard  = (dataPayment.nameOnCard  ?: '').toString()
        String cardNumber  = (dataPayment.cardNumber  ?: '').toString()
        String cvc         = (dataPayment.cvc         ?: '').toString()
        String expMonth    = (dataPayment.expiryMonth ?: '').toString()
        String expYear     = (dataPayment.expiryYear  ?: '').toString()

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
    boolean isPlacedOrderMsgVisible() {
        return isDisplayed(placedOrderMsg)
    }

}
