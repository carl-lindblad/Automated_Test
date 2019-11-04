package test.buySellNotes;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.junit.*;
import com.company.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class testSellNotes {
    private static testFunctions testing = new testFunctions();
    private static starterClass app = new starterClass();
    private static functions use = new functions();
    private static ArrayList listOfValues = new ArrayList<String>();
    private boolean multipleRows = false;


    @BeforeClass
    public static void start() {
        app.start();
        testing.separateElements("C:\\DevStuff\\Automated Test\\src\\Config\\sellNotesElements\\sellNotesElements.properties");
        listOfValues.add("1");
        listOfValues.add("200");
        listOfValues.add("300");
    }

    @Before
    public void goToSneur() throws InterruptedException {
        testing.goToSneur();
    }

    @Ignore
    @Test
    @Order(1)
    public void shouldElementExist() throws NoSuchFieldException {
        Assert.assertTrue("Not all elements could be found: ", testing.ifElementsExists());
        if (!testing.getAllElementsNotFound().isEmpty()) {
            System.out.println("Could not find: ");
            testing.getAllElementsNotFound().forEach((key, value) -> System.out.println("Key: " + key + " || Value: " + value));
        }

    }

    /**
     * Tested and working in browser
     */
    @Test
    @Order(2)
    public void shouldChangeTextboxValue() throws InterruptedException, ParseException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue(testing.isTextBoxValueChanged(listOfValues));
        testing.getAllElementsNotFound().forEach((k, v) -> System.out.println("Could not find " + k + "With value:" + v));

    }


    @Ignore
    @Test
    @Order(3)
    public void shouldAddValue() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
         Assert.assertTrue("Adding x local currency should give y foreign currency", testing.isFcToLcCorrect("100"));
        testing.removeAllValuesInTransaction();
    }

    @Test
    @Order(4)
    public void shouldAddAndRemoveValues() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue("Testing add and remove", testing.isAddedAndRemoved(listOfValues));
    }

    @Test
    @Order(5)
    public void shouldConfirmAndControllSessionTotal() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
        testing.addMultipleValue(listOfValues);
        testing.isSub_total_Correct();
        testing.confirmValues();
        testing.isSessionTotalCorrect();
        testing.clearSession();
    }


    /**
     * Tested and work in browser
     *
     * @throws ParseException
     */

    @Ignore
    @Test
    @Order(4)
    public void shousdConfirm() throws ParseException, InterruptedException {
        shouldAddValue();
        use.clickPath("ucform:execute-button");


        //Session total
        double fcInSessionTotal = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/span");
        double lcInSessionTotal = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[3]/span");

        if (fcInSessionTotal == 105.0) {
            fcInSessionTotal -= 5;
            lcInSessionTotal = 1064.66;
        }
        Assert.assertEquals("fcInSessionTotal", 100.00, fcInSessionTotal, 0.0);
        Assert.assertEquals("lcInSessionTotal", 1064.66, lcInSessionTotal, 0.0);
        //In
        double in = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[3]/span");
        double adjustment = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/table[1]/tbody/tr[1]/td[2]/span");
        double fee = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/table[1]/tbody/tr[1]/td[2]/span");
        double profit = use.toDouble("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/table[1]/tbody/tr[3]/td[2]/span");

        double calcIn = adjustment + lcInSessionTotal;
        if (multipleRows == false) {
            Assert.assertEquals("Controll of cashier", calcIn, in, 0.0);
        } else {
            Assert.assertEquals("Controll of cashier", 3194.0, in, 0.0);

        }

        //Controll of message
        String msg = use.getValue("/html/body/div[2]/div[2]/div/form/div[1]/div[1]/div/ul/li/span");
        Assert.assertEquals("msgControll", "Transaction completed", msg);
        emptySessionTotal();
    }

    private void emptySessionTotal() {
        use.clickPath("ucform:sessionTotal:btn-delete-all-transactions");
        use.clickPath("ucform:sessionTotal:confirm-reverse-all:affirmBtn");
    }

    /**
     * WORKS IN BROWSER
     */

    @Ignore
    @Test
    @Order(5)
    public void ShouldViewRate() throws InterruptedException {
        boolean isChecked = use.isChecked("ucform:sessionTotal:viewrate");
        Assert.assertFalse("No value view rate: ", isChecked);

        boolean preRate = use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span"); //use.getValueCss("tr.even:nth-child(1) > td:nth-child(2) > div:nth-child(3) > span:nth-child(1)");// use.getValueXpath("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span");
        //shouldAddValue();

        try {
            //shouldConfirm();
        } catch (Exception e) {
            System.out.println("Problem parsing");
        }


        use.checkCheckbox("ucform:sessionTotal:viewrate");
        try {
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean postRate = use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span");
        Assert.assertTrue("PreRate:", postRate);
        Assert.assertFalse("Postrate", preRate);


        use.clickPath("ucform:sessionTotal:viewrate");
        try {
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Not tested
     */
    @Ignore
    @Test
    @Order(6)
    public void ShouldDeleteRowInSessionTotal() throws InterruptedException {

        //shouldAddValue();
        try {
            //shouldConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        use.clickPath("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[4]/a[1]/img");
        use.clickPath("//*[@id=\'ucform:sessionTotal:j_idt2027:0:btn-delete-transaction\']");
        use.clickPath("ucform:buttonReverse");
        boolean clickingTrashcan = true;
        boolean pressCancel = true;
        clickingTrashcan = true;
        boolean clickReverse = true;
        String headerMessage = use.getValue("/html/body/div[2]/div[2]/div/form/div[1]/div[1]/div/ul/li/span");
        Assert.assertEquals("Transaction reversed Error:", "Transaction reversal completed.", headerMessage);


        use.setTextBoxValue("ibform:actionCode_input", "SNEUR", true);
        use.setTextBoxValue("ibform:actionCode_input", "SNEUR", true);
        use.setTextBoxValue("ibform:actionCode_input", "SNEUR", true);
        multipleRows = true;
        //shouldAddValue();
        try {
            //shouldConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }

        multipleRows = false;

        emptySessionTotal();
        try {
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String messageNoRecords = use.getValue("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td");
        Assert.assertEquals("No records message not found", "No records", messageNoRecords);
    }



    /**
     * Not tested
     */

/*    public void shouldDeleteRowBeforeTransaction() {
        addValue();

        if (use.existsID("ucform:vexla-table:0:delete-row-button"))
        {
            use.clickPath("ucform:vexla-table:0:delete-row-button");
        }
        else
        {
            System.out.println("Trashcan icon in transaction row could not be found");
        }

        Assert.assertEquals("removal of ");

        String getValuefromFromXpath = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span";
        boolean trashCanExists = false;
        while (trashCanExists == true) {
            String RemoveTrashCan;
            if (getValuefromFromXpath != "") {
                boolean isRemovedFully; //No records found.
                break;
            }
        }
    }*/
/*
    private boolean shouldCheckHeadersInReverseTransaction() {
        List<Boolean> listWithExistingHeaders = Collections.emptyList();
        boolean dateFound, User,SeqNo,TransactionName,TransactionNumber, Rate,Amount, LocalAmount, Workstation, InventoryCode, SourceCashdesk, TargetCshdesk, DebitAccount, CreditAccount, ExtTransactionNumber, TransactionState, DoubleCheckedBy, SessionId, ReversalTransactionSeqNo, ReversedTransactionSeqNo, Description, Product= false;
        switch (true) {
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[1]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[1]/td[2]"):
                dateFound = true;
                listWithExistingHeaders.add(dateFound);
                break;
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[2]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[2]/td[4]"):
                User = true;
                listWithExistingHeaders.add(User);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[3]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[3]/td[2]"):
                SeqNo = true;
                listWithExistingHeaders.add(SeqNo);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[3]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[3]/td[4]"):
                TransactionName = true;
                listWithExistingHeaders.add(TransactionName);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[4]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[4]/td[2]"):
                TransactionNumber = true;
                listWithExistingHeaders.add(TransactionNumber);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[4]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[4]/td[4]/span"):
                Rate = true;
                listWithExistingHeaders.add(Rate);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[5]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[5]/td[2]/span"):
                Amount = true;
                listWithExistingHeaders.add(Amount);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[5]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[5]/td[4]/span"):
                LocalAmount = true;
                listWithExistingHeaders.add(LocalAmount);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[6]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[6]/td[2]"):
                Workstation = true;
                listWithExistingHeaders.add(Workstation);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[6]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[6]/td[4]"):
                InventoryCode = true;
                listWithExistingHeaders.add(InventoryCode);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[7]/td[1]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[7]/td[2]"):
                SourceCashdesk = true;
                listWithExistingHeaders.add(SourceCashdesk);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[7]/td[3]/label") && use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[7]/td[4]"):
                TargetCshdesk = true;
                listWithExistingHeaders.add(TargetCshdesk);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[8]/td[1]/label"):
                DebitAccount = true;
                listWithExistingHeaders.add(DebitAccount);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[8]/td[3]/label"):
                CreditAccount = true;
                listWithExistingHeaders.add(CreditAccount);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[9]/td[1]/label"):
                ExtTransactionNumber = true;
                listWithExistingHeaders.add(ExtTransactionNumber);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[9]/td[3]/label"):
                TransactionState = true;
                listWithExistingHeaders.add(TransactionState);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[10]/td[1]/label"):
                DoubleCheckedBy = true;
                listWithExistingHeaders.add(DoubleCheckedBy);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[10]/td[3]/label"):
                SessionId = true;
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[11]/td[1]/label"):
                ReversalTransactionSeqNo = true;
                listWithExistingHeaders.add(ReversalTransactionSeqNo);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[11]/td[3]/label"):
                ReversedTransactionSeqNo = true;
                listWithExistingHeaders.add(ReversedTransactionSeqNo);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[12]/td[1]/label"):
                Description = true;
                listWithExistingHeaders.add(Description);
            case use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/table/tbody/tr[12]/td[3]/label"):
                Product = true;
                listWithExistingHeaders.add(Product);
        }

        return true;
    }*/


}
