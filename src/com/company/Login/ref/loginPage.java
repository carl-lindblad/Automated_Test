package com.company.Login.ref;

import com.company.functions;
import org.openqa.selenium.By;

import static com.company.test_settings.IS_SET_TO_ST;
import static com.company.Login.ref.ID_Login.*;

public final class loginPage {
    private functions use = new functions();
    private ID_Login a = new ID_Login();

    public loginPage userSelected()  {

        if(IS_SET_TO_ST){
            use.findAndWait(By.id(ST_USER_ID)).sendKeys("f001lear");
            use.findAndWait(By.id(ST_PASSWORD)).sendKeys("abcdef22");
        }
        else {
            use.selectElementInDropdownByIndex(By.id(USER), 13);
        }
        return this;
    }

    public loginPage clickingLogin() {
        if (IS_SET_TO_ST){
            use.findAndWait(By.id(ST_PROCEED)).click();
        }
        else {
            use.findAndWait(By.id(LOGIN_BTN_1)).click();
        }
        return this;
    }

    public boolean loginSuccessful() {
        return use.findAndWait(By.id(LOGIN_TO_BRANCH_SELECT_BTN)).isDisplayed();
    }


}
