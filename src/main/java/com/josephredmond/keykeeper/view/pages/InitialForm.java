package com.josephredmond.keykeeper.view.pages;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class InitialForm {
    private JPanel JPLMain;
    private JLabel JLBLTitle;
    private JPanel JPLCenterMain;
    private JPanel JPLBottomMain;
    private JLabel JLBLPassword;
    private JButton JBTNLoadPassword;
    private JButton JBTNChangePassword;
    private JPanel JPLCenterMainCenter;
    private JButton JBTNOpen;
    private JButton JBTNCreate;
    private JPasswordField JPFPassword;
}
