package com.company;

import com.company.Login.ChooseBranchPage;
import com.company.Login.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.Cashdesk.CashdeskAdministration.test_CloseOpenCashDesk;
import test.Cashdesk.CustomerOrder.test_AuthorizeOrder;
import test.Cashdesk.CustomerOrder.test_CreateOrder;
import test.Cashdesk.Exchange.ReportMissedSales.test_reportMissedSales;
import test.Cashdesk.Exchange.SellNotes.test_SellNotes;
import test.Cashdesk.Exchange.TransferProduct.test_TransferProduct;
import test.Customer.MaintainCustomer.test_MaintainCustomer;
import test.IdentifyCustomer.test_identifyCustomer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        test_CreateOrder.class,
        test_identifyCustomer.class,
        test_SellNotes.class,
        test_CloseOpenCashDesk.class,
        test_reportMissedSales.class,
        test_MaintainCustomer.class,
        test_TransferProduct.class,
        test_AuthorizeOrder.class

})
public class oldMain {
    private static functions use = new functions();
    private static Login login = new Login();
    private static ChooseBranchPage chooseBranchPage = new ChooseBranchPage();


    @BeforeClass
    public static void openBrowser(){
        use.openBrowser();
        login.testingLogin();
        chooseBranchPage.testingSelectingBranch();
    }
    @AfterClass
    public static void closeBrowser() {
        use.waitingForSiteToLoad(10);
        use.closeBrowser();
    }
}
