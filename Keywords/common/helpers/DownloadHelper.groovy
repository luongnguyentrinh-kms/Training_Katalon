package common.helpers

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import core.Browser
import internal.GlobalVariable

public class DownloadHelper {
	
	static String getDownloadDir() {
		return Paths.get(RunConfiguration.getProjectDir(), "Downloads").toString()
	}

    static void cleanDownloadFolder() {
        File dir = new File(getDownloadDir())
        if (!dir.exists()) dir.mkdirs()
        dir.listFiles()?.each { it.delete() }
    }


    static File waitInvoiceDownloaded(int timeoutSec = 30) {
        File dir = new File(getDownloadDir())
        long end = System.currentTimeMillis() + timeoutSec * 1000L

        while (System.currentTimeMillis() < end) {
            File[] files = dir.listFiles()

            if (files != null && files.length > 0) {
                File file = files.find { 
                    !it.name.endsWith(".crdownload") 
                }

                if (file != null) {
                    WebUI.comment("File downloaded: ${file.absolutePath} (${file.length()} bytes)")
                    return file
                }
            }
            WebUI.delay(1)
        }

        WebUI.comment("No file found in: " + dir.absolutePath)
        return null
    }
}
