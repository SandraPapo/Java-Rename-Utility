package com.company;

import java.io.File;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by Sandra on 2016-05-28.
 */
public class Layout extends JPanel {
    private JPanel panel1;
    private JTextField textField1;
    private JButton chooseDirectoryButton;
    private JButton listFilesButton;
    private JButton renameButton;
    private JFormattedTextField enterTextHereFormattedTextField;
    private JTextArea logTextArea;
    private JComboBox comboBox1;

    private JFileChooser fc;
    private String directory;
    private String text;
    private Layout layout = this;

    public Layout(){
        panel1.setPreferredSize(new Dimension(520,400));

        logTextArea.setEditable(false);
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        disableComponents();
        text = "";

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem() == "Add Prefix") {
                    enterTextHereFormattedTextField.setEnabled(true);
                    enterTextHereFormattedTextField.setText("Enter Text Here");
                }else if(comboBox1.getSelectedItem() == "Add Suffix"){
                    enterTextHereFormattedTextField.setEnabled(true);
                    enterTextHereFormattedTextField.setText("Enter Text Here");
                }else if (comboBox1.getSelectedItem() == "Numbers"){
                    enterTextHereFormattedTextField.setEnabled(false);
                    enterTextHereFormattedTextField.setText("Enter Text Here");
                }else if (comboBox1.getSelectedItem() == "Number Letter"){
                    enterTextHereFormattedTextField.setEnabled(false);
                    enterTextHereFormattedTextField.setText("Enter Text Here");
                }else if (comboBox1.getSelectedItem() == "Remove Entered Text"){
                    enterTextHereFormattedTextField.setEnabled(true);
                    enterTextHereFormattedTextField.setText("Enter Text Here");
                }else {
                    logTextArea.append("Error 3\n");
                }
            }
        });
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File path = new File(directory);
                File[] fileNames = path.listFiles();
                int startNumber = 1;
                int increment = 1;
                if (comboBox1.getSelectedItem() == "Add Prefix") {
                    text = enterTextHereFormattedTextField.getText();
                    RenameFiles.prefix(text, fileNames, directory);
                    logTextArea.append("prefix added\n");
                }else if(comboBox1.getSelectedItem() == "Add Suffix"){
                    text = enterTextHereFormattedTextField.getText();
                    RenameFiles.suffix(text, fileNames, directory);
                    logTextArea.append("suffix added\n");
                }else if (comboBox1.getSelectedItem() == "Numbers"){
                    //if (0 < Integer.parseInt(enterTextHereFormattedTextField.getText()))
                    // startNumber = Integer.parseInt(enterTextHereFormattedTextField.getText());
                    RenameFiles.renameNumbers(startNumber, fileNames, directory);
                    logTextArea.append("Files successfully renamed\n");
                }else if (comboBox1.getSelectedItem() == "Number Letter"){
                    //create pop up telling user to make copies
                    int selectedOption = JOptionPane.showConfirmDialog(null, "In order to use this feature you must create copies where you want the numbers to increment. Click yes if the copies have been created"  , "Warning", JOptionPane.YES_NO_OPTION);
                    if (selectedOption == JOptionPane.YES_OPTION) {

                        String startNumberInput = JOptionPane.showInputDialog(this, "Type in start number:");
                        String incrementNumberInput = JOptionPane.showInputDialog(this, "Type in increment number:");


                        RenameFiles.renameNumbersLetters(Integer.parseInt(incrementNumberInput), Integer.parseInt(startNumberInput), fileNames, directory);
                        logTextArea.append("Files successfully renamed\n");
                    }else{
                        logTextArea.append("Error 1: Files not renamed\n");
                    }
                }else if (comboBox1.getSelectedItem() == "Remove Entered Text") {
                    text = enterTextHereFormattedTextField.getText();
                    RenameFiles.remove(text, fileNames, directory);
                    logTextArea.append("Files successfully renamed\n");
                }else {
                    logTextArea.append("Error 2: Files not renamed\n");
                }
            }
        });
        listFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File path = new File(directory);
                File[] fileNames = path.listFiles();
                RenameFiles.printNames(fileNames, logTextArea);

            }
        });
        enterTextHereFormattedTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        chooseDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(Layout.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    directory = file.getAbsolutePath();
                    //This is where a real application would open the file.
                    logTextArea.append("Directory: " + directory + "." + "\n");
                    enableComponents();
                } else {
                    logTextArea.append("Open command cancelled by user." + "\n");
                }
                logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
                textField1.setText(directory);
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        enterTextHereFormattedTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(enterTextHereFormattedTextField.getText().equals("Enter Text Here") && enterTextHereFormattedTextField.isEnabled())
                enterTextHereFormattedTextField.setText("");
            }
        });
    }
    public JPanel getPanel() {
        JPanel panel = panel1;
        return panel;
    }
    private void disableComponents(){
        listFilesButton.setEnabled(false);
        renameButton.setEnabled(false);
        comboBox1.setEnabled(false);
        enterTextHereFormattedTextField.setEnabled(false);
        textField1.setEnabled(false);
    }
    private void enableComponents(){
        listFilesButton.setEnabled(true);
        renameButton.setEnabled(true);
        comboBox1.setEnabled(true);
        enterTextHereFormattedTextField.setEnabled(true);
    }




}

