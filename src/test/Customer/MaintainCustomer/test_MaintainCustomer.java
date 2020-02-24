package test.Customer.MaintainCustomer;

import com.company.functions;
import org.junit.*;
import org.openqa.selenium.By;
import test.Customer.MaintainCustomer.Ref.maintainCustomer;

import java.util.ArrayList;

import static com.company.test_settings.MAINTAIN_CUSTOMER_TEST_SETTING;
import static test.Customer.MaintainCustomer.Ref.ID_maintain_customer.*;
import static test.Customer.MaintainCustomer.Ref.VALUES_swedish_user.*;

public class test_MaintainCustomer {
    private maintainCustomer given = new maintainCustomer();
    private maintainCustomer when = new maintainCustomer();
    private maintainCustomer then = new maintainCustomer();
    private maintainCustomer start = new maintainCustomer();
    private static functions check = new functions();
    private ArrayList<String> countryCodes = start.getCountryCodes();

    @After
    public void reset() {
        check.goToWelcomePage();
    }

    @BeforeClass
    public static void isTestActivated() {
        Assume.assumeTrue(MAINTAIN_CUSTOMER_TEST_SETTING);
    }

    @Test
    @Ignore
    public void testCountryCodes() {
        given
                .customerIdentified(PIN_VALUE)
                .goToFunctionality("MC");
        for (String countryCode : countryCodes
        ) {
            when
                    .sendValues(By.id(COUNTRY_CODE_TEXTBOX), countryCode)
                    .sendValues(By.id(MOBILE_NUMBER_TEXTBOX), MOBILE_NUMBER_VALUE)
                    .click(By.id(SAVE_CUSTOMER_BTN));
            then
                    .saveSuccessful();
        }
    }

    @Test
    @Ignore
    public void testAllInputsInCustomerInformation() {
        given
                .customerIdentified(PIN_VALUE)
                .goToFunctionality("MC");

        when
                .selectByValue(By.id(COUNTRY_DROPDOWN), COUNTRY_VALUE)
                .sendValues(By.id(ADRESS1_TEXTBOX), ADRESS_VALUE)
                .sendValues(By.id(ADRESS2_TEXTBOX), ADRESS_VALUE)
                .sendValues(By.id(ZIP__TEXTBOX), ZIP_VALUE)
                .sendValues(By.id(CITY__TEXTBOX), CITY_VALUE)
                .selectByValue(By.id(RATE_CATEGORY_DROPDOWN), RATE_CATEGORY_VALUE)
                .sendValues(By.id(COUNTRY_CODE_TEXTBOX), COUNTRY_CODE_VALUE)
                .sendValues(By.id(MOBILE_NUMBER_TEXTBOX), MOBILE_NUMBER_VALUE)
                .sendValues(By.id(HOME_NUMBER_TEXTBOX), HOME_NUMBER_VALUE)
                .sendValues(By.id(HOME_NUMBER_TEXTBOX), HOME_NUMBER_VALUE)
                .sendValues(By.id(WORK_NUMBER_TEXTBOX), WORK_NUMBER_VALUE)
                .sendValues(By.id(EMAIL_TEXTBOX), EMAIL_VALUE)
                .selectByIndex(By.id(TAX_CODE_DROPDOWN), TAX_CODE_INDEX)
                .selectByValue(By.id(SECTOR_CODE_DROPDOWN), SECTOR_CODE_INDEX_VALUE)
                .sendValues(By.id(PLACE_OF_BIRTH_TEXTBOX), PLACE_OF_BIRTH_VALUE)
                .selectByValue(By.id(COUNTRY_OF_BIRTH_DROPDOWN), COUNTRY_OF_BIRTH_VALUE)
                .click(By.id(SAVE_CUSTOMER_BTN));
        then
                .customerSuccessfullySaved();
    }

    @Test
    public void testAllCountryOptions() {
        given
                .customerIdentified(PIN_VALUE)
                .goToFunctionality("MC");
        for (String country : start.getCountries()
        ) {
                when

                        .selectByValue(By.id(COUNTRY_DROPDOWN), country)


                        .click(By.id(SAVE_CUSTOMER_BTN));

                then
                        .customerSuccessfullySaved();
            }
    }
}
