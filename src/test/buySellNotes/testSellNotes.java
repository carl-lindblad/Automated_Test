package test.buySellNotes;

import org.junit.*;
import com.company.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import java.text.ParseException;
import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class testSellNotes {
    private static testFunctions testing = new testFunctions();
    private static starterClass app = new starterClass();
    private static functions use = new functions();
    private static ArrayList<String> values = new ArrayList<String>();
    private static ArrayList<String> foreignsTextboxes = new ArrayList<String>();
    private static ArrayList<String> localTextboxes = new ArrayList<String>();

    private static ArrayList<String> ratesID = new ArrayList<String>();
    @BeforeClass
    public static void start() {
        app.start();
        testing.separateElements("C:\\DevStuff\\Automated Test\\src\\Config\\sellNotesElements\\sellNotesElements.properties");
        setValues();

    }


    private static void setValues(){
        values.add("5");
        values.add("200");
        values.add("300");
        foreignsTextboxes.add("txtbx_foreign_Currency1");
        foreignsTextboxes.add("txtbx_foreign_Currency2");
        foreignsTextboxes.add("txtbx_foreign_Currency3");
        ratesID.add("txtbx_rate1");
        ratesID.add("txtbx_rate2");
        ratesID.add("txtbx_rate3");
        localTextboxes.add("txtbx_Local_Currency1");
        localTextboxes.add("txtbx_Local_Currency2");
        localTextboxes.add("txtbx_Local_Currency3");
        testing.setActionFieldId("Sneur");
        testing.setLists(foreignsTextboxes, ratesID, values, localTextboxes);
    }

    @Before
    public void goToSneur() throws InterruptedException {
        testing.actionField("Sneur");
    }

    /**
     * Existence of elements
     * TODO
     * - Add all elements
     *
     * @throws NoSuchFieldException
     */
    @Test
    @Order(5)
    public void shouldElementExist() throws NoSuchFieldException {
        Assert.assertTrue("Not all elements could be found: ", testing.ifElementsExists());
        if (!testing.getAllElementsNotFound().isEmpty()) {
            System.out.println("Could not find: ");
            testing.getAllElementsNotFound().forEach((key, value) -> System.out.println("Key: " + key + " || Value: " + value));
        }

    }

    /**
     * Type: Functional logic
     * Name: Switch of currency
     */
    @Test
    @Order(4)
    public void shouldChangeTextboxValue() throws InterruptedException, ParseException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue(testing.isTextBoxValueChanged());
        testing.getAllElementsNotFound().forEach((k, v) -> System.out.println("Could not find " + k + "With value:" + v));
        testing.clearSession("btn_Reverse_all_transaction", "btn_Reverse_all_transaction_confirm");
    }

    /**
     * Type: Functional logic
     * Name: Auto Calculation
     *
     * @throws ParseException
     * @throws InterruptedException
     */
    @Test
    @Order(3)
    public void shouldCalculateCorrect() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue("Adding x local currency should give y foreign currency", testing.isFcToLcCorrect("100", "txtbx_foreign_Currency1", "txtbx_Local_Currency1"));
        testing.removeAllValuesInTransaction("icon_TrashCan_Before_Confirmed");
    }

    /**
     * Type: Functional logic
     * Name: Adding value
     *
     * @throws ParseException
     * @throws InterruptedException
     */
    @Test
    @Order(2)
    public void shouldAddAndRemoveValues() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue("Testing add and remove", testing.isAddedAndRemoved( "icon_TrashCan_Before_Confirmed"));
    }

    /**
     * Type: Click effects / Functional Logic
     * Name: Confirm & Sub-total Exchange
     *
     * @throws ParseException
     * @throws InterruptedException
     */
    @Test
    @Order(1)
    public void shouldConfirmAndControllSessionTotal() throws ParseException, InterruptedException {
        testing.addNewLineIfNeccessary();
        Assert.assertTrue(testing.isSub_total_Correct("hdr_Sub_total_Exchange_Sum"));
        testing.confirmValues("btn_Confirm");
        testing.isSessionTotalCorrect("hdr_Session_Total_LC", "hdr_Session_Total_FC");
        testing.clearSession("btn_Reverse_all_transaction", "btn_Reverse_all_transaction_confirm");
    }

    /**
     * @throws ParseException
     */
    @Ignore
    @Test
    @Order(5)
    public void ShouldViewRate() throws InterruptedException {
        boolean isChecked = use.isChecked("ucform:sessionTotal:viewrate");
        Assert.assertFalse("No value view rate: ", isChecked);

        boolean preRate = use.isXpathVisible("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span"); //use.getValueCss("tr.even:nth-child(1) > td:nth-child(2) > div:nth-child(3) > span:nth-child(1)");// use.getValueXpath("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td[2]/div/span");
        //shouldCalculateCorrect();

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

        //shouldCalculateCorrect();
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
        //shouldCalculateCorrect();
        try {
            //shouldConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //emptySessionTotal();
        try {
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String messageNoRecords = use.getValue("/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr/td");
        Assert.assertEquals("No records message not found", "No records", messageNoRecords);
    }
}