package com.josephredmond.keykeeper.view;

import com.josephredmond.keykeeper.domain.PasswordEntity;
import com.josephredmond.keykeeper.enums.Page;
import com.josephredmond.keykeeper.service.LoadPasswordService;
import com.josephredmond.keykeeper.service.PasswordManagementService;
import com.josephredmond.keykeeper.view.pages.InitialForm;
import com.josephredmond.keykeeper.view.pages.MainForm;
import com.josephredmond.keykeeper.view.pages.NewEntryForm;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

@Service
public class ClientUI {
    private final JFrame jFrame = new JFrame("KeeKeeper Password Manager");

    private final InitialForm initialForm = new InitialForm();
    private final MainForm mainForm = new MainForm();
    private final NewEntryForm newEntryForm = new NewEntryForm();
    private final LoadPasswordService loadPasswordService;
    private final PasswordManagementService passwordManagementService;
    private JMenuBar jMenuBar = new JMenuBar();


    public ClientUI(final LoadPasswordService loadPasswordService, final PasswordManagementService passwordManagementService) {
        this.loadPasswordService = loadPasswordService;
        this.passwordManagementService = passwordManagementService;
        initializeButtons();
        initializeMenuBar();
        initializeTables();
        jFrame.getContentPane().add(initialForm.getJPLMain());
        jFrame.setSize(1000, 750);
        jFrame.setVisible(true);
    }

    private void initializeButtons() {
        initialForm.getJBTNCreate().addActionListener(new Initial_CreateButtonClickedListener());
        initialForm.getJBTNOpen().addActionListener(new Initial_OpenButtonClickedListener());
        initialForm.getJBTNChangePassword().addActionListener(new Initial_ChangePasswordButtonClickedListener());
        initialForm.getJBTNLoadPassword().addActionListener(new Initial_LoadPasswordButtonClickedListener());

        initialForm.getJBTNChangePassword().setEnabled(false);
        initialForm.getJBTNCreate().setEnabled(false);
        initialForm.getJBTNOpen().setEnabled(false);

        mainForm.getJBTNAdd().addActionListener(new Main_AddButtonClickedListener());
        mainForm.getJBTNRemove().addActionListener(new Main_RemoveButtonClickedListener());
        mainForm.getJBTNSave().addActionListener(new Main_SaveButtonClickedListener());
        mainForm.getJBTNSaveClose().addActionListener(new Main_SaveCloseButtonClickedListener());
        mainForm.getJBTNClose().addActionListener(new Main_CloseButtonClickedListener());
        newEntryForm.getJBTNAddEntity().addActionListener(new NewEntry_AddButtonClickedListener());
        newEntryForm.getJBTNCancel().addActionListener(new NewEntry_CancelButtonClickedListener());

    }

    private void initializeMenuBar() {
        final JMenu jMenu = new JMenu();
        final JMenuItem saveMenuItem = new JMenuItem("Save");
        final JMenuItem saveAndCloseMenuItem = new JMenuItem("Save & Close");
        final JMenuItem closeMenuItem = new JMenuItem("Close");

        jMenu.add(saveMenuItem);
        jMenu.add(saveAndCloseMenuItem);
        jMenu.add(closeMenuItem);
        jMenuBar.add(jMenu);
        jMenuBar.setVisible(true);
        jFrame.setJMenuBar(jMenuBar);
    }

    private void initializeTables() {
        mainForm.getJTBLEntities().removeAll();
        List<PasswordEntity> data = passwordManagementService.getAllPasswordEntities();
        String[] tableHeader = new String[]{"Id", "Website", "Username", "Password"};
        Object[][] dataToLoad = new Object[data.size()][tableHeader.length];
        for(int i = 0; i < data.size(); i++) {
            dataToLoad[i] = new Object[]{data.get(i).getId(), data.get(i).getWebsite(), data.get(i).getUsername(), new String(data.get(i).getPassword())};
        }
        mainForm.getJTBLEntities().setModel(new DefaultTableModel(dataToLoad, tableHeader));
    }
    private void navigate(Page currentPage, Page targetPage) {
        jFrame.getContentPane().removeAll();

        if(Page.INITIAL.equals(currentPage)) {

        }

        if(Page.INITIAL.equals(targetPage)) {
            jFrame.getContentPane().add(initialForm.getJPLMain());
        }

        if(Page.MAIN.equals(currentPage)) {

        }

        if(Page.MAIN.equals(targetPage)) {
            jFrame.getContentPane().add(mainForm.getJPLMain());
        }

        if(Page.NEWENTRY.equals(currentPage)) {
        }

        if(Page.NEWENTRY.equals(targetPage)) {
            jFrame.getContentPane().add(newEntryForm.getJPLMain());

        }

        jFrame.revalidate();
        jFrame.repaint();
    }

    private class Main_SaveButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(jFrame);
            File file = jFileChooser.getSelectedFile();
            saveFile(file);
        }

        private void saveFile(File file) {
            // TODO: Implement save file
        }
    }

    private class Main_SaveCloseButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(jFrame);
            File file = jFileChooser.getSelectedFile();
            saveFile(file);
            loadPasswordService.wipe();
            navigate(Page.MAIN, Page.INITIAL);
        }

        private void saveFile(File file) {
            // TODO: Implement save file and close
        }
    }

    private class Main_CloseButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadPasswordService.wipe();
            navigate(Page.MAIN, Page.INITIAL);
        }
    }

    private class Initial_CreateButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            navigate(Page.INITIAL, Page.MAIN);
        }
    }

    private class Initial_OpenButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            navigate(Page.INITIAL, Page.MAIN);
        }
    }

    private class Main_AddButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            navigate(Page.MAIN, Page.NEWENTRY);
        }
    }

    private class Main_RemoveButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class NewEntry_CancelButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            navigate(Page.NEWENTRY, Page.MAIN);
        }
    }

    private class NewEntry_AddButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PasswordEntity passwordEntity = new PasswordEntity();
            passwordEntity.setUsername(newEntryForm.getJTFUsername().getText());
            passwordEntity.setPassword(newEntryForm.getJPFPassword().getPassword());
            passwordEntity.setWebsite(newEntryForm.getJTFWebsite().getText());
            passwordEntity.setNotes(newEntryForm.getJTANotes().getText());
            passwordManagementService.savePasswordEntity(passwordEntity);
            initializeTables();
            navigate(Page.NEWENTRY, Page.MAIN);
        }
    }

    private class Initial_LoadPasswordButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            char[] password = initialForm.getJPFPassword().getPassword();
            if (password != null && password.length >= 1) {
                loadPasswordService.setPassword(password);
                initialForm.getJPFPassword().setText(generateAsterisks(password.length));
                initialForm.getJBTNLoadPassword().setEnabled(false);
                initialForm.getJBTNChangePassword().setEnabled(true);
                initialForm.getJBTNCreate().setEnabled(true);
                initialForm.getJBTNOpen().setEnabled(true);
            }
        }
    }

    private String generateAsterisks(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private class Initial_ChangePasswordButtonClickedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            initialForm.getJPFPassword().setText("");
            initialForm.getJBTNLoadPassword().setEnabled(true);
            initialForm.getJBTNChangePassword().setEnabled(false);
            initialForm.getJBTNCreate().setEnabled(false);
            initialForm.getJBTNOpen().setEnabled(false);
        }
    }
}
