package com.company;
import org.openqa.selenium.WebDriver;


public class identifyCustomer {
    public static String pin;
    public void customer(WebDriver obj) throws InterruptedException{
        functions use = new functions();
        //Clicking identify Customer
        use.clickPath("ibform:identifyCustomer:btn-identify-customer");

        //Setting textbox to PIN
        use.setTextBoxValue("ibform:identifyCustomer:qs-cst-id-number","195811112217", true);
        //Click search
        use.clickPath("ibform:identifyCustomer:qs-search-button");
        //Select customer
        use.clickPath("ibform:identifyCustomer:search-results-dt:0:select-item-button");
        //Select ID document
        use.selectElementInDropdownByValue("ibform:identifyCustomer:identify-cst-id-type:it-doc-select","CustomerIdDocument [idbHasIdCopy=false, documentNumber=195811112217, documentType=Passport, issuingDate=Fri Sep 09 03:00:00 EEST 2011, expirationDate=Fri Sep 27 03:00:00 EEST 2019, idbId=46163, action=NA, current=false]");//Click save
        //Press save
        use.clickPath( "ibform:identifyCustomer:identify-cst-id-type:btn-save-id-type");
        //Press sarch
    }
}
