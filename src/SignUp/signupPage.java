package SignUp;

import Login.loginDemo;
import PracticeCodes.DataAccess;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Toufiq Rich
 */
class WindowSensor extends WindowAdapter {

    public void windowClosing(WindowEvent we) {
        System.out.println("Window is closing");
        /*loginDemo ld = new loginDemo();
         ld.setVisible(true);*/
    }
}

public class signupPage extends javax.swing.JFrame implements ActionListener {

    private JTextField fullnameField, ageField, nidField, emailField, phnField;
    private JFormattedTextField dobField;
    private static DateFormat dateformat = new SimpleDateFormat();
    private JPasswordField passField, confirmPassField;
    private JLabel fullnameLabel, ageLabel, nidLabel, dobLabel, emailLabel, phnLabel, passLabel, confirmPassLabel, accountTypeLabel;
    private JButton signupButton;
    private JRadioButton savingsRadioButton, overdraftRadioButton;

    public signupPage() {
        super("Sign Up");

        setSize(400, 750);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        setLocation((screenWidth - 400) / 2, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);

        Container content = getContentPane();
        content.setLayout(null);

        fullnameLabel = new javax.swing.JLabel("Full Name");
        fullnameLabel.setBounds(20, 12, 200, 18);
        fullnameLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        fullnameLabel.setForeground(new java.awt.Color(255, 255, 255));

        fullnameField = new JTextField();
        fullnameField.setBounds(20, 40, 350, 35);
        fullnameField.setFont(new java.awt.Font("Tahoma", 1, 18));
        fullnameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                int character2 = e.getKeyChar();
                if ((character < 'a' || character > 'z') && (character < 'A' || character > 'Z')) {
                    if (character == ' ' || character2 == 8 || character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Name Type\nOnly Alphabets are Allowed");
                    }
                }
            }

        });

        ageLabel = new javax.swing.JLabel("Age");
        ageLabel.setBounds(20, 80, 70, 25);
        ageLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        ageLabel.setForeground(new java.awt.Color(255, 255, 255));

        ageField = new JTextField();
        ageField.setBounds(20, 113, 350, 35);
        ageField.setFont(new java.awt.Font("Tahoma", 1, 18));
        ageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                int character2 = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    if (character2 == 8 || character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Name Type\nOnly Alphabets are Allowed");
                    }
                }
            }

        });

        nidLabel = new javax.swing.JLabel("NID");
        nidLabel.setBounds(20, 158, 200, 18);
        nidLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        nidLabel.setForeground(new java.awt.Color(255, 255, 255));

        nidField = new JTextField();
        nidField.setBounds(20, 183, 350, 35);
        nidField.setFont(new java.awt.Font("Tahoma", 1, 18));
        nidField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                int character2 = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    if (character2 == 8 || character == '\n') {

                    } else {
                        e.consume();
                        JOptionPane.showMessageDialog(new signupPage(), "Invalid Name Type\nOnly Alphabets are Allowed");
                    }
                }

            }

        });

        dobLabel = new javax.swing.JLabel("Date of Birth");
        dobLabel.setBounds(20, 226, 200, 18);
        dobLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        dobLabel.setForeground(new java.awt.Color(255, 255, 255));

        dobField = new JFormattedTextField(dateformat);

        MaskFormatter dateMask = new MaskFormatter();
        try {
            dateMask = new MaskFormatter("##/##/####");
            dateMask.install(dobField);
            dateMask.setValidCharacters("0123456789");
        } catch (ParseException e) {

            e.printStackTrace();
        }

        dobField = new JFormattedTextField(dateMask);
        dobField.setBounds(20, 249, 350, 35);
        dobField.setFont(new java.awt.Font("Tahoma", 1, 18));
        dobField.setHorizontalAlignment(JTextField.LEFT);
        dobField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    e.consume();
                    JOptionPane.showMessageDialog(new signupPage(), "Invalid Date Type");
                }

            }

        });

        emailLabel = new javax.swing.JLabel("Email");
        emailLabel.setBounds(20, 292, 200, 18);
        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));

        emailField = new JTextField();
        emailField.setBounds(20, 315, 350, 35);
        emailField.setFont(new java.awt.Font("Tahoma", 1, 18));

        phnLabel = new javax.swing.JLabel("Phone No. ");
        phnLabel.setBounds(20, 358, 200, 18);
        phnLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        phnLabel.setForeground(new java.awt.Color(255, 255, 255));

        phnField = new JTextField();
        phnField.setBounds(20, 382, 350, 35);
        phnField.setFont(new java.awt.Font("Tahoma", 1, 18));
        phnField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    e.consume();
                    JOptionPane.showMessageDialog(new signupPage(), "Invalid Phone Number Type");
                }

            }

        });

        passLabel = new javax.swing.JLabel("Password ");
        passLabel.setBounds(20, 425, 200, 18);
        passLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        passLabel.setForeground(new java.awt.Color(255, 255, 255));

        passField = new JPasswordField();
        passField.setBounds(20, 448, 350, 35);
        passField.setFont(new java.awt.Font("Tahoma", 1, 18));

        confirmPassLabel = new javax.swing.JLabel("Confirm Password ");
        confirmPassLabel.setBounds(20, 492, 200, 18);
        confirmPassLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        confirmPassLabel.setForeground(new java.awt.Color(255, 255, 255));

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(20, 515, 350, 35);
        confirmPassField.setFont(new java.awt.Font("Tahoma", 1, 18));

        accountTypeLabel = new javax.swing.JLabel("Account Type ");
        accountTypeLabel.setBounds(20, 560, 160, 20);
        accountTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        accountTypeLabel.setForeground(new java.awt.Color(255, 255, 255));

        savingsRadioButton = new JRadioButton("Savings");
        savingsRadioButton.setBounds(160, 560, 100, 20);
        savingsRadioButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        savingsRadioButton.setBackground(Color.DARK_GRAY);
        savingsRadioButton.setForeground(new java.awt.Color(255, 255, 255));

        overdraftRadioButton = new JRadioButton("Overdraft");
        overdraftRadioButton.setBounds(260, 560, 130, 20);
        overdraftRadioButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        overdraftRadioButton.setBackground(Color.DARK_GRAY);
        overdraftRadioButton.setForeground(new java.awt.Color(255, 255, 255));

        ButtonGroup bg = new ButtonGroup();
        bg.add(savingsRadioButton);
        bg.add(overdraftRadioButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBackground(new java.awt.Color(25, 181, 254));
        signupButton.setFont(new java.awt.Font("Tahoma", 1, 32));
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setBounds(110, 620, 160, 65);
        signupButton.addActionListener(this);

        content.add(fullnameLabel);
        content.add(fullnameField);
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
        content.add(passLabel);
        content.add(passField);
        content.add(confirmPassLabel);
        content.add(confirmPassField);
        content.add(signupButton);
        content.add(accountTypeLabel);
        content.add(savingsRadioButton);
        content.add(overdraftRadioButton);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == signupButton) {

            if (fullnameField.getText().length() == 0 || ageField.getText().length() == 0 || nidField.getText().length() == 0
                    || dobField.getText().length() == 0 || emailField.getText().length() == 0 || phnField.getText().length() == 0
                    || passField.getText().length() == 0 || confirmPassField.getText().length() == 0
                    || (!savingsRadioButton.isSelected() && !overdraftRadioButton.isSelected())) {

                JOptionPane.showMessageDialog(new signupPage(), "You Must Provide All Information");
                //System.out.println("You Must Provide All Information");
            } else if (Integer.parseInt(ageField.getText()) < 18) {
                JOptionPane.showMessageDialog(new signupPage(), "Sorry!! You must be at least 18 years old");
            } else if (nidField.getText().length() > 13 || nidField.getText().length() < 9) {

                JOptionPane.showMessageDialog(new signupPage(), "Invalid NID \nNID can not be more than 13 digit or less than 9 digit");
            } else if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", emailField.getText()))) {
                JOptionPane.showMessageDialog(new signupPage(), "Please enter a valid email");
            } else if (phnField.getText().length() != 11) {

                JOptionPane.showMessageDialog(new signupPage(), "Please enter a valid Phone Number");
            } else if (passField.getText().length() < 6) {
                JOptionPane.showMessageDialog(new signupPage(), "Password must be at least 6 characters");
            } else if (!confirmPassField.getText().equals(passField.getText())) {
                JOptionPane.showMessageDialog(new signupPage(), "Password does not match with previous password");
            } else if (!checkUserSingupData()) {

            } else {
                storesignupData();
                loginDemo ld = new loginDemo();
                ld.setVisible(true);
                this.setVisible(false);
            }
        }

    }

    String accType;
    int overdraftLimit;

    public void storesignupData() {

        if (savingsRadioButton.isSelected()) {
            accType = "Savings";
            overdraftLimit = 0;
        } else {
            accType = "Overdraft";
            overdraftLimit = 300;
        }

        try {
            DataAccess da = new DataAccess();
            String querySignUpData = "INSERT INTO signupinfo (DOB,Fullname,Age,NID,Email,Phone,Password,AccountType,Overdraft) "
                    + "VALUES ('" + dobField.getText().trim() + " ','" + fullnameField.getText().trim() + " ',"
                    + " '" + ageField.getText().trim() + " ','" + nidField.getText().trim() + " ','" + emailField.getText().trim() + " ',"
                    + " '" + phnField.getText().trim() + " ','" + passField.getText().trim() + " ','" + accType + " '," + overdraftLimit + ")";

            String queryLoginInfo = "INSERT INTO logininfo (Username,Password) VALUES ('" + fullnameField.getText().trim() + " ','" + passField.getText().trim() + " ')";

            da.updateDB(querySignUpData);
            da.updateDB(queryLoginInfo);

        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

    }

    int passFlag;
    int emailFlag;
    int nidFlag;
    int phnFlag;

    public boolean checkUserSingupData() {
        passFlag = 0;
        emailFlag = 0;
        nidFlag = 0;
        phnFlag = 0;

        String enteredpassInfo = passField.getText().trim();
        String enteredemailnfo = emailField.getText().trim();
        String enterednidInfo = nidField.getText().trim();
        String enteredphnInfo = phnField.getText().trim();

        try {
            DataAccess da = new DataAccess();
            String querySignUpInfo = "SELECT * from signupinfo ";
            ResultSet rsSignUpInfo = da.getData(querySignUpInfo);

            while (rsSignUpInfo.next()) {

                if (enteredpassInfo.equals(rsSignUpInfo.getString("Password").trim())) {

                    passFlag += 1;

                } else if (enteredemailnfo.equals(rsSignUpInfo.getString("Email").trim())) {

                    emailFlag += 1;

                } else if (enterednidInfo.equals(rsSignUpInfo.getString("NID").trim())) {

                    nidFlag += 1;

                } else if (enteredphnInfo.equals(rsSignUpInfo.getString("Phone").trim())) {

                    phnFlag += 1;

                }
            }

            if (passFlag != 0) {
                JOptionPane.showMessageDialog(new signupPage(), "Enter an unique password");
                return false;
            } else if (emailFlag != 0) {
                JOptionPane.showMessageDialog(new signupPage(), "Enter an unique email");
                return false;
            } else if (nidFlag != 0) {
                JOptionPane.showMessageDialog(new signupPage(), "Enter an unique NID");
                return false;
            } else if (phnFlag != 0) {
                JOptionPane.showMessageDialog(new signupPage(), "Enter an unique phone number");
                return false;
            } else {
                return true;
            }

            // System.out.println("count : " + loginFlag);
        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

        return false;
    }

    public static void main(String[] args) {

        /*signupPage signup = new signupPage();
        signup.addWindowListener(new WindowSensor());
        signup.setVisible(true);*/
    }
}
