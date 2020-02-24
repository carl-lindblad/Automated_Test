package test.Cashdesk.CustomerOrder.Ref;

import com.company.functions;
import com.company.services;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.company.ID_Basic.CONTENT_TITLE;
import static test.Cashdesk.CustomerOrder.Ref.ID_CreateCustomerOrder.*;

public class CustomerOrder extends services {
    private functions use = new functions();

    public void reset() {
        use.reset();
    }

    public void isAtRegisterCustomer() {
        Assert.assertTrue("Failed register customer", use.isDisplayed(By.id(VERIFYING_IS_AT_REGISTER_CUSTOMER)));
    }

    public void isAtCreateOrder() {
        Assert.assertTrue("Failed creating order", use.isDisplayed(By.id(VERIFYING_IS_AT_CREATE_ORDER)));
    }


    public void orderIsCreated() {
       Assert.assertTrue("Failed when creating order" , use.findAndWait(By.id(VERIFYING_IS_AT_ORDER_CREATED)).isDisplayed());
    }


    public void isPickPopUpDisplayed() {
        Assert.assertTrue("Click on pop up failed", use.isDisplayed(By.id(AUTHORIZATION_APPROVE_BTN)));
    }

    public void hasCancelled() {
        Assert.assertEquals("Failed to cancel to Authorize Order popup", "Authorize Order(AO)", use.getText(By.id(CONTENT_TITLE)));
    }

    public void IsAtConfirmPopup() {
        Assert.assertTrue("Failed to approve authorization", use.isDisplayed(By.id(AUTHORIZATION_CONFIRM_COMMENT_BTN)));
    }

    public void authorizationSuccesful() {
        isAtPage("Authorize Order(AO)");
    }
}
