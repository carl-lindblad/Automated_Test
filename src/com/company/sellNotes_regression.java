package com.company;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sellNotes_regression {
    functions use = new functions();
    public void Regression(WebDriver obj) {
        sellNote(obj);

    }

    /**
     * 4.1.5 Flow Sell Notes Currency included, Example SNUSD
     */
    private void sellNote (WebDriver obj){
        //Writes Sneur command in action field
        use.setTextBoxValue(obj,"ibform:actionCode_input", "SNUSD",true);

        //Sets value in foreign box
        use.setTextBoxValue(obj, "ucform:vexla-table:0:foreign", "100", true);
        use.click(obj, "ucform:execute-button");

    }


}
