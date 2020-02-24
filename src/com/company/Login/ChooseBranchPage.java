package com.company.Login;

import com.company.Login.ref.chooseBranchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseBranchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private chooseBranchPage given = new chooseBranchPage();
    private chooseBranchPage when = new chooseBranchPage();
    private chooseBranchPage then = new chooseBranchPage();

    public void testingSelectingBranch(){
        given
                .branchSelected();
        when
                .clickingOK()
                .clickLoginToBranch();
        then
                .loggedInToBranchSuccesful();
    }
}
