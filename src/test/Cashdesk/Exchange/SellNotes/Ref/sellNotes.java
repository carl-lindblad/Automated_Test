package test.Cashdesk.Exchange.SellNotes.Ref;

import com.company.functions;
import com.company.services;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import test.Cashdesk.CashdeskAdministration.Ref.CashdeskAdministration;

import static test.Cashdesk.CashdeskAdministration.test_CloseOpenCashDesk.openCashdesk;
import static com.company.ID_Basic.ACTIONBOX;
import static test.Cashdesk.Exchange.SellNotes.Ref.ID_SellNotes.*;

public final class sellNotes extends services{
    private functions use = new functions();

    public boolean transactionSuccesful() {
        return use.isAtWelcomePage();
    }

    public void reset() {
        use.reset();
    }
}
