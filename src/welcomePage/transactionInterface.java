package welcomePage;

/**
 *
 * @author Toufiq Rich
 */
public interface transactionInterface {
    
    void deposit(int amount);
    boolean withdraw(int amount);
    void transfer(int id, int amount);
    
}
