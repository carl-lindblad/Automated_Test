package test.Cashdesk.Exchange.SellNotes.Ref;

import com.company.functions;

import java.util.ArrayList;

public class ID_SellNotes {

    //PAGE TITLE
    public static final String SELL_NOTES_ID = "ucform:contentTitle";
    public static final String SELL_NOTES_TEXT = "Sell Notes/Coins(S)";


    //FUNCTIONALITIES
    public static final String RATETYPE_BOX = "ucform:vexla-table:0:ratetype";
    public static final String CURRENCY_VALUE = "10";
    public static final String CONFIRM_TRANSACTION = "ucform:execute-button";
    public static final String TAKE_MONEY_FROM_CDPOOL_BTN = "ucform:take-button";
    public static final String PAYMENT_BTN = "ucform:sessionTotal:paymentButton";
    public static final String PAYMENT_OK_BTN = "ucform:receivedOkButton";
    public static final String SESSION_END_BTN = "ucform:sessionTotal:end-button";
    public static final String FOREIGN_CURRENCY_BOX = "ucform:vexla-table:0:foreign";
    public static final String PAYMENT_CASH_BTN = "ucform:sessionTotal:cashPayment";
    public static final String CURRENCY_TYPE_TEXTFIELD = "ucform:vexla-table:0:foreignCurCode_input";

    //HEADERS IN LEFT TRANSACTIONS
    public static final String CURRENCY_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[2]/span";
    public static final String RATE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[3]/span";
    public static final String INVERTED_RATE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[4]/span";
    public static final String FOREIGN_CURRENCY_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[5]/span";
    public static final String LEFT_LOCAL_CURRENCY_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[6]/span";
    public static final String FEE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[7]/span";
    public static final String PROFIT_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/thead/tr/th[8]/span";
    public static final String TRASHCAN_HDR = "ucform:vexla-table:0:delete-row-button";
    public static final String MANDATORY_ASTERIX = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/table/tbody/tr/td[5]/span";

    //STOCK TABLE
    public static final String GAV_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[3]/div[1]/div/table/thead/tr/th[1]/span";
    public static final String BALANCE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[3]/div[1]/div/table/thead/tr/th[2]/span/div";
    public static final String STOCK_TABLE_CONTENT = "//*[@id=\"ucform:j_idt2465:balance-table_data\"]";
    public static final String RATE_CHANGE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[1]/div[3]/div[4]/div/table/thead/tr/th[1]/span";
    public static final String RATE_CHANGE_CONTENT = "//*[@id=\"ucform:j_idt2505_data\"]";

    //RIGHT TRANSACTION
    public static final String SESSION_TOTAL_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/thead/tr/th[1]/span";
    public static final String RIGHT_LOCAL_CURRENCY_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/table/thead/tr/th[3]/span";
    public static final String IN_TOTAL = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/div[3]";
    public static final String ADJUSTMENT_FEE_PROFIT_PANEL = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[1]/table[1]";

    //MESSAGE FIELD
    public static final String MESSAGE_HDR = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/div/div[1]";
    public static final String MESSAGE_FIELD = "/html/body/div[2]/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/div/div[2]";

}
