package test.Cashdesk.Exchange.SellNotes;

import com.company.functions;
import org.junit.*;

import static com.company.test_settings.SELL_NOTE_TEST_SETTING;

import org.openqa.selenium.By;
import test.Cashdesk.Exchange.SellNotes.Ref.sellNotes;
import static test.Cashdesk.Exchange.SellNotes.Ref.ID_SellNotes.*;


public class test_SellNotes {
    private sellNotes given = new sellNotes();
    private sellNotes when = new sellNotes();
    private sellNotes then = new sellNotes();
    private static functions start = new functions();

    @After
    public void reset() {
        then.goToFunctionality("WS");
    }

    @BeforeClass
    public  static void isTestActivated(){
        Assume.assumeTrue(SELL_NOTE_TEST_SETTING);
    }
    @Test
    public void testingSellNotesCash() {
        given
                .goToFunctionality("SN");
        when
                .sendValues(By.id(CURRENCY_TYPE_TEXTFIELD), "EUR")
                .sendValues(By.id(FOREIGN_CURRENCY_BOX), "10,00")
                .click(By.id(CONFIRM_TRANSACTION))
                .click(By.id(PAYMENT_BTN))
                .click(By.id(PAYMENT_CASH_BTN))
                .click(By.id(PAYMENT_OK_BTN))
                .click(By.id(SESSION_END_BTN));
        then
                .transactionSuccesful();
    }
}
