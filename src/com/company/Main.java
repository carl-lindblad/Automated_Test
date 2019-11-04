package com.company;

import Config.PropertiesFile;

import test.buySellNotes.testSellNotes;

public class Main {

    public static void main(String[] args) {
        testSellNotes test = new testSellNotes();
        starterClass app = new starterClass();
        System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");

        app.start();
       // test.ShouldAddValue();

    }
}

