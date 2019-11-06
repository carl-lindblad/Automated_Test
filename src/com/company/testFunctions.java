package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Config.*;


public class testFunctions {
    functions use = new functions();
    PropertiesFile get = new PropertiesFile();
    private HashMap<String, String> allElements = new HashMap<String, String>();
    private HashMap<String, String> allHeaders = new HashMap<String, String>();
    private HashMap<String, String> allCheckboxes = new HashMap<String, String>();
    private HashMap<String, String> allTextboxes = new HashMap<String, String>();
    private HashMap<String, String> allButton = new HashMap<String, String>();
    private HashMap<String, String> allIcons = new HashMap<String, String>();
    private HashMap<String, String> allElementsNotFound = new HashMap<String, String>();
    private static String  actionFieldCommand;
    private ArrayList<String> rates = new ArrayList<String>();
    private ArrayList<String> foreignTextBoxes = new ArrayList<String>();
    private ArrayList<String> localTextBoxes = new ArrayList<String>();

    private ArrayList<String> values = new ArrayList<String>();

    private double totalValueLc = 0;
    private double totalValueFc = 0;
    public void setActionFieldId(String actionFieldCommand){
        this.actionFieldCommand = actionFieldCommand;
    }
    public void setLists(ArrayList<String> foreigntextboxes, ArrayList<String> rates, ArrayList<String> values, ArrayList<String> localTextBoxes){
        this.rates = rates;
        this.foreignTextBoxes = foreigntextboxes;
        this.values = values;
        this.localTextBoxes = localTextBoxes;
    }


    private static String btnConfirmId = "btn_Confirm";


    public double getTotalValueFc() {
        return totalValueFc;
    }

    public HashMap<String, String> getAllHeaders() {
        return allHeaders;
    }

    public HashMap<String, String> getAllButton() {
        return allButton;
    }

    public HashMap<String, String> getAllCheckboxes() {
        return allCheckboxes;
    }

    public HashMap<String, String> getAllTextboxes() {
        return allTextboxes;
    }

    public HashMap<String, String> getAllIcons() {
        return allIcons;
    }

    public HashMap<String, String> getAllElements() {
        return allElements;
    }

    public HashMap<String, String> getAllElementsNotFound() {
        return allElementsNotFound;
    }

    public double getTotalValueLc() {
        return this.totalValueLc;
    }


    public void setAllElements(HashMap<String, String> allElements) {
        this.allElements = allElements;
    }

    public void setAllHeaders(HashMap<String, String> allHeaders) {
        this.allHeaders = allHeaders;
    }

    public void setAllCheckboxes(HashMap<String, String> allCheckboxes) {
        this.allCheckboxes = allCheckboxes;
    }

    public void setTotalValueLc(double totalValueLc) {
        this.totalValueLc += totalValueLc;
    }

    public void setTotalValueFc(double totalValueFc) {
        this.totalValueFc += totalValueFc;
    }

    public void setAllTextboxes(HashMap<String, String> allTextboxes) {
        this.allTextboxes = allTextboxes;
    }

    public void setAllButton(HashMap<String, String> allButton) {
        this.allButton = allButton;
    }

    public void setAllIcons(HashMap<String, String> allIcons) {
        this.allIcons = allIcons;
    }

    public void setElementsNotfound(HashMap<String, String> elementsNotfound) {
        this.allElementsNotFound = elementsNotfound;
    }

    public void separateElements(String fileInputPath) {
        this.allElements = get.readPropertiesFile(fileInputPath);
        setAllElements(this.allElements);
        for (Map.Entry<String, String> key : allElements.entrySet()) {
            if (key.getKey().contains("hdr_")) {
                this.allHeaders.put(key.getKey(), key.getValue());
                setAllHeaders(allHeaders);
            }
            if (key.getKey().contains("txtbx_")) {
                this.allTextboxes.put(key.getKey(), key.getValue());
                setAllTextboxes(allTextboxes);

            }
            if (key.getKey().contains("chkbx_")) {
                this.allCheckboxes.put(key.getKey(), key.getValue());
                setAllCheckboxes(allCheckboxes);

            }
            if (key.getKey().contains("icon_")) {
                this.allIcons.put(key.getKey(), key.getValue());
                setAllIcons(allIcons);
            }
            if (key.getKey().contains("btn_")) {
                this.allButton.put(key.getKey(), key.getValue());
                setAllButton(allButton);
            }
        }
    }

    public boolean ifElementsExists() {

        boolean exists = false;
        for (Map.Entry<String, String> identifier : allElements.entrySet()) {
            if (identifier.getValue().contains("/")) {
                exists = use.isXpathVisible(identifier.getValue());
                if (!exists) {
                    this.allElementsNotFound.put(identifier.getKey(), identifier.getValue());
                    setElementsNotfound(allElementsNotFound);
                    exists = true;
                }
            } else {
                exists = use.isExisting(identifier.getValue());
                if (!exists) {
                    this.allElementsNotFound.put(identifier.getKey(), identifier.getValue());
                    setElementsNotfound(allElementsNotFound);
                    exists = true;

                }
            }
        }
        return exists;

    }

