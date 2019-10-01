package com.company;

import Config.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
        starterClass app = new starterClass();
        identifyCustomer identify = new identifyCustomer();
        sellNotes_regression start = new sellNotes_regression();
        WebDriver obj = new FirefoxDriver();
        app.start(obj);

        //identify.customer(obj);

        start.Regression(obj);



        PropertiesFile.readPropertiesFile();
    }
}

