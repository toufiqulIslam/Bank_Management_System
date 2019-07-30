package welcomePage;

import PracticeCodes.DataAccess;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Toufiq Rich
 */
public class allTransaction implements transactionInterface {
    
    private String textUsernameShow, storePass;

    public allTransaction(String textUsernameShow, String storePass) {
        this.textUsernameShow = textUsernameShow;
        this.storePass = storePass;
    }

    public void deposit(int amount) {

        try {
 
            DataAccess da = new DataAccess();
            String queryUserInfo = "SELECT * from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsUserInfo = da.getData(queryUserInfo);
            System.out.println(textUsernameShow+" "+storePass);

            while (rsUserInfo.next()) {
                
                int id = Integer.parseInt(rsUserInfo.getString("ID").trim());
                int balance = Integer.parseInt(rsUserInfo.getString("Balance").trim());

                if (amount > 0) {

                    balance += amount;
                    String queryBalance = "UPDATE signupinfo SET Balance=" + balance + " where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
                    da.updateDB(queryBalance);
                    String queryDepositData = "INSERT INTO transaction (ID,Amount,Status) VALUES (" + id + ",'" + amount + " ','Deposit')";
                    da.updateDB(queryDepositData);
                    JOptionPane.showMessageDialog(new welcomePage(), "Deposit Successful");
                    System.out.println("\nAmmount Added Succesfully");

                } else {
                    JOptionPane.showMessageDialog(new welcomePage(), "Deposit ammount cannot be less than or equal 0");
                    System.out.println("\nAccount Balance can't be Negative");
                }

            }
        } catch (Exception ex) {
            System.out.println(ex + "dexception");
        }

    }

    public boolean withdraw(int amount) {

        try {
            DataAccess da = new DataAccess();
            String queryUserInfo = "SELECT * from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsUserInfo = da.getData(queryUserInfo);

            while (rsUserInfo.next()) {

                int id = Integer.parseInt(rsUserInfo.getString("ID").trim());
                int balance = Integer.parseInt(rsUserInfo.getString("Balance").trim());
                int limit = Integer.parseInt(rsUserInfo.getString("Overdraft").trim());
                String accType = rsUserInfo.getString("AccountType").trim();
                int check = balance - amount;

                if (check < limit && accType.equals("Overdraft")) {
                    JOptionPane.showMessageDialog(new welcomePage(), "Sorry! You have reached your OVERDRAFT LIMIT");
                    return false;
                } else if (check >= 0) {

                    String queryBalance = "UPDATE signupinfo SET Balance=" + check + " where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
                    da.updateDB(queryBalance);
                    String queryWithdrawData = "INSERT INTO transaction (ID,Amount,Status) VALUES (" + id + ",'" + amount + " ','Withdraw')";
                    da.updateDB(queryWithdrawData);
                    JOptionPane.showMessageDialog(new welcomePage(), "Withdraw Successful");
                    System.out.println("\nAmmount deducted succesfully");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(new welcomePage(), "Unsuccessful, You don't have enough balance in your Account");
                    System.out.println("\nAccount Balance can't be less than 0");
                    return false;
                }

            }
        } catch (Exception ex) {
            System.out.println(ex + "dexception");
        }
        return false;

    }

    int idFlag;

    public void transfer(int id, int amount) {
        idFlag = 0;

        try {
            DataAccess da = new DataAccess();

            String queryRecieverInfo = "SELECT * from signupinfo where ID="+ id +" ";
            String queryAllIDInfo = "SELECT ID from signupinfo";
            String queryUserIDInfo = "SELECT ID from signupinfo where Fullname='" + textUsernameShow + " ' and Password='" + storePass + " ' ";
            ResultSet rsRecieverInfo = da.getData(queryRecieverInfo);
            ResultSet rsAllIDInfo = da.getData(queryAllIDInfo);
            ResultSet rsUserIDInfo = da.getData(queryUserIDInfo);

            while (rsUserIDInfo.next()) {

                int userid = Integer.parseInt(rsUserIDInfo.getString("ID").trim());
                if (Integer.parseInt(rsUserIDInfo.getString("ID").trim()) == id) {
                    JOptionPane.showMessageDialog(new welcomePage(), "Receiver ID and USER ID can't be same");
                    break;
                } else {

                    while (rsAllIDInfo.next()) {

                        if (Integer.parseInt(rsAllIDInfo.getString("ID").trim()) == id) {
                            System.out.println(rsAllIDInfo.getString("ID").trim() + " " + id);
                            idFlag += 1;
                            break;
                        }

                    }

                    if (idFlag != 0) {

                        if (withdraw(amount)) {
                            while (rsRecieverInfo.next()) {

                                int balance = Integer.parseInt(rsRecieverInfo.getString("Balance").trim());

                                balance += amount;
                                String queryBalance = "UPDATE signupinfo SET Balance=" + balance + " where ID=" + id + " ";
                                da.updateDB(queryBalance);
                                JOptionPane.showMessageDialog(new welcomePage(), "Transfer Successful");

                                String queryReceiverData = "INSERT INTO transaction (ID,Amount,SenderID,Status) VALUES (" + id + ",'" + amount + " '," + userid + ",'Received Money')";
                                da.updateDB(queryReceiverData);
                                String querySenderData = "INSERT INTO transaction (ID,Amount,ReceiverID,Status) VALUES (" + userid + ",'" + amount + " '," + id + ",'Send Money')";
                                da.updateDB(querySenderData);
                                System.out.println("\nAmmount Added Succesfully");

                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(new welcomePage(), "Invalid ID");
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex + "exception");
        }

    }

}
