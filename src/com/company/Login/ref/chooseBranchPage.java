package com.company.Login.ref;

import com.company.functions;
import com.company.services;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.company.Login.ref.ID_Login.*;

public class chooseBranchPage {
    private services start = new services();

    private functions use = new functions();
    WebDriverWait wait;


    public chooseBranchPage branchSelected() {
        use.findAndWait(By.id(BRANCH)).click();
        return this;
    }

    public chooseBranchPage clickingOK() {
        use.findAndWait(By.id(OK_BTN)).click();
        return this;
    }

    public chooseBranchPage clickLoginToBranch() {
        use.findAndWait(By.id(LOGIN_BTN_2)).click();
        return this;
    }

    public boolean loggedInToBranchSuccesful() {
        if(use.isDisplayed(By.id(USER_ALREADY_LOGGED_IN_BTN))){
            start.click(By.id(USER_ALREADY_LOGGED_IN_BTN));
        }
        return use.isAtWelcomePage();

    }
}
