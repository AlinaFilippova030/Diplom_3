package site.stellarburgers.utils;

import org.openqa.selenium.WebDriver;
public class BrowserUtils {
    /**
     * Закрывает браузер, если он открыт.
     *
     * @param driver Экземпляр WebDriver.
     */
    public static void closeBrowser (WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
