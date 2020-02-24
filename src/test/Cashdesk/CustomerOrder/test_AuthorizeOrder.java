package test.Cashdesk.CustomerOrder;

import com.company.functions;
import com.company.services;
import com.sun.javafx.sg.prism.NGImageView;
import com.sun.scenario.effect.impl.state.AccessHelper;
import org.junit.*;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import test.Cashdesk.CashdeskAdministration.Ref.CashdeskAdministration;
import test.Cashdesk.CashdeskAdministration.test_CloseOpenCashDesk;
import test.Cashdesk.CustomerOrder.Ref.CustomerOrder;

import static com.company.test_settings.AUTHORIZE_ORDER_TEST_SETTING;
import static test.Cashdesk.CustomerOrder.Ref.ID_CreateCustomerOrder.*;
import static com.company.ID_Basic.*;

@FixMethodOrder
public class test_AuthorizeOrder {
    private CustomerOrder given = new CustomerOrder();
    private CustomerOrder when = new CustomerOrder();
    private CustomerOrder then = new CustomerOrder();
    private static services check = new services();
    private static CustomerOrder use = new CustomerOrder();

    private static functions start = new functions();

    @BeforeClass
    public static void controllCashdeskIsOpen() {
        Assume.assumeTrue(AUTHORIZE_ORDER_TEST_SETTING);
        if (CashdeskAdministration.cashdeskIsClosed(true)) {
            test_CloseOpenCashDesk.openCashdesk();
        }
        start.waitingForSiteToLoad(1);

    }

    @Before
    public void preset() {
        check.goToFunctionality("AO");
        if(!check.tableHasValues(By.id(AUTHORIZATION_TABLEBODY))){
            test_CreateOrder run = new test_CreateOrder();
            run.createOrderAED();
            run.registerPersonalCustomer();
            run.createOrder();
            check.goToFunctionality("AO");
        }

    }

    @After
    public void reset() {
        check.goToFunctionality("WS");
    }


    private void pickOrder() {
        given
                .isAtPage(AUTHORIZE_ORDER_TITLE_VALUE);

        when
                .click(By.id(AUTHORIZATION_PICK_ORDER_LINK));
        then
                .isPickPopUpDisplayed();
    }

    @Test
    @Order(1)
    public void cancelAuthorization() {
        //given
                pickOrder();
        when
                .click(By.id(AUTHORIZATION_CANCEL_LINK));
        then
                .hasCancelled();
    }

    private void approveOrder(){
        //Given
         pickOrder();
         when
                 .click(By.id(AUTHORIZATION_APPROVE_BTN));
         then
                 .IsAtConfirmPopup();
     }

     @Test
     @Order(2)
    public void cancelComment(){
        //Given
         approveOrder();
         when
                 .click(By.id(AUTHORIZATION_COMMENT_CANCEL_LINK));
         then
                 .hasCancelled();
     }
    @Test
    @Order(3)
    public void confirmComment() {
        //Given
        approveOrder();
        when
                .sendValues(By.id(AUTHORIZATION_INTERNAL_COMMENT_TEXTFIELD), "This is an internal comment")
                .sendValues(By.id(AUTHORIZATION_BRANCH_COMMENT_TEXTFIELD), "This is a branch comment")
                .click(By.id(AUTHORIZATION_CONFIRM_COMMENT_BTN));
        then
                .authorizationSuccesful();
    }



    }
