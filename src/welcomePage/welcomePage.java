package welcomePage;

import Login.loginDemo;
import PracticeCodes.DataAccess;
import SignUp.signupPage;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Toufiq Rich
 */
class WindowSensor extends WindowAdapter {

    public void windowClosing(WindowEvent we) {
        System.out.println("Window is closing");
        System.exit(0);
    }
}

public class welcomePage extends javax.swing.JFrame implements ActionListener {

    private JTextField usernameShow, depositAmountField, withdrawAmountField, transferToField, transferAmountField;
    private JButton okdepositButton, okwithdrawButton, oktransferButton, historyDepositButton, historyWithdrawButton, historyTransferButton;
    private JButton depositButton, withdrawButton, transactionButton, aboutButton, helpButton, myAccountButton;
    private JButton logoutButton, transferButton, HistoryButton;
    private JPanel optionPanel, transactionOptionPanel, historyOptionPanel;
    private JLabel depositAmountLabel, withdrawAmountLabel, transferToLabel, transferAmountLabel, helpLabel, helpLabel2;

    private JTextField fullnameField, useridField, ageField, nidField, emailField, phnField, dobField, accountTypeField, balanceField, overdraftField;
    private JLabel fullnameLabel, useridLabel, ageLabel, nidLabel, dobLabel, emailLabel, phnLabel, accountTypeLabel, balanceLabel, overdraftLabel;
    private JTextArea aboutText;

    private JTable depositTable, withdrawTable, transferTable;

    private String textUsernameShow, storePass;

    public welcomePage() {
        super("Bank Management System");

        setSize(750, 570);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        setLocation((screenWidth - 800) / 2, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new java.awt.Color(1, 50, 67));

        Container content = getContentPane();
        content.setLayout(null);

        usernameShow = new JTextField("No User Logged In");
        usernameShow.setBounds(0, 0, 170, 35);
        usernameShow.setFont(new java.awt.Font("Tahoma", 1, 18));
        usernameShow.setEditable(false);

        myAccountButton = new JButton("My Account");
        myAccountButton.setBackground(new java.awt.Color(0, 230, 64));
        myAccountButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        myAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        myAccountButton.setBounds(5, 90, 160, 50);
        myAccountButton.addActionListener(this);

        transactionButton = new JButton("Transaction");
        transactionButton.setBackground(new java.awt.Color(0, 230, 64));
        transactionButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        transactionButton.setForeground(new java.awt.Color(255, 255, 255));
        transactionButton.setBounds(5, 150, 160, 50);
        transactionButton.addActionListener(this);

        HistoryButton = new JButton("History");
        HistoryButton.setBackground(new java.awt.Color(0, 230, 64));
        HistoryButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        HistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        HistoryButton.setBounds(5, 210, 160, 50);
        HistoryButton.addActionListener(this);

        aboutButton = new JButton("About");
        aboutButton.setBackground(new java.awt.Color(0, 230, 64));
        aboutButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        aboutButton.setForeground(new java.awt.Color(255, 255, 255));
        aboutButton.setBounds(5, 270, 160, 50);
        aboutButton.addActionListener(this);

        helpButton = new JButton("Help");
        helpButton.setBackground(new java.awt.Color(0, 230, 64));
        helpButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setBounds(5, 330, 160, 50);
        helpButton.addActionListener(this);

        logoutButton = new JButton("Log Out");
        logoutButton.setBackground(new java.awt.Color(242, 38, 19));
        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setBounds(20, 460, 130, 50);
        logoutButton.addActionListener(this);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(new java.awt.Color(255, 0, 0));
        depositButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        depositButton.setForeground(new java.awt.Color(255, 255, 255));
        depositButton.setBounds(250, 20, 160, 50);
        depositButton.addActionListener(this);
        depositButton.setVisible(false);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new java.awt.Color(255, 0, 0));
        withdrawButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        withdrawButton.setForeground(new java.awt.Color(255, 255, 255));
        withdrawButton.setBounds(450, 20, 160, 50);
        withdrawButton.addActionListener(this);
        withdrawButton.setVisible(false);

