package common.helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

public class LocatorHelper {

    static TestObject toById(String id) {
        TestObject to = new TestObject()
        to.addProperty('id', ConditionType.EQUALS, id)
        return to
    }

    static TestObject toByName(String name) {
        TestObject to = new TestObject()
        to.addProperty('name', ConditionType.EQUALS, name)
        return to
    }

    static TestObject toByCss(String css) {
        TestObject to = new TestObject()
        to.addProperty('css', ConditionType.EQUALS, css)
        return to
    }

    static  TestObject toByXpath(String xpath) {
        TestObject to = new TestObject()
        to.addProperty('xpath', ConditionType.EQUALS, xpath)
        return to
    }

    static TestObject deliveryAddressByPostPath(String postPath) {
        String deliveryAddressBaseXpath =
        "//h3[contains(text(), 'delivery address')]/parent::li/following-sibling::li"

        return LocatorHelper.toByXpath(
            "${deliveryAddressBaseXpath}${postPath}"
        )
    }

    static TestObject paymentFormLocator(String nameField, String name = '') {
        String baseXpath = "//label[contains(text(),'${nameField}')]/following-sibling::input"

        if (!name.equals('')) {
            String postPath = "[@name='${name}']"
            return LocatorHelper.toByXpath(baseXpath + postPath)
        }
        return LocatorHelper.toByXpath(baseXpath)
    }

    static TestObject cartTableLocator(String postPath = '') {
        String baseXpath = "//table[@id='cart_info_table']"
        if (!postPath.equals('')) {
            return LocatorHelper.toByXpath(baseXpath + postPath)
        }

        return LocatorHelper.toByXpath(baseXpath)
    }

}
