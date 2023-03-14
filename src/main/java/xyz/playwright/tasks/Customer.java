package xyz.playwright.tasks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import xyz.playwright.model.AccountSummary;
import xyz.playwright.model.CustomerTransaction;
import xyz.playwright.model.SortOrder;
import xyz.playwright.userInterface.CustomerHomePage;
import xyz.playwright.userInterface.CustomerTransactionPage;
import xyz.playwright.userInterface.CustomerTransactionsPage;

import java.util.ArrayList;
import java.util.List;

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
        goToTransactions(page);
        CustomerTransactionsPage customerTransactionsPage = new CustomerTransactionsPage(page);
        customerTransactionsPage.buttonReset.click();
        customerTransactionsPage.buttonBack.click();
    }

    public static void sortTransactions(Page page, SortOrder sortOrder) {
        goToTransactions(page);
        CustomerTransactionsPage customerTransactionsPage = new CustomerTransactionsPage(page);
        switch (sortOrder) {
            case ASC -> {
                customerTransactionsPage.linkDate.click();
                customerTransactionsPage.linkDate.click();
            }
            case DESC -> customerTransactionsPage.linkDate.click();
        }
    }

    public static List<CustomerTransaction> getTransactions(Page page) {
        CustomerTransactionsPage customerTransactionsPage = new CustomerTransactionsPage(page);
        List<CustomerTransaction> result = new ArrayList<>();
        List<Locator> rows = customerTransactionsPage.tableTransactionsRows.all();
        rows.subList(1, rows.size()).forEach(row -> result.add(new CustomerTransaction(row.allTextContents().get(0))));
        return result;

    }

    public static void goToTransactions(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        customerHomePage.buttonTransactions.click();
    }
}