        transferButton = new JButton("Transfer");
        transferButton.setBackground(new java.awt.Color(255, 0, 0));
        transferButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        transferButton.setForeground(new java.awt.Color(255, 255, 255));
        transferButton.setBounds(340, 80, 160, 50);
        transferButton.addActionListener(this);
        transferButton.setVisible(false);

        depositAmountLabel = new javax.swing.JLabel("Enter the amount to Deposit ");
        depositAmountLabel.setBounds(280, 200, 300, 20);
        depositAmountLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        depositAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        depositAmountLabel.setVisible(false);

        depositAmountField = new JTextField();
        depositAmountField.setBounds(280, 240, 200, 35);
        depositAmountField.setFont(new java.awt.Font("Tahoma", 1, 18));
        depositAmountField.setVisible(false);
        depositAmountField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    if (character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Amount Type");
                    }
                }

            }

        });

        transferToLabel = new javax.swing.JLabel("Enter the the receiver ID ");
        transferToLabel.setBounds(280, 200, 300, 20);
        transferToLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        transferToLabel.setForeground(new java.awt.Color(255, 255, 255));
        transferToLabel.setVisible(false);

        transferToField = new JTextField();
        transferToField.setBounds(280, 222, 200, 35);
        transferToField.setFont(new java.awt.Font("Tahoma", 1, 18));
        transferToField.setVisible(false);

        transferAmountLabel = new javax.swing.JLabel("Enter the amount to Transfer ");
        transferAmountLabel.setBounds(280, 270, 400, 20);
        transferAmountLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        transferAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        transferAmountLabel.setVisible(false);

        transferAmountField = new JTextField();
        transferAmountField.setBounds(280, 292, 200, 35);
        transferAmountField.setFont(new java.awt.Font("Tahoma", 1, 18));
        transferAmountField.setVisible(false);
        transferAmountField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    if (character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Amount Type");
                    }
                }

            }

        });

        oktransferButton = new JButton("OK");
        oktransferButton.setBackground(new java.awt.Color(255, 0, 0));
        oktransferButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        oktransferButton.setForeground(new java.awt.Color(255, 255, 255));
        oktransferButton.setBounds(320, 340, 80, 50);
        oktransferButton.addActionListener(this);
        oktransferButton.setVisible(false);

        okdepositButton = new JButton("OK");
        okdepositButton.setBackground(new java.awt.Color(255, 0, 0));
        okdepositButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        okdepositButton.setForeground(new java.awt.Color(255, 255, 255));
        okdepositButton.setBounds(340, 280, 80, 50);
        okdepositButton.addActionListener(this);
        okdepositButton.setVisible(false);

        withdrawAmountLabel = new javax.swing.JLabel("Enter the amount to Withdraw");
        withdrawAmountLabel.setBounds(280, 200, 330, 20);
        withdrawAmountLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        withdrawAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        withdrawAmountLabel.setVisible(false);

        withdrawAmountField = new JTextField();
        withdrawAmountField.setBounds(280, 240, 200, 35);
        withdrawAmountField.setFont(new java.awt.Font("Tahoma", 1, 18));
        withdrawAmountField.setVisible(false);
        withdrawAmountField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    if (character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Amount Type");
                    }
                }

            }

        });

        okwithdrawButton = new JButton("OK");
        okwithdrawButton.setBackground(new java.awt.Color(255, 0, 0));
        okwithdrawButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        okwithdrawButton.setForeground(new java.awt.Color(255, 255, 255));
        okwithdrawButton.setBounds(340, 280, 80, 50);
        okwithdrawButton.addActionListener(this);
        okwithdrawButton.setVisible(false);

        helpLabel = new javax.swing.JLabel("We are here to help 24/7");
        helpLabel.setBounds(240, 160, 450, 36);
        helpLabel.setFont(new java.awt.Font("Tahoma", 1, 32));
        helpLabel.setForeground(new java.awt.Color(255, 255, 255));
        helpLabel.setVisible(false);

        helpLabel2 = new javax.swing.JLabel("Call Us at ******");
        helpLabel2.setBounds(290, 220, 330, 36);
        helpLabel2.setFont(new java.awt.Font("Tahoma", 1, 36));
        helpLabel2.setForeground(new java.awt.Color(255, 255, 255));
        helpLabel2.setVisible(false);

        optionPanel = new JPanel();
        optionPanel.setBounds(0, 0, 170, 570);
        optionPanel.setBackground(new java.awt.Color(51, 110, 123));

        transactionOptionPanel = new JPanel();
        transactionOptionPanel.setBounds(170, 0, 598, 150);
        transactionOptionPanel.setBackground(new java.awt.Color(52, 73, 94));
        transactionOptionPanel.setVisible(false);

        historyDepositButton = new JButton("Deposit");
        historyDepositButton.setBackground(new java.awt.Color(25, 181, 254));
        historyDepositButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        historyDepositButton.setForeground(new java.awt.Color(255, 255, 255));
        historyDepositButton.setBounds(250, 20, 160, 50);
        historyDepositButton.addActionListener(this);
        historyDepositButton.setVisible(false);

        historyWithdrawButton = new JButton("Withdraw");
        historyWithdrawButton.setBackground(new java.awt.Color(25, 181, 254));
        historyWithdrawButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        historyWithdrawButton.setForeground(new java.awt.Color(255, 255, 255));
        historyWithdrawButton.setBounds(450, 20, 160, 50);
        historyWithdrawButton.addActionListener(this);
        historyWithdrawButton.setVisible(false);

        historyTransferButton = new JButton("Transfer");
        historyTransferButton.setBackground(new java.awt.Color(25, 181, 254));
        historyTransferButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        historyTransferButton.setForeground(new java.awt.Color(255, 255, 255));
        historyTransferButton.setBounds(340, 90, 160, 50);
        historyTransferButton.addActionListener(this);
        historyTransferButton.setVisible(false);

        historyOptionPanel = new JPanel();
        historyOptionPanel.setBounds(170, 0, 598, 150);
        historyOptionPanel.setBackground(new java.awt.Color(52, 73, 94));
        historyOptionPanel.setVisible(false);

        aboutText = new JTextArea();
        aboutText.setBounds(230, 100, 440, 300);
        aboutText.setFont(new java.awt.Font("Tahoma", 1, 30));
        aboutText.setText("ESTED in 2018.\nQuality service assurance.\n\n              THANK YOU  ");
        aboutText.setVisible(false);

        fullnameLabel = new javax.swing.JLabel("Full Name");
        fullnameLabel.setBounds(245, 7, 200, 18);
        fullnameLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        fullnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fullnameLabel.setVisible(false);

        fullnameField = new JTextField();
        fullnameField.setBounds(245, 30, 350, 25);
        fullnameField.setFont(new java.awt.Font("Tahoma", 1, 18));
        fullnameField.setEditable(false);
        fullnameField.setVisible(false);

        useridLabel = new javax.swing.JLabel("User ID");
        useridLabel.setBounds(245, 60, 200, 18);
        useridLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        useridLabel.setForeground(new java.awt.Color(255, 255, 255));
        useridLabel.setVisible(false);

        useridField = new JTextField();
        useridField.setBounds(245, 83, 350, 25);
        useridField.setFont(new java.awt.Font("Tahoma", 1, 18));
        useridField.setEditable(false);
        useridField.setVisible(false);

        ageLabel = new javax.swing.JLabel("Age");
        ageLabel.setBounds(245, 113, 70, 18);
        ageLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        ageLabel.setForeground(new java.awt.Color(255, 255, 255));
        ageLabel.setVisible(false);

        ageField = new JTextField();
        ageField.setBounds(245, 133, 350, 25);
        ageField.setFont(new java.awt.Font("Tahoma", 1, 18));
        ageField.setEditable(false);
        ageField.setVisible(false);

        nidLabel = new javax.swing.JLabel("NID");
        nidLabel.setBounds(245, 161, 200, 18);
        nidLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        nidLabel.setForeground(new java.awt.Color(255, 255, 255));
        nidLabel.setVisible(false);

        nidField = new JTextField();
        nidField.setBounds(245, 185, 350, 25);
        nidField.setFont(new java.awt.Font("Tahoma", 1, 18));
        nidField.setEditable(false);
        nidField.setVisible(false);

        dobLabel = new javax.swing.JLabel("Date of Birth");
        dobLabel.setBounds(245, 217, 200, 18);
        dobLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        dobLabel.setForeground(new java.awt.Color(255, 255, 255));
        dobLabel.setVisible(false);

        dobField = new JFormattedTextField();
        dobField.setBounds(245, 239, 350, 25);
        dobField.setFont(new java.awt.Font("Tahoma", 1, 18));
        dobField.setHorizontalAlignment(JTextField.LEFT);
        dobField.setEditable(false);
        dobField.setVisible(false);

        emailLabel = new javax.swing.JLabel("Email");
        emailLabel.setBounds(245, 270, 200, 18);
        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setVisible(false);

        emailField = new JTextField();
        emailField.setBounds(245, 293, 350, 25);
        emailField.setFont(new java.awt.Font("Tahoma", 1, 18));
        emailField.setEditable(false);
        emailField.setVisible(false);

        phnLabel = new javax.swing.JLabel("Phone No. ");
        phnLabel.setBounds(245, 321, 200, 18);
        phnLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        phnLabel.setForeground(new java.awt.Color(255, 255, 255));
        phnLabel.setVisible(false);

        phnField = new JTextField();
        phnField.setBounds(245, 343, 350, 25);
        phnField.setFont(new java.awt.Font("Tahoma", 1, 18));
        phnField.setEditable(false);
        phnField.setVisible(false);

        balanceLabel = new javax.swing.JLabel("Current Balance");
        balanceLabel.setBounds(245, 374, 200, 18);
        balanceLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        balanceLabel.setForeground(new java.awt.Color(255, 255, 255));
        balanceLabel.setVisible(false);

        balanceField = new JTextField();
        balanceField.setBounds(245, 396, 350, 25);
        balanceField.setFont(new java.awt.Font("Tahoma", 1, 18));
        balanceField.setEditable(false);
        balanceField.setVisible(false);

        accountTypeLabel = new javax.swing.JLabel("Account Type");
        accountTypeLabel.setBounds(245, 421, 200, 18);
        accountTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        accountTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountTypeLabel.setVisible(false);

        accountTypeField = new JTextField();
        accountTypeField.setBounds(245, 445, 350, 25);
        accountTypeField.setFont(new java.awt.Font("Tahoma", 1, 18));
        accountTypeField.setEditable(false);
        accountTypeField.setVisible(false);

        overdraftLabel = new javax.swing.JLabel("Overdraft Limit");
        overdraftLabel.setBounds(245, 475, 200, 18);
        overdraftLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        overdraftLabel.setForeground(new java.awt.Color(255, 255, 255));
        overdraftLabel.setVisible(false);

        overdraftField = new JTextField();
        overdraftField.setBounds(245, 499, 350, 25);
        overdraftField.setFont(new java.awt.Font("Tahoma", 1, 18));
        overdraftField.setEditable(false);
        overdraftField.setVisible(false);

        depositTable = new JTable();
        depositTable.setBounds(270, 180, 360, 340);
        depositTable.setFont(new java.awt.Font("Tahoma", 0, 18));
        depositTable.setVisible(false);

        withdrawTable = new JTable();
        withdrawTable.setBounds(270, 180, 360, 340);
        withdrawTable.setFont(new java.awt.Font("Tahoma", 0, 18));
        withdrawTable.setVisible(false);

        transferTable = new JTable();
        transferTable.setBounds(210, 180, 480, 340);
        transferTable.setFont(new java.awt.Font("Tahoma", 1, 10));
        transferTable.setVisible(false);

        content.add(usernameShow);
        content.add(depositButton);
        content.add(withdrawButton);
        content.add(transferButton);
        content.add(myAccountButton);
        content.add(HistoryButton);
        content.add(logoutButton);
        content.add(transactionButton);
        content.add(aboutButton);
        content.add(helpButton);
        content.add(optionPanel);
        content.add(transactionOptionPanel);
        content.add(depositAmountLabel);
        content.add(depositAmountField);
        content.add(okdepositButton);
        content.add(withdrawAmountLabel);
        content.add(withdrawAmountField);
        content.add(okwithdrawButton);
        content.add(helpLabel);
        content.add(helpLabel2);
        content.add(transferToField);
        content.add(transferToLabel);
        content.add(transferAmountLabel);
        content.add(transferAmountField);
        content.add(oktransferButton);
        content.add(aboutText);
        content.add(historyDepositButton);
        content.add(historyTransferButton);
        content.add(historyWithdrawButton);
        content.add(historyOptionPanel);
        content.add(depositTable);
        content.add(withdrawTable);
        content.add(transferTable);

        content.add(fullnameLabel);
        content.add(fullnameField);
        content.add(useridField);
        content.add(useridLabel);
        content.add(ageLabel);
        content.add(ageField);
        content.add(nidLabel);
        content.add(nidField);
        content.add(dobLabel);
        content.add(dobField);
        content.add(emailLabel);
        content.add(emailField);
        content.add(phnLabel);
        content.add(phnField);
        content.add(balanceField);
        content.add(balanceLabel);
        content.add(overdraftLabel);
        content.add(overdraftField);
        content.add(accountTypeLabel);
        content.add(accountTypeField);

    }

    public void setusernameShow(String s) {
        textUsernameShow = s;
        String part[] = s.split(" ", 2);
        usernameShow.setText("Hello, " + part[0]);
    }

    public void getPass(String p) {
        storePass = p;
    }

    public void actionPerformed(ActionEvent ae) {

        allTransaction transaction = new allTransaction(textUsernameShow, storePass);

        if (ae.getSource() == logoutButton) {
            loginDemo ld = new loginDemo();
            ld.addWindowListener(new WindowSensor());
            ld.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == transactionButton) {
            helpLabel.setVisible(false);
            helpLabel2.setVisible(false);
            fullnameField.setVisible(false);
            fullnameLabel.setVisible(false);
            ageField.setVisible(false);
            ageLabel.setVisible(false);
            phnField.setVisible(false);
            phnLabel.setVisible(false);
            emailField.setVisible(false);
            emailLabel.setVisible(false);
            dobField.setVisible(false);
            dobLabel.setVisible(false);
            nidField.setVisible(false);
            nidLabel.setVisible(false);
            balanceLabel.setVisible(false);
            balanceField.setVisible(false);
            useridField.setVisible(false);
            useridLabel.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            aboutText.setVisible(false);
            overdraftLabel.setVisible(false);
            overdraftField.setVisible(false);
            accountTypeLabel.setVisible(false);
            accountTypeField.setVisible(false);
            historyDepositButton.setVisible(false);
            historyTransferButton.setVisible(false);
            historyWithdrawButton.setVisible(false);
            historyOptionPanel.setVisible(false);
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            transactionOptionPanel.setVisible(true);
            depositButton.setVisible(true);
            withdrawButton.setVisible(true);
            transferButton.setVisible(true);

        } else if (ae.getSource() == depositButton) {
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            transactionOptionPanel.setVisible(true);
            depositButton.setVisible(true);
            withdrawButton.setVisible(true);
            depositAmountLabel.setVisible(true);
            depositAmountField.setVisible(true);
            okdepositButton.setVisible(true);

        } else if (ae.getSource() == withdrawButton) {
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            depositButton.setVisible(true);
            withdrawButton.setVisible(true);
            transactionOptionPanel.setVisible(true);
            withdrawAmountLabel.setVisible(true);
            withdrawAmountField.setVisible(true);
            okwithdrawButton.setVisible(true);

        } else if (ae.getSource() == transferButton) {;
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            transferAmountField.setVisible(true);
            transferAmountLabel.setVisible(true);
            transferToField.setVisible(true);
            transferToLabel.setVisible(true);
            oktransferButton.setVisible(true);
            transactionOptionPanel.setVisible(true);
            depositButton.setVisible(true);
            withdrawButton.setVisible(true);

        } else if (ae.getSource() == helpButton) {
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            transactionOptionPanel.setVisible(false);
            depositButton.setVisible(false);
            withdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            fullnameField.setVisible(false);
            fullnameLabel.setVisible(false);
            ageField.setVisible(false);
            transferButton.setVisible(false);
            ageLabel.setVisible(false);
            phnField.setVisible(false);
            phnLabel.setVisible(false);
            emailField.setVisible(false);
            emailLabel.setVisible(false);
            dobField.setVisible(false);
            dobLabel.setVisible(false);
            nidField.setVisible(false);
            nidLabel.setVisible(false);
            balanceLabel.setVisible(false);
            balanceField.setVisible(false);
            useridField.setVisible(false);
            useridLabel.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            aboutText.setVisible(false);
            overdraftLabel.setVisible(false);
            overdraftField.setVisible(false);
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            accountTypeLabel.setVisible(false);
            accountTypeField.setVisible(false);
            historyDepositButton.setVisible(false);
            historyTransferButton.setVisible(false);
            historyWithdrawButton.setVisible(false);
            historyOptionPanel.setVisible(false);
            helpLabel.setVisible(true);
            helpLabel2.setVisible(true);

        } else if (ae.getSource() == aboutButton) {
            helpLabel.setVisible(false);
            helpLabel2.setVisible(false);
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            transactionOptionPanel.setVisible(false);
            depositButton.setVisible(false);
            withdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            fullnameField.setVisible(false);
            fullnameLabel.setVisible(false);
            transferButton.setVisible(false);
            ageField.setVisible(false);
            ageLabel.setVisible(false);
            phnField.setVisible(false);
            phnLabel.setVisible(false);
            emailField.setVisible(false);
            emailLabel.setVisible(false);
            dobField.setVisible(false);
            dobLabel.setVisible(false);
            nidField.setVisible(false);
            nidLabel.setVisible(false);
            balanceLabel.setVisible(false);
            balanceField.setVisible(false);
            useridField.setVisible(false);
            useridLabel.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            overdraftLabel.setVisible(false);
            overdraftField.setVisible(false);
            accountTypeLabel.setVisible(false);
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            accountTypeField.setVisible(false);
            historyDepositButton.setVisible(false);
            historyTransferButton.setVisible(false);
            historyWithdrawButton.setVisible(false);
            historyOptionPanel.setVisible(false);
            aboutText.setVisible(true);

        } else if (ae.getSource() == myAccountButton) {
            UserData();
            helpLabel.setVisible(false);
            helpLabel2.setVisible(false);
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            transactionOptionPanel.setVisible(false);
            depositButton.setVisible(false);
            withdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            transferButton.setVisible(false);
            okdepositButton.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            historyDepositButton.setVisible(false);
            historyTransferButton.setVisible(false);
            historyWithdrawButton.setVisible(false);
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            historyOptionPanel.setVisible(false);
            aboutText.setVisible(false);
            fullnameField.setVisible(true);
            fullnameLabel.setVisible(true);
            ageField.setVisible(true);
            ageLabel.setVisible(true);
            phnField.setVisible(true);
            phnLabel.setVisible(true);
            emailField.setVisible(true);
            emailLabel.setVisible(true);
            dobField.setVisible(true);
            dobLabel.setVisible(true);
            nidField.setVisible(true);
            nidLabel.setVisible(true);
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            useridField.setVisible(true);
            useridLabel.setVisible(true);
            overdraftLabel.setVisible(true);
            overdraftField.setVisible(true);
            accountTypeLabel.setVisible(true);
            accountTypeField.setVisible(true);

        } else if (ae.getSource() == HistoryButton) {

            helpLabel.setVisible(false);
            helpLabel2.setVisible(false);
            withdrawAmountLabel.setVisible(false);
            withdrawAmountField.setVisible(false);
            okwithdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            transactionOptionPanel.setVisible(false);
            depositButton.setVisible(false);
            withdrawButton.setVisible(false);
            depositAmountLabel.setVisible(false);
            depositAmountField.setVisible(false);
            okdepositButton.setVisible(false);
            fullnameField.setVisible(false);
            fullnameLabel.setVisible(false);
            transferButton.setVisible(false);
            ageField.setVisible(false);
            ageLabel.setVisible(false);
            phnField.setVisible(false);
            phnLabel.setVisible(false);
            emailField.setVisible(false);
            emailLabel.setVisible(false);
            dobField.setVisible(false);
            dobLabel.setVisible(false);
            nidField.setVisible(false);
            nidLabel.setVisible(false);
            balanceLabel.setVisible(false);
            balanceField.setVisible(false);
            useridField.setVisible(false);
            useridLabel.setVisible(false);
            transferAmountField.setVisible(false);
            transferAmountLabel.setVisible(false);
            transferToField.setVisible(false);
            transferToLabel.setVisible(false);
            oktransferButton.setVisible(false);
            overdraftLabel.setVisible(false);
            overdraftField.setVisible(false);
            accountTypeLabel.setVisible(false);
            accountTypeField.setVisible(false);
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            aboutText.setVisible(false);
            historyOptionPanel.setVisible(true);
            historyDepositButton.setVisible(true);
            historyTransferButton.setVisible(true);
            historyWithdrawButton.setVisible(true);

        } else if (ae.getSource() == historyDepositButton) {

            depositHistory();
            withdrawTable.setVisible(false);
            transferTable.setVisible(false);
            depositTable.setVisible(true);

        } else if (ae.getSource() == historyWithdrawButton) {

            withdrawHistory();
            depositTable.setVisible(false);
            transferTable.setVisible(false);
            withdrawTable.setVisible(true);
        } else if (ae.getSource() == historyTransferButton) {

            transferHistory();
            depositTable.setVisible(false);
            withdrawTable.setVisible(false);
            transferTable.setVisible(true);

        } else if (ae.getSource() == oktransferButton) {
            if (transferAmountField.getText().trim().length() == 0 || transferToField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(new welcomePage(), "You must fillup both information");
            } else {
                if (Integer.parseInt(transferAmountField.getText().trim()) == Integer.parseInt(useridField.getText().trim())) {
                    JOptionPane.showMessageDialog(new welcomePage(), "Reicever id and USER ID can't be same");
                } else {
                    System.out.println("Im here");
                    if (Integer.parseInt(transferAmountField.getText().trim()) <= 0) {
                        JOptionPane.showMessageDialog(new welcomePage(), "Transfer amount can't be 0 or less than 0");
                    } else {

                        transaction.transfer(Integer.parseInt(transferToField.getText().trim()), Integer.parseInt(transferAmountField.getText().trim()));
                    }
                }
            }
            transferAmountField.setText("");
            transferToField.setText("");

        } else if (ae.getSource() == okdepositButton) {
            transaction.deposit(Integer.parseInt(depositAmountField.getText().trim()));
            depositAmountField.setText("");
        } else if (ae.getSource() == okwithdrawButton) {
            transaction.withdraw(Integer.parseInt(withdrawAmountField.getText().trim()));
            withdrawAmountField.setText("");
        }

    }

    void UserData() {

        System.out.println(textUsernameShow);

        try {
            DataAccess da = new DataAccess();
            String queryUserInfo = "SELECT * from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsUserInfo = da.getData(queryUserInfo);

            while (rsUserInfo.next()) {

                this.fullnameField.setText(rsUserInfo.getString("Fullname").trim());
                this.ageField.setText(rsUserInfo.getString("Age").trim());
                this.nidField.setText(rsUserInfo.getString("NID").trim());
                this.emailField.setText(rsUserInfo.getString("Email").trim());
                this.phnField.setText(rsUserInfo.getString("Phone").trim());
                this.dobField.setText(rsUserInfo.getString("DOB").trim());
                this.balanceField.setText(rsUserInfo.getString("Balance").trim());
                this.useridField.setText(rsUserInfo.getString("ID").trim());
                this.overdraftField.setText(rsUserInfo.getString("Overdraft").trim());
                this.accountTypeField.setText(rsUserInfo.getString("AccountType").trim());

            }
        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

    }

    void depositHistory() {
        int ID = -1;

        try {
            DataAccess da = new DataAccess();
            String[] columnNames = new String[]{
                "Date", "Amount"
            };
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            String queryID = "SELECT ID from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsID = da.getData(queryID);

            while (rsID.next()) {
                ID = Integer.parseInt(rsID.getString("ID"));
            }

            String query = "select Date,Amount from transaction where ID=" + ID + " and Status='Deposit' ";
            ResultSet rs = da.getData(query);

            model.addRow(new Object[]{"Date", "Amount"});
            while (rs.next()) {
                String col1 = rs.getString("Date");
                String col2 = rs.getString("Amount");
                model.addRow(new Object[]{col1, col2});
            }
            depositTable.setModel(model);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    void withdrawHistory() {
        int ID = -1;

        try {
            DataAccess da = new DataAccess();
            String[] columnNames = new String[]{
                "Date", "Amount"
            };
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            String queryID = "SELECT ID from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsID = da.getData(queryID);

            while (rsID.next()) {
                ID = Integer.parseInt(rsID.getString("ID"));
                System.out.println(ID);
            }

            String query = "select Date,Amount from transaction where ID=" + ID + " and Status='Withdraw' ";
            ResultSet rs = da.getData(query);

            model.addRow(new Object[]{"Date", "Amount"});
            while (rs.next()) {
                String col1 = rs.getString("Date");
                String col2 = rs.getString("Amount");
                model.addRow(new Object[]{col1, col2});
            }
            withdrawTable.setModel(model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    void transferHistory() {
        int ID = -1;

        try {
            DataAccess da = new DataAccess();
            String[] columnNames = new String[]{
                "Date", "Amount", "Status"
            };
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            String queryID = "SELECT ID from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsID = da.getData(queryID);

            while (rsID.next()) {
                ID = Integer.parseInt(rsID.getString("ID"));
                System.out.println(ID);
            }

            String query = "select Date,Amount,SenderID,ReceiverID,Status from transaction where ID=" + ID + " and (Status='Send Money' or Status='Received Money') ";
            ResultSet rs = da.getData(query);

            model.addRow(new Object[]{"Date", "Amount", "Sender/Reciever ID"});
            while (rs.next()) {
                if (rs.getString("Status").trim().equals("Send Money")) {
                    String col1 = rs.getString("Date");
                    String col2 = rs.getString("Amount");
                    String col3 = rs.getString("Status") + " To" + " ID: " + rs.getString("ReceiverID");
                    model.addRow(new Object[]{col1, col2, col3});
                } else if (rs.getString("Status").trim().equals("Received Money")) {
                    String col1 = rs.getString("Date");
                    String col2 = rs.getString("Amount");
                    String col3 = rs.getString("Status") + " From" + " ID: " + rs.getString("SenderID");
                    model.addRow(new Object[]{col1, col2, col3});
                }
            }
            transferTable.setModel(model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {

        /*welcomePage wp = new welcomePage();
        wp.setVisible(true);*/
    }

}
