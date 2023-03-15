package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class CustomerTransactionsPage {
    private final Locator buttonBack;
    private final Locator buttonReset;
    private final Locator tableTransactions;
    private final Locator tableTransactionsRows;
    private final Locator linkDate;

    public CustomerTransactionsPage(Page page) {
        this.buttonBack = page.getByText("Back");
        this.buttonReset = page.getByText("Reset");
        this.tableTransactions = page.getByRole(AriaRole.TABLE);
        this.tableTransactionsRows = page.locator("tr");
        this.linkDate = page.getByText("Date-Time");
    }
}
