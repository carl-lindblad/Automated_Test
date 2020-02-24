package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static com.company.DriverFactory.getFirefoxDriver;
import static com.company.DriverFactory.getWebDriverWait;
import static com.company.test_settings.IS_SET_TO_ST;
import static com.company.ID_Basic.CONTENT_TITLE;
import static com.company.ID_Basic.LOADING_BLOCK;
import static com.company.Login.ref.ID_Login.CONFIRM_REVERSAL_BTN;
import static com.company.Login.ref.ID_Login.REVERSE_ALL_TRANSACTION_BTN;


public class functions {
    private WebDriver driver = getFirefoxDriver();
    private WebDriverWait wait;
    public static boolean isActivated = false;
    Map<String, Integer> numRowsByColumns = new HashMap<String, Integer>();
    private By previousKey = null;
    private String previousValue = "";
    private static Map<String, String> settings = new HashMap<>();

    /**
     * Static function that clicks the remove all transaction in counter services
     */
    public void removeAllTransactions() {
        try {
            findAndWait(By.id(REVERSE_ALL_TRANSACTION_BTN)).click();
            Thread.sleep(1000);
            findAndWait(By.id(CONFIRM_REVERSAL_BTN)).click();
            goToWelcomePage();
        } catch (Exception e) {
            goToWelcomePage();
        }
    }

