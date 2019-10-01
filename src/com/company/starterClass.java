package com.company;

import Config.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class starterClass{
    public static String usernameLogin;

    public void start(WebDriver obj){


        functions start = new functions();
        start.openBrowser(obj);
        PropertiesFile.readPropertiesFile();
        start.selectIDInDropdownByValue(obj, "dynSo", usernameLogin);
        //login
        //start.clickById(obj,"dynLoginSbmt");
        start.click(obj, "dynLoginSbmt");
        //Select branch Hitta länk direkt till branch https://www.aktors.ee/forex-demo/index.xhtml
        start.click(obj,"ucform:selbranch:0");
        //Press OK
        start.click(obj,"ucform:btnOK");
        //login to Branch
        start.click(obj,"ucform:btnlogin");
        //If someone else is logged in, kick them out
        start.click(obj,"ucform:activeSession:affirmBtn");
        //Logged in
    }
}
