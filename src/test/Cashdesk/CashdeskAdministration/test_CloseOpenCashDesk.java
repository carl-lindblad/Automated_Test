package test.Cashdesk.CashdeskAdministration;

import com.company.functions;
import com.company.services;
import org.junit.After;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import test.Cashdesk.CashdeskAdministration.Ref.CashdeskAdministration;
import static test.Cashdesk.CashdeskAdministration.Ref.ID_CashdeskAdminstration.*;


import static com.company.test_settings.CLOSE_OPEN_CASHDESK_TEST_SETTING;

public class test_CloseOpenCashDesk {
    private static CashdeskAdministration given = new CashdeskAdministration();
    private static CashdeskAdministration when = new CashdeskAdministration();
    private static CashdeskAdministration then = new CashdeskAdministration();

    @After
    public void reset() {
        then.reset();
    }

    @BeforeClass
    public static void isTestActivated() {
        Assume.assumeTrue(CLOSE_OPEN_CASHDESK_TEST_SETTING);
    }

    @Test
    public void setUpCashdesk() {
        if (given.cashdeskIsClosed(true)) {
            openCashdesk();
        } else {
            closeCashdesk();
        }

        if (given.cashdeskIsClosed(false)) {
            closeCashdesk();
        } else {
            openCashdesk();
        }

    }


    private static void closeCashdesk() {
        given
                .goToFunctionality("901")
                .click(By.id(CONFIRM_CASHDESK_BTN))
                .tableHasValues(By.id(CURRENCIES_TABLEHEAD));
        when
                .balancingCurrencies()
                .click(By.id((SAVE_BALANCE_BTN)))
                .click(By.id(LEAVE_BALANCE_LINK))
                .click(By.id(CLOSE_CASHDESK_BTN));

        then
                .cashdeskChanged();
    }


    public static void openCashdesk() {
        given
                .goToFunctionality("901")
                .click(By.id(FOURTH_CASHDESK_RADIOBTN))
                .click(By.id(CONFIRM_CASHDESK_BTN))
                .tableHasValues(By.id(CURRENCIES_TABLEHEAD));
        when
                .click(By.id(LEAVE_BALANCE_LINK))
                .click(By.id(CONFIRM_CASHDESK_BTN));
        then
                .cashdeskChanged();
    }
}
