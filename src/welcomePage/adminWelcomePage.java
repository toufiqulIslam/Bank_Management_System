package welcomePage;

import Login.loginDemo;
import PracticeCodes.DataAccess;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Toufiq Rich
 */
public class adminWelcomePage extends javax.swing.JFrame implements ActionListener {

    private JTable userInfoTable;
    private JButton deleteAccount, logoutButton;
    private JTextField accIDField;
    private JLabel accIDLabel;
    ResultSet rs;
    DataAccess da;
    DefaultTableModel model = new DefaultTableModel();
    String[] columnNames = new String[]{
        "ID", "Username", "Email", "Age", "NID", "Phone", "AccountType", "Balance", "Overdraft", "Password"
    };

    public adminWelcomePage() {

        setSize(800, 700);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        setLocation((screenWidth - 500) / 2, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);

        Container content = getContentPane();
        content.setLayout(null);

        userInfoTable = new JTable();
        userInfoTable.setBounds(10, 20, 780, 470);
        userInfoTable.setFont(new java.awt.Font("Tahoma", 1, 10));
        userInfoTable.setVisible(true);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBackground(new java.awt.Color(0, 230, 64));
        deleteAccount.setFont(new java.awt.Font("Tahoma", 1, 20));
        deleteAccount.setForeground(new java.awt.Color(255, 255, 255));
        deleteAccount.setBounds(530, 500, 190, 50);
        deleteAccount.addActionListener(this);

        accIDLabel = new javax.swing.JLabel("Account ID ");
        accIDLabel.setBounds(45, 510, 120, 25);
        accIDLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        accIDLabel.setForeground(new java.awt.Color(255, 255, 255));
        accIDLabel.setVisible(true);

        accIDField = new JTextField();
        accIDField.setBounds(165, 500, 350, 50);
        accIDField.setFont(new java.awt.Font("Tahoma", 1, 18));
        accIDField.setVisible(true);

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new java.awt.Color(242, 38, 19));
        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setBounds(620, 610, 150, 50);
        logoutButton.addActionListener(this);

        content.add(userInfoTable);
        content.add(deleteAccount);
        content.add(logoutButton);
        content.add(accIDField);
        content.add(accIDLabel);

        tableInfo();

    }

    public void actionPerformed(ActionEvent ae) {
        int idFlag = 0;

        if (ae.getSource() == logoutButton) {
            loginDemo ld = new loginDemo();
            ld.addWindowListener(new WindowSensor());
            ld.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == deleteAccount) {

            if (accIDField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Enter a valid User ID");
            } else {

                try {
                    DataAccess da = new DataAccess();
                    int id = Integer.parseInt(accIDField.getText().trim());
                    String queryAllIDInfo = "SELECT ID from signupinfo";
                    ResultSet rsAllIDInfo = da.getData(queryAllIDInfo);

                    while (rsAllIDInfo.next()) {

                        if (Integer.parseInt(rsAllIDInfo.getString("ID").trim()) == id) {
                            System.out.println(rsAllIDInfo.getString("ID").trim() + " " + id);
                            idFlag += 1;
                            break;
                        }

                    }

                    if (idFlag != 0) {

                        String queryDeleteUserInfo = "DELETE from signupinfo where ID='" + accIDField.getText().trim() + "' ";
                        String queryDeleteUserInfo2 = "DELETE from logininfo where ID='" + accIDField.getText().trim() + "' ";
                        String queryDeleteUserInfo3 = "DELETE from transaction where ID='" + accIDField.getText().trim() + "' ";
                        da.updateDB(queryDeleteUserInfo2);
                        da.updateDB(queryDeleteUserInfo3);
                        da.updateDB(queryDeleteUserInfo);
                        JOptionPane.showMessageDialog(null, "User Deleted Successfully");
                        this.setVisible(false);
                        adminWelcomePage aw = new adminWelcomePage();
                        aw.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(new welcomePage(), "Invalid ID");
                    }

                } catch (Exception ex) {
                    System.out.println(ex + "dexception");
                }
            }
        }
    }

    public void tableInfo() {
        da = new DataAccess();
        model.setColumnIdentifiers(columnNames);
        String query = "select * from signupinfo";
        rs = da.getData(query);
        try {

            model.addRow(new Object[]{"ID", "Username", "Email", "Age", "NID", "Phone", "AccountType", "Balance", "Overdraft", "Password"});
            while (rs.next()) {
                String col1 = rs.getString("ID");
                String col2 = rs.getString("Fullname");
                String col3 = rs.getString("Email");
                String col4 = rs.getString("Age");
                String col5 = rs.getString("NID");
                String col6 = rs.getString("Phone");
                String col7 = rs.getString("AccountType");
                String col8 = rs.getString("Balance");
                String col9 = rs.getString("Overdraft");
                String col10 = rs.getString("Password");
                model.addRow(new Object[]{col1, col2, col3, col4, col5, col6, col7, col8, col9, col10});
            }
            userInfoTable.setModel(model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        /*adminWelcomePage aw = new adminWelcomePage();
        aw.setVisible(true);*/
    }

}
