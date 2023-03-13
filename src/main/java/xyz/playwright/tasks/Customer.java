package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.model.AccountSummary;
import xyz.playwright.userInterface.CustomerHomePage;

public class Customer {
    public static AccountSummary getSummary(Page page) {
        CustomerHomePage customerHomePage = new CustomerHomePage(page);
        return new AccountSummary(customerHomePage.textAccountSummary.innerText());
    }
}
