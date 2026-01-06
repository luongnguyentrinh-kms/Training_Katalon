package core

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Paths

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class BrowserSettings {
	static String getProjectDownloadPath() {
	    String path = Paths.get(RunConfiguration.getProjectDir(), "Downloads").toString()
	    new File(path).mkdirs()
	    return path	
	}
	
	// ===================== CHROME / EDGE =====================
    static void setChromeOptions(Map customOptions = null) {
        String downloadPath = getProjectDownloadPath()

        def prefs = [
            "download.default_directory"              : downloadPath,
            "download.prompt_for_download"            : false,
            "profile.default_content_settings.popups" : 0,
            "plugins.always_open_pdf_externally"      : true,
            "credentials_enable_service"              : false,
            "profile.password_manager_enabled"        : false
        ]

        if (customOptions != null) {
            customOptions.each { k, v -> prefs[k] = v }
        }

        RunConfiguration.setWebDriverPreferencesProperty("prefs", prefs)
    }

    // ===================== FIREFOX =====================
    static void setFireFoxOptions(Map customOptions = null) {
        String downloadPath = getProjectDownloadPath()

        def prefs = [
            "browser.download.folderList"            : 2,
            "browser.download.dir"                   : downloadPath,
            "browser.download.useDownloadDir"        : true,
            "browser.download.manager.showWhenStarting": false,
            "browser.helperApps.neverAsk.saveToDisk" : "application/pdf,application/octet-stream,text/plain",
            "pdfjs.disabled"                         : true
        ]

        if (customOptions != null) {
            customOptions.each { k, v -> prefs[k] = v }
        }

        RunConfiguration.setWebDriverPreferencesProperty(
            "moz:firefoxOptions",
            ["prefs": prefs]
        )
    }

    // ===================== ENTRY POINT =====================
    static void setupDownloadFolder() {
        String browserType = DriverFactory.getExecutedBrowser()

        switch (browserType) {
            case WebUIDriverType.CHROME_DRIVER.toString():
            case WebUIDriverType.EDGE_CHROMIUM_DRIVER.toString():
                setChromeOptions()
                break

            case WebUIDriverType.FIREFOX_DRIVER.toString():
            case WebUIDriverType.FIREFOX_HEADLESS_DRIVER.toString():
                setFireFoxOptions()
                break

            default:
                KeywordUtil.markFailedAndStop("Unsupported browser: ${browserType}")
        }
    }
}
