package test.IdentifyCustomer;

import com.company.functions;
import org.junit.*;
import org.junit.jupiter.api.Order;
import test.IdentifyCustomer.Ref.identifyCustomer;

import static com.company.test_settings.IDENTIFY_CUSTOMER_TEST_SETTING;

@FixMethodOrder
public class test_identifyCustomer {
    private static identifyCustomer given = new identifyCustomer();
    private static identifyCustomer when = new identifyCustomer();
    private static identifyCustomer then = new identifyCustomer();
    private static functions start = new functions();

    @BeforeClass
    public static void isTestActivated() {
        Assume.assumeTrue(IDENTIFY_CUSTOMER_TEST_SETTING);
    }

    @After
    public void reset() {
        start.goToWelcomePage();
    }

    @Test
    @Order(2)
    public void testingIdentifySwedishUser() {
        given
                .openIdentifyCustomer();
        when
                .searchWithPIN("201401262389")
                .selectCustomer()
                .selectCustomerIdentification();
        then
                .customerSelected();
    }

    @Test
    @Ignore
    @Order(1)
    public void testingUnidentifyCustomer() {
        //Given
                customerIdentified("201401262389");
        when
                .crossClicked()
                .unidentifyClicked();
        then
                .customerUnidentified();
    }

    public static void customerIdentified(String pin) {
        given
                .openIdentifyCustomer();
        when
                .searchWithPIN(pin)
                .selectCustomer()
                .selectCustomerIdentification();
        then
                .customerSelected();
    }
}
