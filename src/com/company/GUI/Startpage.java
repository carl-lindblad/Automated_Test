package com.company.GUI;

import com.company.Login.ChooseBranchPage;
import com.company.Login.Login;
import com.company.functions;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.DriverFactory.getFirefoxDriver;

public class Startpage {
    private JCheckBox runTestsInHeadlessCheckBox;
    private JRadioButton runTestInSTRadioButton;
    private JRadioButton runTestInDEVRadioButton;
    private JCheckBox openCloseCashdeskCheckBox;
    private JButton runButton;
    private JCheckBox reportMissedSalesCheckBox;
    private JCheckBox sellNotesCheckBox;
    private JCheckBox transferProductCheckBox;
    private JCheckBox maintainCustomerCheckBox;
    private JCheckBox identifyCustomerCheckBox;
    private JPanel startPagePanel;
    private static Login login = new Login();
    private static ChooseBranchPage chooseBranch = new ChooseBranchPage();
    private static functions use = new functions();

    public void StartGUI(){
        JFrame frame = new JFrame("FrameDemo");
        frame.setContentPane(startPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public Startpage() {

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                use.openBrowser();
                login.testingLogin();
                chooseBranch.testingSelectingBranch();

            }
        });
    }
}
