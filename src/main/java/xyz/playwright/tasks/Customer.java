package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.model.AccountSummary;
import xyz.playwright.userInterface.CustomerHomePage;
import xyz.playwright.userInterface.CustomerTransactionPage;
import xyz.playwright.userInterface.CustomerTransactionsPage;

public class Customer {
    public static AccountSummary getSummary(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        return new AccountSummary(customerHomePage.textAccountSummary.innerText());
    }

    public static void selectAccount(Page page, String accountNumber) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        customerHomePage.drpAccounts.selectOption(accountNumber);
    }

    public static void deposit(Page page, Integer amount) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        customerHomePage.buttonDeposit.click();
        performTransaction(page, amount);
    }

    public static String getSuccessMessage(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        return customerHomePage.labelMessage.textContent();
    }

    public static void withdraw(Page page, Integer amount) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        customerHomePage.buttonWithdrawal.click();
        performTransaction(page, amount);
    }

    private static void performTransaction(Page page, Integer amount) {
        CustomerTransactionPage customerTransactionPage = new CustomerTransactionPage(page);
        customerTransactionPage.inputAmount.fill(amount.toString());
        customerTransactionPage.buttonTransaction.click();
    }

    public static void resetAccount(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        customerHomePage.buttonTransactions.click();
        CustomerTransactionsPage customerTransactionsPage = new CustomerTransactionsPage(page);
        customerTransactionsPage.buttonReset.click();
        customerTransactionsPage.buttonBack.click();
    }
}
