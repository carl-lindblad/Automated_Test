package test.Cashdesk.Exchange.ReportMissedSales;

import com.company.functions;
import com.company.services;
import org.junit.*;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import test.Cashdesk.Exchange.ReportMissedSales.Ref.reportMissedSales;

import static com.company.functions.isActivated;
import static com.company.test_settings.REPORT_MISSED_SALES_TEST_SETTING;
import static test.Cashdesk.Exchange.ReportMissedSales.Ref.ID_report_missed_sales.*;


@FixMethodOrder
public class test_reportMissedSales {
    private services given = new services();
    private services when = new services();
    private reportMissedSales then = new reportMissedSales();
    private static functions check = new functions();

    @After
    public void reset() {
        if(isActivated){
            check.reset();
        }
    }
    @BeforeClass
    public static void isTestActivated(){
        Assume.assumeTrue(REPORT_MISSED_SALES_TEST_SETTING);
    }

    @Test
    @Ignore
    @Order(2)
    public void searchForMissedSales(){

        given
                .goToFunctionality("M");
        when
                .selectByIndex(By.id(BRANCH_DROPDOWN), 0)
                .selectByIndex(By.id(USER_DROPDOWN), 0)
                .setTodayAsDate(By.id(DATE_FROM_DATEPICKER))
                .setTodayAsDate(By.id(DATE_TO_DATEPICKER))
                .sendValues(By.id(CURRENCY_ADD_TEXTBOX), "")
                .click(By.id(TRANSACTION_TYPE_SEARCH_BUY_RADIOBUTTON))
                .click(By.id(SEARCH_BUTTON));
        then
                .resultShown();
    }

    @Test
    @Order(3)
    public void createMissedSale_sell(){
        given
                .goToFunctionality("M");
        when
                .selectByValue(By.id(TRANSACTION_TYPE_DROPDOWN), "Sell Notes")
                .sendValues(By.id(CURRENCY_ADD_TEXTBOX), "SEK")
                .sendValues(By.id(AMOUNT_TEXTBOX), "100,00")
                .selectByIndex(By.id(COMMENT_DROPDOWN), 1)
                .click(By.id(CONFIRM_BTN));
        then
                .msgAppear("Missed sales reported");
    }
    @Test
    @Order(1)
    public void createMissedSale_buy(){

        given
                .goToFunctionality("M");
        when
                .selectByValue(By.id(TRANSACTION_TYPE_DROPDOWN), "Buy Notes")
                .sendValues(By.id(CURRENCY_ADD_TEXTBOX), "EUR")
                .sendValues(By.id(AMOUNT_TEXTBOX), "100,00")
                .selectByIndex(By.id(COMMENT_DROPDOWN), 2)
                .click(By.id(CONFIRM_BTN));
        then
                .msgAppear("Missed sales reported");
    }

}
