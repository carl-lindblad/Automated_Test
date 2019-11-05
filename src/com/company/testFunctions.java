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
    private double totalValueLc;
    private double totalValueFc;
    private static String actionFieldId = "txtbx_Action_box";
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
        System.out.println(getAllElements().size());
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

    public boolean isTextBoxValueChanged(ArrayList<String> values, ArrayList<String> checkboxesID) throws InterruptedException, ParseException {
        boolean isChanged = false;
        addValuesToTextboxes(values, checkboxesID);
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
        String key = getAllTextboxes().get(actionFieldId);
        use.setTextBoxValue(key, "SNEUR", true);
    }

    public boolean isFcToLcCorrect(String value, String foreignCurrencyID, String localCurrencyID) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        use.setTextBoxValue(textboxes.get(foreignCurrencyID), value ,true);
        String foreignCurrency = use.getValue(textboxes.get(foreignCurrencyID));
        String localCurrency = use.getValue(textboxes.get(localCurrencyID));
        String rate = use.getValue(textboxes.get("txtbx_rate"));

        double r = (double) Math.round(use.toDouble(rate)*100)/100;
        double fc = Math.round(use.toDouble(foreignCurrency));
        double lc = Math.round(use.toDouble(localCurrency));
        double presumedLC = fc * r;
        setTotalValueLc(lc);

        System.out.println(r);
        System.out.println(fc);
        System.out.println(lc);
        System.out.println(presumedLC);

        if (lc == presumedLC) {
            return true;
        } else {
            return false;
        }
    }

    public void addValue(String value, String textBoxID) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        //isTextBoxValueChanged(value);
        use.setTextBoxValue(textboxes.get(textBoxID), value, true);
    }

    public void addValuesToTextboxes(ArrayList<String> values, ArrayList<String> textBoxes) throws ParseException, InterruptedException {
        HashMap<String, String> textboxes = getAllTextboxes();
        String value1 = values.get(0);
        String value2 = values.get(1);
        String value3 = values.get(2);
        use.setTextBoxValue(textboxes.get(textBoxes.get(0)), value1, true);
        goToSneur();
        use.setTextBoxValue(textboxes.get(textBoxes.get(1)), value2, true);
        goToSneur();
        use.setTextBoxValue(textboxes.get(textBoxes.get(2)), value3, true);
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

    public void removeValueInTransaction(String removeBtnId) {

        if (use.isExisting(getAllIcons().get(removeBtnId))) {
            use.clickPath(getAllIcons().get(removeBtnId));
        }
    }

    public boolean isAddedAndRemoved(ArrayList<String> listOfValues, ArrayList<String> textboxesID, String removeBtnId) throws ParseException, InterruptedException {
        addValuesToTextboxes(listOfValues, textboxesID);
        System.out.println(textboxesID.get(0));
        System.out.println(removeBtnId);
        removeAllValuesInTransaction(removeBtnId);
        return !use.isExisting(getAllIcons().get(removeBtnId));
    }

    public boolean isSessionTotalCorrect(String sessionTotalLc, String sessionTotalFc) throws ParseException, InterruptedException {
        String totalLc = use.getValue(getAllHeaders().get(sessionTotalLc));
        String totalFc = use.getValue(getAllHeaders().get(sessionTotalFc));


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
    public boolean isSub_total_Correct(String subTotalExchangeSumId) throws ParseException, InterruptedException {
        String tempSum = use.getValue(getAllHeaders().get(subTotalExchangeSumId));
        double sum = use.toDouble(tempSum);
        if (sum == getTotalValueLc()) {
            return true;
        } else {
            return false;
        }

    }

    public void clearSession(String reverseBtnId, String reverseBtnConfirmId) {
        System.out.println(getAllButton().get(reverseBtnId));
        System.out.println(getAllButton().get(reverseBtnConfirmId));
        use.clickPath(getAllButton().get(reverseBtnId));
        use.clickPath(getAllButton().get(reverseBtnConfirmId));
    }

    public void addNewLineIfNeccessary() throws InterruptedException {
        if (!use.isExisting(getAllButton().get(btnConfirmId))) {
            goToSneur();
        }
    }
}