package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import Config.*;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;


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
    private double totalValueLc;
    private double totalValueFc;

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
        return totalValueLc;
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
                    //throw new NoSuchFieldException("Identifier could not be found: " + identifier.getKey()+"||"+identifier.getValue());
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

    public boolean isTextBoxValueChanged(ArrayList<String> values) throws InterruptedException, ParseException {
        boolean isChanged = false;
        addMultipleValue(values);
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

    public void goToSneur() throws InterruptedException {
        String key = getAllTextboxes().get("txtbx_Action_box");
        use.setTextBoxValue(key, "SNEUR", true);
    }

    public boolean isFcToLcCorrect(String value) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        String foreignCurrency = use.getValue(textboxes.get("txtbx_foreign_Currency"));
        String localCurrency = use.getValue(textboxes.get("txtbx_Local_Currency"));
        String rate = use.getValue(textboxes.get("txtbx_rate"));
        double r = Math.round(use.toDouble(rate));
        double fc = use.toDouble(foreignCurrency);
        double lc = use.toDouble(localCurrency);
        double presumedLC = fc * r;
        setTotalValueLc(lc);
        if (lc == presumedLC) {
            return true;
        } else {
            return false;
        }
    }

    public void addValue(String value) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        //isTextBoxValueChanged(value);
        use.setTextBoxValue(textboxes.get("txtbx_foreign_Currency1"), value, true);
        isFcToLcCorrect(value);

    }

    public void addMultipleValue(ArrayList<String> listOfValues) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        String value1 = listOfValues.get(0);
        String value2 = listOfValues.get(1);
        String value3 = listOfValues.get(2);
        use.setTextBoxValue(textboxes.get("txtbx_foreign_Currency1"), value1, true);
        goToSneur();
        use.setTextBoxValue(textboxes.get("txtbx_foreign_Currency2"), value2, true);
        goToSneur();
        use.setTextBoxValue(textboxes.get("txtbx_foreign_Currency3"), value3, true);
    }

    public void confirmValues() {
        HashMap<String, String> allbuttons = getAllButton();
        use.clickPath(allbuttons.get("btn_Confirm"));
    }

    public void removeAllValuesInTransaction() {
        while (use.isExisting(getAllIcons().get("icon_TrashCan_Before_Confirmed"))) {
            removeValueInTransaction();
            try {

                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void removeValueInTransaction() {

        if (use.isExisting(getAllIcons().get("icon_TrashCan_Before_Confirmed"))) {
            use.clickPath(getAllIcons().get("icon_TrashCan_Before_Confirmed"));
        }
    }

    public boolean isAddedAndRemoved(ArrayList<String> listOfValues) throws ParseException, InterruptedException {
        addMultipleValue(listOfValues);
        removeAllValuesInTransaction();
        return !use.isExisting(getAllIcons().get("icon_TrashCan_Before_Confirmed"));
    }

    public boolean isSessionTotalCorrect() throws ParseException, InterruptedException {
        String totalLc = use.getValue(getAllHeaders().get("hdr_Session_Total_LC"));
        String totalFc = use.getValue(getAllHeaders().get("hdr_Session_Total_Fc"));


        double fc = use.toDouble(totalFc);
        double lc = use.toDouble(totalLc);
        setTotalValueLc(lc);
        setTotalValueFc(fc);

        System.out.println(fc);
        System.out.println(lc);
        System.out.println(getTotalValueLc());
        System.out.println(getTotalValueFc());

        return getTotalValueLc() == lc && getTotalValueFc() == fc;
    }


    //Need id cant reach subtotal with xpath.
    public boolean isSub_total_Correct() throws ParseException, InterruptedException {
        String tempSum = use.getValue(getAllHeaders().get("hdr_Sub_total_Exchange_Sum"));
        double sum = use.toDouble(tempSum);
        if (sum == getTotalValueLc()) {
            return true;
        } else {
            return false;
        }

    }
    public void clearSession(){
        System.out.println(getAllButton().get("btn_Reverse_all_transaction"));
        System.out.println(getAllButton().get("btn_Reverse_all_transaction_confirm"));
        use.clickPath(getAllButton().get("btn_Reverse_all_transaction"));
        use.clickPath(getAllButton().get("btn_Reverse_all_transaction_confirm"));
    }
    public void addNewLineIfNeccessary() throws InterruptedException {
        if(!use.isExisting(getAllButton().get("btn_Confirm"))){
            goToSneur();
        }
    }
}