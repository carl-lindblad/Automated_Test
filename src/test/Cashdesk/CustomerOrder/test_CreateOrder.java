package test.Cashdesk.CustomerOrder;

import com.company.services;
import org.junit.*;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import test.Cashdesk.CashdeskAdministration.Ref.CashdeskAdministration;
import test.Cashdesk.CashdeskAdministration.test_CloseOpenCashDesk;
import test.Cashdesk.CustomerOrder.Ref.CustomerOrder;

import static com.company.test_settings.CUSTOMER_ORDER_TEST_SETTING;
import static test.Cashdesk.CustomerOrder.Ref.ID_CreateCustomerOrder.*;


@FixMethodOrder
public class test_CreateOrder {
    private CustomerOrder given = new CustomerOrder();
    private CustomerOrder when = new CustomerOrder();
    private CustomerOrder then = new CustomerOrder();
    private CashdeskAdministration check = new CashdeskAdministration();
    private test_CloseOpenCashDesk use = new test_CloseOpenCashDesk();

    @BeforeClass
    public static void isTestActivated() {
        Assume.assumeTrue(CUSTOMER_ORDER_TEST_SETTING);

    }

    @Before
    public void isCashdeskOpen() {
        if (CashdeskAdministration.cashdeskIsClosed(true)) {
            test_CloseOpenCashDesk.openCashdesk();
        }
    }

    @Test
    @Order(1)
    public void createOrderAED() {
        given
                .goToFunctionality("CO");
        when
                .selectByValue(By.id(PRODUCT_TYPE_DROPDOWN), "Notes")
                .selectByValue(By.id(CURRENCY_DROPDOWN), "EUR")
                .sendValues(By.id(CURRENCY_AMOUNT_TEXTFIELD), "10,00")
                .click(By.id(SAVER_ORDER_BTN))
                .click(By.id(NEXT_BTN_CREATE_ORDER));
        then
                .isAtRegisterCustomer();
    }

    @Test
    @Order(2)
    public void registerPersonalCustomer() {
        given
                .isAtPage("Create Order(CO)");
        when
                .selectByIndex(By.id(PERSONAL_OR_COMPANY_DROPDOWN), 0)
                .sendValues(By.id(LASTNAME_TEXTFIELD), "Test")
                .sendValues(By.id(FIRSTNAME_TEXTFIELD), "Testsson")
                .sendValues(By.id(EMAIL_TEXTFIELD), "carl@test.lr")
                .sendValues(By.id(MOBILE_NUMBER_TEXTFIELD), "01234658")
                .sendValues(By.id(HOME_NUMBER_TEXTFIELD), "0987654321")
                .sendValues(By.id(WORK_NUMBER_TEXTFIELD), "0123456789")
                .click(By.id(NEXT_BTN_REGISTER_CUSTOMER));
        then
                .isAtCreateOrder();
    }

    @Test
    @Order(3)
    public void createOrder() {
        given
                .isAtPage("Create Order(CO)");
        when
                .selectByIndex(By.id(BRANCH_DROPDOWN), 4)
                .selectByIndex(By.id(ORGIN_DROPDOWN), 1)
                .selectByIndex(By.id(PURPOSE_DROPDOWN), 1)
                .sendValues(By.id(FURTHER_DESCRIPTION_TEXTBOX), "Further description of origin/purpose")
                .sendValues(By.id(ATTACHMENT_DESCRIPTION_TEXTBOX), "Description of attachment for verification")
                .click(By.id(CREATE_ORDER_BTN));
        then
                .orderIsCreated();
    }
}
