package com.company;
import Config.PropertiesFile;
import org.openqa.selenium.WebDriver;


public class identifyCustomer {
    public static String pin;
    public void customer(WebDriver obj){
        functions use = new functions();
        //Clicking identify Customer
        use.click(obj,"ibform:identifyCustomer:btn-identify-customer");

        //Setting textbox to PIN
        use.setTextBoxValue(obj,"ibform:identifyCustomer:qs-cst-id-number","195811112217");
        //Click search
        use.click(obj,"ibform:identifyCustomer:qs-search-button");
        //Select customer
        use.click(obj,"ibform:identifyCustomer:search-results-dt:0:select-item-button");
        //Select ID document
        use.selectIDInDropdownByValue(obj,"ibform:identifyCustomer:identify-cst-id-type:it-doc-select","CustomerIdDocument [idbHasIdCopy=false, documentNumber=195811112217, documentType=Passport, issuingDate=Fri Sep 09 03:00:00 EEST 2011, expirationDate=Fri Sep 27 03:00:00 EEST 2019, idbId=46163, action=NA, current=false]");//Click save
        //Press save
        use.click(obj, "ibform:identifyCustomer:identify-cst-id-type:btn-save-id-type");
        //Press sarch
    }
}
