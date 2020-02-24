package com.company;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.company.ID_Basic.*;

public class services {
    private static functions use = new functions();

    public static boolean cashdeskIsClosed(boolean state) {
        if (state) {
            Assert.assertEquals("Cashdesk is not closed", "N/A", use.getText(By.id(CASHDESK_STATE_TEXT)));
            return true;
        } else {
            Assert.assertNotEquals("Cashdesk is not opened", "N/A", use.getText(By.id(CASHDESK_STATE_TEXT)));
            return false;
        }
    }

    public void isAtPage(String title) {
        use.waitUntilisAtPage(By.id(CONTENT_TITLE), title);
        Assert.assertEquals("Is not at page" + title, use.getText(By.id(CONTENT_TITLE)), title);
    }

    public services goToFunctionality(String shortCut) {
        use.findAndWait(By.id(ACTIONBOX)).sendKeys(shortCut);
        Assert.assertEquals("Could not go to" + shortCut, use.getAttribute(By.id(ACTIONBOX), "value"), shortCut);
        use.findAndWait(By.id(ACTIONBOX)).sendKeys(Keys.ENTER);
        use.waitingForSiteToLoad(1);
        return this;
    }

    public boolean tableHasValues(By tableBody) {
        List<WebElement> tableTD = use.getTableTD(tableBody);
        if (tableTD.size() == 1) {
            return false;

        }
        Assert.assertTrue("Table has no values", tableTD.size() > 1);

        return true;
    }


    public services selectByIndex(By key, Integer index) {
        use.selectElementInDropdownByIndex(key, index);
        System.out.println("Selecting nr: "+index);
        return this;
    }

    public services selectByValue(By key, String value) {
        System.out.println(value);
        WebElement option = use.selectElementByValue(key, value);
        if (!option.getText().equals(value)) {
            use.waitingForSiteToLoad(1);
            option = use.selectElementByValue(key, value);
        }
        Assert.assertEquals("Could not select " + value, option.getText(), value);

        return this;
    }

    public services setTodayAsDate(By key) {
        use.findAndWait(key).sendKeys(use.todaysDate());
        return this;
    }

    public services sendValues(By key, String value) {
        use.findAndWait(key).sendKeys(Keys.CONTROL, "a", "");
        use.findAndWait(key).sendKeys(value);
        use.waitingForSiteToLoad(1);
        use.findAndWait(key).sendKeys(Keys.DOWN,Keys.TAB);
        System.out.println("Sending value: "+value);
        //Does not work with country codes
        //Assert.assertEquals("Could not send value" + value, value, use.getAttribute(key, "value"));

        return this;
    }

    public services msgSuccess(String msg) {

        Assert.assertEquals("Message successfailed: ", msg, use.findAndWait(By.id(SUCCESS_MSG)).getText());
        return this;
    }
    public services click(By key) {
        use.waitingForSiteToLoad(1);
        use.click(key);
        return this;
    }
}
