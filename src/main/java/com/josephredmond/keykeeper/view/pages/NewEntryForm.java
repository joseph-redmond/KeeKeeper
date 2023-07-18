package com.josephredmond.keykeeper.view.pages;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class NewEntryForm {
    private JPanel JPLMain;
    private JLabel JLBLTitle;
    private JPanel JPLCenterMain;
    private JLabel JLBLUsername;
    private JTextField JTFUsername;
    private JPanel JPLUsername;
    private JPanel JPLNotes;
    private JLabel JLBLNotes;
    private JTextArea JTANotes;
    private JPanel JPLWebsite;
    private JLabel JLBLWebsite;
    private JTextField JTFWebsite;
    private JPanel JPLPassword;
    private JLabel JLBLPassword;
    private JPasswordField JPFPassword;
    private JPanel JPLBottom;
    private JButton JBTNAddEntity;
    private JButton JBTNCancel;
    private JPanel JPLPasswordConfig;
    private JCheckBox JCHShowPassword;
    private JCheckBox JCHIncludeUpperCaseLetters;
    private JCheckBox JCHIncludeLowerCaseLetters;
    private JCheckBox JCHIncludeNumbers;
    private JCheckBox JCHIncludesSymbols;
    private JCheckBox JCHExcludeLikeCharacters;
    private JCheckBox JCHExcludeAmbiguousCharacters;
}
