package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Driver;

public class functions {
    /**
     *
     * @param obj
     */
    public void openBrowser(WebDriver obj) {
        obj.get("https://evry.aktors.ee/evry/forex-so.php?to_page=https://www.aktors.ee/forex-demo/index.xhtml");
    }

    /**
     *
     * @param obj
     * @param id
     * @param userValue
     *
     */
    public void selectIDInDropdownByValue(WebDriver obj, String id, String userValue) {
       Select select = new Select(selectElement(obj,id));
       select.selectByValue(userValue);
    }


    /**
     *
     * @param obj
     * @param id
     * @return the element with the param ID
     */
    public WebElement selectElement(WebDriver obj, String id) {
        WebDriverWait wait = new WebDriverWait(obj, 5);
        return  wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    public void setTextBoxValue(WebDriver obj, String id, String value, Boolean hitEnter){
        WebDriverWait wait = new WebDriverWait(obj, 5);
        if(hitEnter = true)
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).sendKeys(value, Keys.ENTER);
        else
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).sendKeys(value);

    }
        public void click(WebDriver obj, String id){
        selectElement(obj, id).click();
    }}
