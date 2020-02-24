package test.IdentifyCustomer.Ref;

import com.company.functions;

import java.util.ArrayList;

public class ID_identify_Customer {
    public static Object ID_identify_Customer;
    private functions use = new functions();

    //FUNCTIONALITIES
    public ArrayList getElements() {
        ArrayList<String> elements = new ArrayList<>();
        elements.add(IDENTIFY_CUSTOMER_LINK);
        elements.add(IDENTIFY_CUSTOMER_BY_PIN_BOX);
        elements.add(SEARCH_ON_PIN_BTN);
        elements.add(SELECT_CUSTOMER_ARROW);
        elements.add(BANKID_SEARCH_BTN);
        elements.add(CLEAR_SEARCH_ON_NAME_DOB);
        elements.add(CLEAR_SEARCH_PIN);
        elements.add(COUNTRY_OF_ISSUE_DROPDOWN);
        elements.add(ID_TYPE_DROPDOWN);
        elements.add(LAST_NAME_COMPANY_NAME_HDR);
        elements.add(LAST_NAME_TEXTBOX);
        elements.add(FIRST_NAME_TEXTBOX);
        elements.add(DOB_DATEPICKER);
        elements.add(SEX_DROPDOWN);
        elements.add(SEARCH_ON_NAME_DOB);
        elements.add(COUNTRY_OF_ISSUE_HDR);
        elements.add(FIRST_NAME_HDR);
        elements.add(DOB_HDR);
        elements.add(SEX_HDR);
        elements.add(ADRESS_HDR);
        elements.add(ID_HDR);
        elements.add(CANCEL_LINK);
        elements.add(SELECT_CUSTOMER_ARROW);


        return elements;
    }

    //ELEMENTS
    public static final String IDENTIFY_CUSTOMER_LINK = "ibform:identifyCustomer:btn-identify-customer";
    public static final String IDENTIFY_CUSTOMER_BY_PIN_BOX = "ibform:identifyCustomer:qs-cst-id-number";
    public static final String SEARCH_ON_PIN_BTN = "ibform:customerIdentified:qs-search-button";
    public static final String SELECT_CUSTOMER_ARROW = "ibform:identifyCustomer:search-results-dt:0:select-item-button";
    public static final String BANKID_SEARCH_BTN = "ibform:customerIdentified:j_idt617";
    public static final String CLEAR_SEARCH_PIN = "ibform:customerIdentified:btn-qs-clear-search";
    public static final String COUNTRY_OF_ISSUE_DROPDOWN = "ibform:customerIdentified:as-cst-nationality";
    public static final String ID_TYPE_DROPDOWN = "ibform:customerIdentified:as-cst-idType";
    public static final String LAST_NAME_TEXTBOX = "ibform:customerIdentified:as-cst-lastname";
    public static final String FIRST_NAME_TEXTBOX = "ibform:customerIdentified:as-cst-firstname";
    public static final String DOB_DATEPICKER = "ibform:customerIdentified:as-cst-dob";
    public static final String SEX_DROPDOWN = "ibform:customerIdentified:as-cst-sex";
    public static final String SEARCH_ON_NAME_DOB = "ibform:customerIdentified:as-search-button";
    public static final String CLEAR_SEARCH_ON_NAME_DOB = "ibform:customerIdentified:btn-as-cst-clear-search";
    public static final String COUNTRY_OF_ISSUE_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[2]/span";
    public static final String LAST_NAME_COMPANY_NAME_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[3]/span[1]";
    public static final String FIRST_NAME_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[4]/span[1]";
    public static final String DOB_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[5]/span[1]";
    public static final String SEX_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[6]/span";
    public static final String ADRESS_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[7]/span";
    public static final String ID_HDR = "/html/body/div[2]/div[1]/span/form[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div/span[3]/span[5]/fieldset/div/div/div[1]/table/thead/tr/th[8]/span";
    public static final String CANCEL_LINK = "ibform:customerIdentified:cst-search-cancel";

    //FUNCTIONALITIES
    public static final String SELECT_DOCUMENT_DROPDOWN = "ibform:identifyCustomer:identify-cst-id-type:it-doc-select";
    public static final String SAVE_CUSTOMER_IDENTIFICATION_BTN = "ibform:identifyCustomer:identify-cst-id-type:btn-save-id-type";
    public static final String IDENTIFIED_CUSTOMER_LINK = "ibform:btn-customer-name";
    public static final String UNIDENTIFY_CUSTOMER_IKON = "ibform:remove-customer-button";
    public static final String UNIDENTIFY_CUSTOMER_LINK = "ibform:mi-close-session";
    public static final  String CUSTOMER_SESSION_END_SUCCESS_MSG ="/html/body/div[2]/div[2]/div/form/div[1]/div[1]/div/ul/li/span";


    //VALUES
    public static final String SWEDISH_PIN_VALUE = "201311112393";
    public static final String FIRST_NAME_VALUE = "Zatan";
    public static final String LAST_NAME_VALUE = "Ebrahomhanvilloich";
    public static final String DOB_VALUE = " 2013-11-11";
}
