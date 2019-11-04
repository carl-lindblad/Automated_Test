package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.WebDriver;

public class functions {
    public static WebDriver obj;

    private void setObj(WebDriver obj) {
        this.obj = obj;
    }

    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
        //Set Firefox Headless mode as TRUE
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        WebDriver obj = new FirefoxDriver(options);
        obj.get("https://evry.aktors.ee/evry/forex-so.php?to_page=https://www.aktors.ee/forex-demo/index.xhtml");

        setObj(obj);
    }

    public void closeBrowser() {
        obj.close();
    }

    /**
     * @param
     * @param id
     * @param userValue
     */
    public void selectElementInDropdownByValue(String key, String userValue) {
        Select select = new Select(selectElement(key));
        select.selectByValue(userValue);
    }


    /**
     * @param
     * @param id
     * @return the element with the param ID
     */
    public WebElement selectElement(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 10);
        setObj(obj);
        if(isID(key)) {
            return wait.until(ExpectedConditions.elementToBeClickable(By.id(key)));
        }
        else{
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key)));
        }

    }

    public void setTextBoxValue(String key, String value, Boolean hitEnter) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(obj, 10);
        if(isID(key)) {
            if (hitEnter = true) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(key))).sendKeys(value, Keys.ENTER);
                Thread.sleep(1000);
            } else
                wait.until(ExpectedConditions.elementToBeClickable(By.id(key))).sendKeys(value);
            setObj(obj);
        }
        else{
            if (hitEnter = true) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key))).sendKeys(value, Keys.ENTER);
                Thread.sleep(1000);

            } else
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key))).sendKeys(value);
            setObj(obj);
        }
        try {
            Thread.sleep(2500);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickPath(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 10);
        try {
            if (isID(key)) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(key))).click();
                Thread.sleep(2000);
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key))).click();
                Thread.sleep(2000);
            }
        } catch (TimeoutException | InterruptedException e) {
            System.out.println("Something went wrong when clicking identifier: " + key);
        }

    }

    public Boolean isExisting(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 2);
        try {
            if (isID(key)) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(key))).isDisplayed();
                return true;
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key))).isDisplayed();
                return true;
            }

        } catch (TimeoutException t) {
            System.out.println("Couuld not find the identifiter: " + key);
            return false;
        }
    }


    public String getValue(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 10);
        try {
            if (isID(key)) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(key))).getAttribute("value");

            } else {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key))).getText();
            }

        } catch (NoSuchElementException n) {
            System.out.println("Could not find element with identifier: " + key);
            return "";
        } catch (TimeoutException n) {
            System.out.println("Timeout when trying to find identifier: " + key);
            return "";
        }

    }

    public boolean isChecked(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 10);
        boolean checked = false;
        if(isID(key)) {
            checked = wait.until(ExpectedConditions.elementToBeClickable(By.id(key))).isSelected();
        }
        else{
             checked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key))).isSelected();

        }
        return checked;
    }

    public void checkCheckbox(String key) {
        clickPath(key);
    }


    public boolean isXpathVisible(String key) {
        WebDriverWait wait = new WebDriverWait(obj, 2);
        try {
            if(isID(key)) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(key))).isDisplayed();
            }
            else{
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key))).isDisplayed();
            }
        } catch (Exception e) {
            System.out.println("ID visble error with identifier: " + key);
            return false;
        }

    }

    private boolean isID(String key) {
        if (key.contains("/")) {
            return false;
        } else {
            return true;
        }
    }

    public double toDouble(String value) throws ParseException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(obj, 2);
        String input = "";
        try {
                input = value.replace(" ", "");
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number i = format.parse(input);
                return i.doubleValue();
        } catch (ParseException p) {
            System.out.println("Cant parse: " + input);
            return 0;
        }

    }


}

