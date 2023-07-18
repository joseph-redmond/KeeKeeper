package com.josephredmond.keykeeper.view.pages;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class MainForm {
    private JPanel JPLMain;
    private JLabel JLBLTitle;
    private JPanel JPLBottomMain;
    private JPanel JPLCenterMain;
    private JTable JTBLEntities;
    private JPanel JPLToolbar;
    private javax.swing.JToolBar JTBToolBar;
    private JButton JBTNAdd;
    private JButton JBTNRemove;
    private JButton JBTNSave;
    private JButton JBTNSaveClose;
    private JButton JBTNClose;
    private JScrollPane JSPTableScroll;
}
