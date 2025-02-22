import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int pin;
    private double balance;
    
    public Account(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }
    
    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private Map<Integer, Account> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    
    public ATM() {
        accounts.put(12345, new Account(1234, 5000)); // Sample account
        accounts.put(67890, new Account(5678, 3000));
    }
    
    public void start() {
        System.out.println("Do you have an account? (yes/no)");
        String response = scanner.next();
        
        if (response.equalsIgnoreCase("no")) {
            createAccount();
        } else {
            accessAccount();
        }
    }
    
    private void createAccount() {
        System.out.print("Enter new account number: ");
        int accountNumber = scanner.nextInt();
        
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }
        
        System.out.print("Set a PIN: ");
        int pin = scanner.nextInt();
        
        accounts.put(accountNumber, new Account(pin, 0));
        System.out.println("Account created successfully!");
        
        accessAccount();
    }
    
    private void accessAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            
            Account account = accounts.get(accountNumber);
            if (account.validatePin(pin)) {
                showMenu(account);
            } else {
                System.out.println("Invalid PIN. Exiting...");
            }
        } else {
            System.out.println("Account not found. Exiting...");
        }
    }
    
    private void showMenu(Account account) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}