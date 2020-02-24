package test.Customer.MaintainCustomer.Ref;

import com.company.functions;
import com.company.services;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import test.IdentifyCustomer.test_identifyCustomer;

import java.util.ArrayList;

import static com.company.ID_Basic.ACTIONBOX;
import static com.company.ID_Basic.SUCCESS_MSG;
import static test.Customer.MaintainCustomer.Ref.ID_maintain_customer.*;
import static test.Customer.MaintainCustomer.Ref.LISTCONTENT_MaintainCustomer.getArrayList;

public class maintainCustomer extends services {
    private functions use = new functions();
    private services start = new services();
    private static String code = "";
    private static String expectedMessage = "Customer information was updated successfully";

    public maintainCustomer customerIdentified(String pin) {
        test_identifyCustomer.customerIdentified(pin);
        return this;
    }

    public maintainCustomer setCountryCode(String countryCode) {

        try {
            use.findAndWait(By.id(COUNTRY_CODE_TEXTBOX)).sendKeys(countryCode);
        } catch (Exception e) {
            use.waitingForSiteToLoad(1);
            use.findAndWait(By.id(COUNTRY_CODE_TEXTBOX)).sendKeys(countryCode);
        }
        use.waitingForSiteToLoad(1.5);
        use.findAndWait(By.id(COUNTRY_CODE_TEXTBOX)).sendKeys(Keys.ARROW_DOWN, Keys.TAB);
        use.setPreviousValue(countryCode);
        this.code = countryCode;
        return this;
    }


    public boolean saveSuccessful() {
        String value = use.getAttribute(By.id(COUNTRY_CODE_TEXTBOX), "value");
        if (value.equals("")) {
            System.out.println(value +" "+ code + "was not found");
        }
        else {
            System.out.println(value +" "+code + " successfully added");
        }

        return use.findAndWait(By.id(SUCCESS_MSG)).getText().equals("Customer information was updated successfully");


    }

    public ArrayList<String> getCountryCodes() {
        return getArrayList();
    }

    public maintainCustomer setMobileNumber(String number, boolean isNeeded) {
        if (!isNeeded) {
            return this;
        }
        use.findAndWait(By.id(MOBILE_NUMBER_TEXTBOX)).sendKeys(number.toString());
        use.setPreviousValue(number.toString());
        return this;
    }

    public maintainCustomer save() {
        System.out.println("Running method: "+ new Throwable().getStackTrace()[0].getMethodName());
        start.click(By.id(SAVE_CUSTOMER_BTN));
        return this;

    }
    public maintainCustomer setAdress(String adress){
        System.out.println("Running method: "+ new Throwable().getStackTrace()[0].getMethodName());
        use.findAndWait(By.id(ADRESS1_TEXTBOX)).sendKeys(adress);
        return this;
    }
    public maintainCustomer setZip(String zip){
        System.out.println("Running method: "+ new Throwable().getStackTrace()[0].getMethodName());
        use.findAndWait(By.id(ZIP__TEXTBOX)).sendKeys(zip);
        return this;
    }
    public maintainCustomer setCity(String city){
        System.out.println("Running method: "+ new Throwable().getStackTrace()[0].getMethodName());
        use.findAndWait(By.id(CITY__TEXTBOX)).sendKeys(city);
        return this;
    }
    public maintainCustomer setRateCategory(String rateCategory){
        use.selectElementByValue(By.id(RATE_CATEGORY_DROPDOWN), rateCategory);
        return this;
    }
    public maintainCustomer setEmployee(boolean checkEmployee){
        if(checkEmployee){
            start.click(By.id(EMPLOYEE_CHECKBOX));
        }
        return this;
    }
    public maintainCustomer setRemoteClient(boolean remoteClient){
        if(remoteClient){
            start.click(By.id(REMOTE_CLIENT_CHECKBOX));
        }
        return this;
    }
    public maintainCustomer setHomeNumber(String homeNumber){
        use.findAndWait(By.id(HOME_NUMBER_TEXTBOX)).sendKeys(homeNumber);
        return this;
    }
    public maintainCustomer setWorkNumber(String workNumber){
        use.findAndWait(By.id(WORK_NUMBER_TEXTBOX)).sendKeys(workNumber);
        return this;
    }
    public maintainCustomer setEmail(String email){
        use.findAndWait(By.id(EMAIL_TEXTBOX)).sendKeys(email);
        return this;
    }
    public maintainCustomer setTaxCode(String taxCode){
        use.selectElementByValue(By.id(TAX_CODE_DROPDOWN), taxCode);
        return this;
    }
    public maintainCustomer setSectorCode(String sectorCode){
        use.selectElementByValue(By.id(SECTOR_CODE_DROPDOWN), sectorCode);
        return this;
    }
    public maintainCustomer setPlaceOfBirth(String placeOfBirth){
        use.findAndWait(By.id(PLACE_OF_BIRTH_TEXTBOX)).sendKeys(placeOfBirth);

        return this;
    }
    public maintainCustomer setCountryOfBirth(String countryOfBirth){
        use.selectElementByValue(By.id(COUNTRY_OF_BIRTH_DROPDOWN),countryOfBirth.toUpperCase());
        return this;
    }
    public void customerSuccessfullySaved() {
        use.waitingForSiteToLoad(1);
        String actualMessage = use.getText(By.xpath(MESSAGE_TEXT_XPATH));
        Assert.assertEquals("Testing all inputs", expectedMessage.trim(), actualMessage.trim());
    }

    public maintainCustomer setCountry(String country) {
        if(!country.equals("(None)")){
            use.selectElementByValue(By.id(COUNTRY_DROPDOWN), country.toUpperCase());
            this.expectedMessage ="Customer information was updated successfully";

        }
        else {
            use.selectElementByValue(By.id(COUNTRY_DROPDOWN), country);
            this.expectedMessage ="Country is required to save customer address";
            if(!use.getAttribute(By.id(ADRESS1_TEXTBOX), "value").isEmpty()){
                this.expectedMessage ="Country is required to save customer address";
            }
        }

        return this;
    }

    public ArrayList<String> getCountries() {
        return (ArrayList<String>) use.getAllElementsFromDropdown(By.id(COUNTRY_DROPDOWN));
    }
}
