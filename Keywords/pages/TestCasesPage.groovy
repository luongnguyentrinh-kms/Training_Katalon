package pages

import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testobject.TestObject

import common.helpers.LocatorHelper
import pages.common.CommonPage

public class TestCasesPage extends CommonPage {

    // TEST CASES PAGE LOCATOR
    private final TestObject lblTestCasesTitle = LocatorHelper.toByXpath("//h2[normalize-space()='Test Cases']")

    // VERIFY
    boolean isTestCasesPageVisible() {
        return isDisplayed(lblTestCasesTitle)
    }

}
