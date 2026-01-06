package common.helpers

import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DownloadHelper {

    static String getDownloadDir() {
        return Paths.get(RunConfiguration.getProjectDir(), 'Downloads').toString()
    }

    static void cleanDownloadFolder() {
        File dir = new File(getDownloadDir())
        if (!dir.exists()) {
            dir.mkdirs()
        }
        dir.listFiles()?.each { it.delete() }
    }

    static File waitInvoiceDownloaded(int timeoutSec = 30) {
        File dir = new File(getDownloadDir())
        long end = System.currentTimeMillis() + timeoutSec * 1000L

        while (System.currentTimeMillis() < end) {
            File[] files = dir.listFiles()

            if (files != null && files.length > 0) {
                File file = files.find {
                    !it.name.endsWith('.crdownload')
                }

                if (file != null) {
                    WebUI.comment("File downloaded: ${file.absolutePath} (${file.length()} bytes)")
                    return file
                }
            }
            WebUI.delay(1)
        }

        WebUI.comment('No file found in: ' + dir.absolutePath)
        return null
    }

}
