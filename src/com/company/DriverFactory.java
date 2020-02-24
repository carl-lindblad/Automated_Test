package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.company.test_settings.HEADLESS_SETTING;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {
        //Prevent instatiation
    }

    public static WebDriver getFirefoxDriver() {
        if (driver == null) {

            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(HEADLESS_SETTING);
            System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
            driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    static WebDriverWait getWebDriverWait(int timeOut) {
        FluentWait wait = new WebDriverWait(getFirefoxDriver(), timeOut);
        return (WebDriverWait) wait;
    }
}
