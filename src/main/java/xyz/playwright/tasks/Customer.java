package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.model.AccountSummary;
import xyz.playwright.userInterface.CustomerHomePage;
import xyz.playwright.userInterface.CustomerTransactionPage;

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
        CustomerTransactionPage customerTransactionPage = new CustomerTransactionPage(page);
        customerTransactionPage.inputAmount.fill(amount.toString());
        customerTransactionPage.buttonTransaction.click();
    }

    public static String getSuccessMessage(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        return customerHomePage.labelMessage.textContent();
    }
}
