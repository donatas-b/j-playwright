package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CustomerTransactionsPage {
    public Locator buttonBack;
    public Locator buttonReset;
    public Locator tableTransactions;
    public Locator tableTransactionsRows;
    public Locator linkDate;

    public CustomerTransactionsPage(Page page) {
        this.buttonBack = page.getByText("Back");
        this.buttonReset = page.getByText("Reset");
        this.tableTransactions = page.getByRole(AriaRole.TABLE);
        this.tableTransactionsRows = page.locator("tr");
        this.linkDate = page.getByText("Date-Time");
    }
}
