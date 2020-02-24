package test.Cashdesk.Exchange.ReportMissedSales.Ref;

import com.company.functions;
import com.company.services;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.company.ID_Basic.ACTIONBOX;
import static com.company.ID_Basic.SUCCESS_MSG;
import static test.Cashdesk.Exchange.ReportMissedSales.Ref.ID_report_missed_sales.*;


public class reportMissedSales extends services{
    private functions use = new functions();
    private services start = new services();


    public void reset() {
        use.reset();
    }


    public boolean msgAppear(String msg) {
        return use.findAndWait(By.id(SUCCESS_MSG)).getText().equals(msg);
    }

    public boolean resultShown() {
        return !use.getText(By.id(SEARCH_RESULTS_TABLE)).equals("No records found.");
    }
}
