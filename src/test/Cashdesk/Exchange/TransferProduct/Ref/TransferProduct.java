package test.Cashdesk.Exchange.TransferProduct.Ref;
import com.company.functions;
import com.company.services;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.company.ID_Basic.SUCCESS_MSG;
import static test.Cashdesk.Exchange.TransferProduct.Ref.ID_TransferProduct.*;

import static com.company.ID_Basic.ACTIONBOX;

public class TransferProduct {
    private functions use = new functions();
    private services start = new services();


    public void reset() {
        use.reset();
    }

    public TransferProduct TPopen() {
        use.findAndWait(By.id(ACTIONBOX)).sendKeys("TP", Keys.ENTER);
        return this;
    }


    public TransferProduct setProduct(String productType) {
        use.selectElementByValue(By.id(PRODUCT_TYPE_DROPDOWN), productType);
        return this;
    }
    public TransferProduct setCurrency(String currency) {
        use.selectElementByValue(By.id(CURRENCY_DROPDOWN), currency);
        return this;
    }
    public TransferProduct setAmount(Integer value) {
        use.waitingForSiteToLoad(1);
        use.findAndWait(By.id(AMOUNT_TEXT_FIELD)).sendKeys(value.toString());
        use.findAndWait(By.id(AMOUNT_TEXT_FIELD)).sendKeys(Keys.ENTER);
        use.setPreviousValue(value.toString());
        return this;
    }
    public TransferProduct setSource(int index) {
        use.waitUntilOldValueIsSet();
        use.selectElementInDropdownByIndex(By.id(SOURCE_DROPDOWN), index);
        return this;
    }
    public TransferProduct setDestination(int index) {
        use.selectElementInDropdownByIndex(By.id(DESTINATION_DROPDOWN), index);
        return this;
    }
    public TransferProduct setComment(String comment) {
        use.findAndWait(By.id(COMMENT_TEXT_FIELD)).sendKeys(comment, Keys.ENTER);

        return this;
    }
    public TransferProduct confirm() {
        start.click(By.id(CONFIRM_BTN));
        return this;
    }
    public void isTransferSuccesful() {
        Assert.assertEquals("Transfer unsuccessful", "Transaction completed", use.getText(By.id(SUCCESS_MSG)));
    }
}
