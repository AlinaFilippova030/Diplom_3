package site.stellarburgers.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex"); // Путь к Yandex на macOS
                return new ChromeDriver(yandexOptions);
            case "chrome":
            default:
                String driverPath = "chromedriver";
                System.setProperty("webdriver.chrome.driver", driverPath);
                return new ChromeDriver();
        }
    }
}