    /**
     * Takes the amount a sec the driver should wait before continue
     * @param sec
     */
    public void waitingForSiteToLoad(double sec) {
        try {
            Thread.sleep((long) (sec * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Takes a table and an returns the id for each row. Only applies for dynamic ID:s such as :
     * Row 1 = ucform:currency-compartments-dt:0:cb-diff
     * Row 2 = ucform:currency-compartments-dt:0:cb-diff
     * Row 3 = ucform:currency-compartments-dt:0:cb-diff
     * Get rows via numRowsByColumn from function setElementListFromTable()
     * @param firstHalf
     * @param secondHalf
     * @param columnHeader
     * @param index
     * @return
     */
    public WebElement findDynamicElements(String firstHalf, String secondHalf, String columnHeader, int index) {
        String key = "";
        for (Map.Entry<String, Integer> column : numRowsByColumns.entrySet()
        ) {
            if (column.getKey().equals(columnHeader)) {
                for (int i = 0; i < column.getValue(); i++) {
                    if (index == i) {
                        key = firstHalf + i + secondHalf;
                        return findAndWait(By.id(key));
                    }

                }
                break;
            }
        }
        return null;
    }

    /**
     * Returns a list with all the values from each row in a column in specifed column by columnName
     *
     * @param firstHalf
     * @param secondHalf
     * @param columnHeader
     * @return
     */
    public List getValuesFromDynamicElements(String firstHalf, String secondHalf, String columnHeader) {
        String key = "";
        List<String> values = new ArrayList<>();
        if(numRowsByColumns == null){
            return null;
        }
        else {
            for (Map.Entry<String, Integer> column : numRowsByColumns.entrySet()
            ) {
                if (column.getKey().equals(columnHeader)) {
                    for (int i = 0; i < column.getValue(); i++) {
                        key = firstHalf + i + secondHalf;
                        String value = findAndWait(By.id(key)).getText();

                        values.add(value.substring(0, value.length() - 3));

                    }
                    break;
                }
            }
            return values;
        }
    }

    public  List<WebElement> getTableTR(By tableKey){
        WebElement table = findAndWait(tableKey);
        return table.findElements(By.tagName("tr"));
    }
    public List<WebElement> getTableTD(By tableBody){
        List<WebElement> tableTR = getTableTR(tableBody);
        List<WebElement> tableTD = null;

        for (WebElement td: tableTR
             ) {
            tableTD = td.findElements(By.tagName("td"));
        }
        return tableTD;
    }



    /**
     * sets the key (colname) and value (allColumnsize) based on the table head in the map numRowsByColumn
     * @param tableKey
     */
    public void setElementListFromTable(By tableKey) {
        List<WebElement> allColumns = getTableTR(tableKey);
        if (allColumns.size() < 2){
            this.numRowsByColumns = null;
        }
        else{
            for (WebElement col : allColumns) {
                List<WebElement> cells = col.findElements(By.tagName("th"));
                for (WebElement colName : cells
                ) {
                    this.numRowsByColumns.put(colName.getText(), allColumns.size() - 1);
                }
            }
        }
    }

    public void goToWelcomePage() {
        findAndWait(By.id("ibform:actionCode_input")).sendKeys("WS", Keys.ENTER);
    }

    public boolean isAtWelcomePage() {
        wait = getWebDriverWait(3);
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(CONTENT_TITLE), "Welcome Screen(WS)"));
        } catch (Exception e) {
            return false;
        }
    }

    public String todaysDate() {
        return java.time.LocalDate.now().toString();
    }

    public void reset(){
        goToWelcomePage();
    }

    public void setPreviousValue(String value) {
        this.previousValue = value;
    }

    /**
     * Wait until the previous value is set
     * @return
     */
    public boolean waitUntilOldValueIsSet() {
        wait = getWebDriverWait(5);
        boolean isSet = wait.until(ExpectedConditions.textToBePresentInElementValue(previousKey, previousValue));
        return isSet;

    }

    /**
     * Tries to find element. Depending on the Exception it either tries again or writes an error.
     * @param key
     * @return
     */
    public WebElement findAndWait(By key) {
        waitingForSiteToLoad(1);
        this.previousKey = key;
        WebElement temp = null;
        int attempts = 0;
        wait = getWebDriverWait(5);
        while (attempts < 10) {

            try {
                temp = wait.until(ExpectedConditions.visibilityOfElementLocated(key));
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                waitingForSiteToLoad(0.5);
            } catch (TimeoutException t) {
                System.out.println("findAndWait: Selenium could not find: " + key);
            }
            if (temp != null) {
                break;
            }
            attempts++;
        }
        return temp;
    }

    public void selectElementInDropdownByIndex(By key, int index) {
        Select select = trySelect(key);
        int attempts = 0;
        while (attempts < 10) {
            try {
                this.previousValue = select.getOptions().get(index).getText();
                select.selectByIndex(index);
                System.out.println(select.getOptions().get(index).getText());
                break;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                waitingForSiteToLoad(0.5);
            }
            catch (TimeoutException t){
                System.out.println("selectElementInDropdownByIndex| ERROR: Could not select:" + previousValue);
            }

            attempts++;
        }



    }

    public boolean isDisplayed(By key) {
        wait = getWebDriverWait(1);
        return driver.findElements(key).size() != 0;
    }

    public void waitUntilisAtPage(By key, String title){
        WebDriverWait wait = getWebDriverWait(5);
        wait.until(ExpectedConditions.textToBe(key, title));
    }

    /**
     * If a problem occurs when trying to select element in drop down an error message i printed
     * @param key
     * @param value
     * @return
     */
    public WebElement selectElementByValue(By key, String value) {
        System.out.println("selectElementByValue| Trying to select: " + value);
        Select select = trySelect(key);
        int attempts = 0;
        while (attempts < 5) {
            try {
                this.previousValue = value;
                select.selectByVisibleText(value);
                System.out.println(select.getFirstSelectedOption().getText());

                break;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                System.out.println("selectElementByValue| ERROR: Could not select:" + value);
                waitingForSiteToLoad(0.5);
            }
            attempts++;
        }

        return select.getFirstSelectedOption();
    }

    /**
     * Trying to select an element. Depending on exception it will either try again or display an error.
     * @param key
     * @return
     */
    public Select trySelect(By key) {
        WebDriverWait wait = getWebDriverWait(5);
        int count = 0;
        Select select = null;
        while (count < 20) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(key));
                select = new Select(findAndWait(key));
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                waitingForSiteToLoad(0.5);
                System.out.println("trySelect| waiting to find: " + key);
            } catch (TimeoutException t) {
                System.out.println("trySelect| Selenium could not select: " + key);
            }
            if (select != null) {
                return select;
            }
            count++;
        }
        return select;
    }

    public ArrayList getAllElementsFromDropdown(By key) {
        ArrayList<String> options = new ArrayList<>();
        Select select = trySelect(key);
        for (WebElement option : select.getOptions()
        ) {
            String value = option.getText();
            options.add(value);
        }
        return options;

    }

    public boolean isTestActivated(String className) {
        for (Map.Entry<String, String> key : settings.entrySet()
        ) {
            String propName = key.getKey();
            String propValue = key.getValue();
            if (propName.equals(className) && propValue.equals("true")) {
                return true;
            }
        }
        return false;
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void openBrowser() {

        if (IS_SET_TO_ST) {
            driver.get("https://bankportal.preprod-restricted.evry.com/authpriv/login/basicauth?configKey=saljstod-s1&orgId=00460087");
        } else {
            driver.get("https://evry.aktors.ee/evry/forex-so.php?to_page=https://www.aktors.ee/forex-demo/index.xhtml");
        }
    }

    /**
     * Not used
     */
    public void waitWhileLoading() {
        int count = 0;
        waitingForSiteToLoad(0.5);
        while (isDisplayed(By.id(LOADING_BLOCK))) {
            if (count == 10) {
                break;
            }
            waitingForSiteToLoad(0.5);
            if (!isDisplayed(By.id(LOADING_BLOCK))) {
                break;
            }
            count++;
        }
    }
    public void click(By key) {
        boolean isClickable = false;
        int counter = 0;
        while (!isClickable) {
            try {
                findAndWait(key).click();
                isClickable = true;
            } catch (Exception e) {
                waitingForSiteToLoad(1);
            }
            if (counter > 5) {
                break;
            }
            counter++;
        }
    }

    public String getAttribute(By key, String type) {
        boolean isUsable = false;
        String temp = null;
        int counter = 0;
        while (!isUsable) {
            try {
                temp = findAndWait(key).getAttribute(type);
                isUsable = true;
            } catch (Exception e) {
                waitingForSiteToLoad(1);
            }
            if (counter > 5 || temp != null) {
                break;
            }
            counter++;
        }
        return temp;
    }

    public String getText(By key) {
        boolean isUsable = false;
        String temp = null;
        int counter = 0;
        while (!isUsable) {
            try {
                temp = findAndWait(key).getText();
                isUsable = true;
            } catch (Exception e) {
                waitingForSiteToLoad(1);
            }
            if (counter > 5 || temp != null) {
                break;
            }
            counter++;
        }
        return temp;
    }

}

