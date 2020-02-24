package test.IdentifyCustomer.Ref;

import com.company.functions;
import com.company.services;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static test.IdentifyCustomer.Ref.ID_identify_Customer.*;

public class identifyCustomer {
    private services start = new services();
    private functions use = new functions();
    private ID_identify_Customer collect = new ID_identify_Customer();

    public identifyCustomer openIdentifyCustomer() {
        use.waitingForSiteToLoad(1);
        use.findAndWait(By.id(IDENTIFY_CUSTOMER_LINK)).click();
        return this;
    }

    public identifyCustomer searchWithPIN(String pin) {
        use.findAndWait(By.id(IDENTIFY_CUSTOMER_BY_PIN_BOX)).sendKeys(pin, Keys.ENTER);
        return this;
    }

    public identifyCustomer selectCustomer() {
        start.click(By.id(SELECT_CUSTOMER_ARROW));
        return this;
    }

    public void customerSelected() {
        Assert.assertNotEquals("Testing customer selected", "Identify customer", use.findAndWait(By.id(IDENTIFIED_CUSTOMER_LINK)).getText());
    }

    public identifyCustomer selectCustomerIdentification() {
        use.selectElementInDropdownByIndex(By.id(SELECT_DOCUMENT_DROPDOWN), 1);
        use.waitingForSiteToLoad(1);
        use.findAndWait(By.id(SAVE_CUSTOMER_IDENTIFICATION_BTN)).click();
        return this;
    }

    public identifyCustomer crossClicked() {
        start.click(By.id(UNIDENTIFY_CUSTOMER_IKON));
        return this;
    }

    public void customerUnidentified() {
        Assert.assertTrue("Testing customer unidentified", use.isAtWelcomePage());
    }

    public identifyCustomer unidentifyClicked() {
        use.waitingForSiteToLoad(1);
        start.click(By.id(UNIDENTIFY_CUSTOMER_LINK));
        return this;
    }


}
