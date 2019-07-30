package Login;

import PracticeCodes.DataAccess;
import SignUp.signupPage;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import welcomePage.adminWelcomePage;
import welcomePage.welcomePage;

/**
 *
 * @author Toufiq Rich
 */
class WindowSensor extends WindowAdapter {

    public void windowClosing(WindowEvent we) {
        System.out.println("Window is closing");
        loginDemo ld = new loginDemo();
        ld.setVisible(true);
    }
}

public class loginDemo extends javax.swing.JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private String[] optionStrings = {"Admin", "Customer"};
    private JComboBox loginOption;
    private JLabel usernameLabel, passLabel, newuserLabel;

    public loginDemo() {
        super("User Login");

        setSize(500, 500);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        setLocation((screenWidth - 500) / 2, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);

        Container content = getContentPane();
        content.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(70, 80, 100, 35);
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 18));

        passLabel = new JLabel("Password");
        passLabel.setBounds(70, 140, 100, 35);
        passLabel.setForeground(new java.awt.Color(255, 255, 255));
        passLabel.setFont(new java.awt.Font("Tahoma", 1, 18));

        usernameField = new JTextField();
        usernameField.setFont(new java.awt.Font("Tahoma", 0, 20));
        usernameField.setBounds(190, 70, 220, 50);

        passwordField = new JPasswordField();
        passwordField.setFont(new java.awt.Font("Tahoma", 0, 20));
        passwordField.setBounds(190, 130, 220, 50);

        loginOption = new JComboBox(optionStrings);
        loginOption.setSelectedIndex(1);
        loginOption.setBackground(new java.awt.Color(236, 236, 236));
        loginOption.setFont(new java.awt.Font("Tahoma", 1, 20));
        loginOption.setForeground(new java.awt.Color(34, 49, 63));
        loginOption.setBounds(180, 200, 160, 30);
        loginOption.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBackground(new java.awt.Color(0, 230, 64));
        loginButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setBounds(180, 240, 130, 70);
        loginButton.addActionListener(this);

        newuserLabel = new JLabel("New User?");
        newuserLabel.setBounds(180, 310, 110, 35);
        newuserLabel.setForeground(new java.awt.Color(255, 255, 255));
        newuserLabel.setFont(new java.awt.Font("Tahoma", 1, 20));

        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new java.awt.Color(0, 204, 255));
        signUpButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        signUpButton.setForeground(new java.awt.Color(255, 255, 255));
        signUpButton.setBounds(180, 350, 130, 70);
        signUpButton.addActionListener(this);

        content.add(usernameLabel);
        content.add(passLabel);
        content.add(usernameField);
        content.add(passwordField);
        content.add(loginButton);
        content.add(newuserLabel);
        content.add(signUpButton);
        content.add(loginOption);
    }

    public void actionPerformed(ActionEvent ae) {

        String text = usernameField.getText();

        if (ae.getSource() == loginButton) {
            if (text.length() == 0) {
                JOptionPane.showMessageDialog(new loginDemo(), "You Must Provide Both Information");
                System.out.println("You Must Provide Both Information");
            } else {

                String msg = (String) loginOption.getSelectedItem();

                switch (msg) {

                    case "Admin":
                        checkAdminLoginData();
                        break;
                    case "Customer":
                        checkLoginData();
                        break;

                }

            }
        } else if (ae.getSource() == signUpButton) {

            this.setVisible(false);
            signupPage sp = new signupPage();
            sp.addWindowListener(new WindowSensor());
            sp.setVisible(true);

        }

    }

    int loginFlag;

    void checkLoginData() {
        loginFlag = 0;
        String s = null, p = null;

        String enteredusernameInfo = usernameField.getText();
        String entereduserPassInfo = passwordField.getText();
        System.out.println(p);

        try {
            DataAccess da = new DataAccess();
            String queryUsername = "SELECT * from logininfo ";
            ResultSet rsUsername = da.getData(queryUsername);

            while (rsUsername.next()) {

                if (enteredusernameInfo.equals(rsUsername.getString("Username").trim())
                        && entereduserPassInfo.equals(rsUsername.getString("Password").trim())) {

                    s = rsUsername.getString("Username").trim();
                    p = rsUsername.getString("Password").trim();

                    loginFlag += 1;
                    break;
                }
            }
            if (loginFlag == 0) {
                JOptionPane.showMessageDialog(new loginDemo(), "Incorrect Username/Password ");
            } else {
                JOptionPane.showMessageDialog(new loginDemo(), "Login Successfull");
                this.setVisible(false);
                welcomePage wp = new welcomePage();
                wp.setusernameShow(s);
                wp.getPass(p);
                wp.setVisible(true);

            }

            // System.out.println("count : " + loginFlag);
        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

    }

    void checkAdminLoginData() {
        loginFlag = 0;
        String enteredusernameInfo = usernameField.getText();
        String entereduserPassInfo = passwordField.getText();

        try {
            DataAccess da = new DataAccess();
            String queryUsername = "SELECT * from admin ";
            ResultSet rsUsername = da.getData(queryUsername);

            while (rsUsername.next()) {

                if (enteredusernameInfo.equals(rsUsername.getString("Username").trim())
                        && entereduserPassInfo.equals(rsUsername.getString("Password").trim())) {

                    loginFlag += 1;
                    break;
                }
            }
            if (loginFlag == 0) {
                JOptionPane.showMessageDialog(new loginDemo(), "Incorrect Username/Password ");
            } else {
                JOptionPane.showMessageDialog(new loginDemo(), "Login Successfull");
                this.setVisible(false);
                adminWelcomePage aw = new adminWelcomePage();
                aw.setVisible(true);

            }

            // System.out.println("count : " + loginFlag);
        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

    }

    public static void main(String args[]) {
        loginDemo login = new loginDemo();
        //login.addWindowListener(new WindowSensor());
        login.setVisible(true);
    }
}
