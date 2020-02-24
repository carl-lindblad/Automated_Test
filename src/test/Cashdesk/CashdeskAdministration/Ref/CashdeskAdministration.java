package test.Cashdesk.CashdeskAdministration.Ref;

import com.company.functions;
import com.company.services;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static test.Cashdesk.CashdeskAdministration.Ref.ID_CashdeskAdminstration.*;
import static com.company.ID_Basic.ACTIONBOX;

public class CashdeskAdministration extends services {

    private functions use = new functions();
    private static services start = new services();

    public void reset() {
        use.reset();
    }


    public CashdeskAdministration balancingCurrencies() {
        List<String> values;
        use.setElementListFromTable(By.id(CURRENCIES_TABLEHEAD));
        values = use.getValuesFromDynamicElements("ucform:currency-compartments-dt:", ":cb-balance", "Balance");

            int index = 0;
            for (String value : values
            ) {
                WebElement temp = use.findDynamicElements("ucform:currency-compartments-dt:", ":amt-input", "Amount", index);
                temp.sendKeys(Keys.CONTROL, "A");
                use.waitingForSiteToLoad(1);
                temp.sendKeys(value);
                temp.sendKeys(Keys.TAB);
                use.waitingForSiteToLoad(1);
                index++;
            }
        return this;
    }
    public void cashdeskChanged() {
        Assert.assertTrue("Testing if cashdesk is changed", use.isAtWelcomePage());
    }

}