    public boolean isTextBoxValueChanged() throws InterruptedException, ParseException {
        boolean isChanged = false;
        addValuesToTextboxes();
        for (Map.Entry<String, String> identifier : getAllTextboxes().entrySet()) {
            String preValue = use.getValue(identifier.getValue());
            use.setTextBoxValue(identifier.getValue(), values.get(0), true);
            String postValue = use.getValue(identifier.getValue());
            if (preValue != postValue) {
                isChanged = true;
            } else {
                this.allElementsNotFound.put(identifier.getKey(), identifier.getValue());
                setElementsNotfound(allElementsNotFound);
                return false;
            }
        }
        if (isChanged) {
            return true;
        } else {
            return false;

        }
    }

    public void actionField(String command) throws InterruptedException {
        String key = getAllTextboxes().get("txtbx_Action_box");
        use.setTextBoxValue(key, command, true);
    }

    public boolean isFcToLcCorrect(String value, String foreignCurrencyID, String localCurrencyID) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        use.setTextBoxValue(textboxes.get(foreignCurrencyID), value, true);
        String foreignCurrency = use.getValue(textboxes.get(foreignCurrencyID));
        String localCurrency = use.getValue(textboxes.get(localCurrencyID));
        String rate = use.getValue(textboxes.get("txtbx_rate1"));

        return isCalculatedLcCorrect(use.toDouble(rate), use.toDouble(foreignCurrency), use.toDouble(localCurrency));

    }

    public boolean isCalculatedLcCorrect(double rate, double foreignCurrency, double localCurrency){

        double r = use.roundToTwoDecimals(rate);
        double fc = use.roundToTwoDecimals(foreignCurrency);
        double lc = use.roundToTwoDecimals(localCurrency);
        double presumedLC = use.roundToTwoDecimals(fc * r);

        System.out.println("rate = "+rate);
        System.out.println("foreign = "+fc);
        System.out.println("local = "+lc);
        System.out.println("presumed lc = "+presumedLC);
        double diff = presumedLC - lc;
        System.out.println(diff);
        if(diff <= 1 || diff >= -1)
        {

            setTotalValueLc(presumedLC);
            return  true;
        }
        else {
            return false;
        }

    }

    public void addValuesToTextboxes() throws InterruptedException, ParseException {
        HashMap<String, String> textboxes = getAllTextboxes();
        int i = 0;
        for (String value: values) {
            use.setTextBoxValue(textboxes.get(foreignTextBoxes.get(i)), values.get(i), true);
            double theRate= use.toDouble(use.getValue(textboxes.get(rates.get(i))));
            double lc= use.toDouble(use.getValue(textboxes.get(localTextBoxes.get(i))));
            double fc = use.toDouble(values.get(i));
            setTotalValueFc(fc);
            isCalculatedLcCorrect(theRate, fc, lc);

            if(i != values.size()-1) {
                actionField(actionFieldCommand);
            }
            i++;
        }
    }

    public void confirmValues(String btnID) {
        HashMap<String, String> allbuttons = getAllButton();
        use.clickPath(allbuttons.get(btnID));
    }

    public void removeAllValuesInTransaction(String removeBtnId) {
        while (use.isExisting(getAllIcons().get(removeBtnId))) {
            removeValueInTransaction(removeBtnId);
            try {

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void removeValueInTransaction(String removeBtnId) {

        if (use.isExisting(getAllIcons().get(removeBtnId))) {
            use.clickPath(getAllIcons().get(removeBtnId));
        }
    }

    public boolean isAddedAndRemoved(String removeBtnId) throws InterruptedException, ParseException {
        addValuesToTextboxes();
        removeAllValuesInTransaction(removeBtnId);
        return !use.isExisting(getAllIcons().get(removeBtnId));
    }

    public void isSessionTotalCorrect(String sessionTotalLc, String sessionTotalFc) throws ParseException, InterruptedException {
        String totalLc = use.getValue(getAllHeaders().get(sessionTotalLc));
        String totalFc = use.getValue(getAllHeaders().get(sessionTotalFc));


        double fc = use.toDouble(totalFc);
        double lc = use.toDouble(totalLc);


    }


    //Need id cant reach subtotal with xpath.
    public boolean isSub_total_Correct(String subTotalExchangeSumId) throws ParseException, InterruptedException {
        addValuesToTextboxes();
        String tempSum = use.getValue(getAllHeaders().get(subTotalExchangeSumId));
        double sum = use.toDouble(tempSum);
        System.out.println("total lc = "+getTotalValueLc());
        System.out.println("total fc = "+getTotalValueFc());
        System.out.println("total sum = "+sum);
        double diff = sum-getTotalValueLc();
        if (diff < 1 ) {
            return true;
        } else {
            return false;
        }

    }

    public void clearSession(String reverseBtnId, String reverseBtnConfirmId) {
        use.clickPath(getAllButton().get(reverseBtnId));
        use.clickPath(getAllButton().get(reverseBtnConfirmId));
    }

    public void addNewLineIfNeccessary() throws InterruptedException {
        if (!use.isExisting(getAllButton().get(btnConfirmId))) {
            actionField("Sneur");
        }
    }
}