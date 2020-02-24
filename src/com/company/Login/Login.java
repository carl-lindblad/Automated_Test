package com.company.Login;

import com.company.Login.ref.loginPage;

public class Login {
    private loginPage given = new loginPage();
    private loginPage when = new loginPage();
    private loginPage then = new loginPage();


    public void testingLogin() {
        given
                .userSelected();
        when
                .clickingLogin();

        then
                .loginSuccessful();
    }
}
