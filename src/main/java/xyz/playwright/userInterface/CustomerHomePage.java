package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class CustomerHomePage {
    private final Locator lblCustomerName;
    private final Locator drpAccounts;
    private final Locator textAccountSummary;
    private final Locator buttonTransactions;
    private final Locator buttonDeposit;
    private final Locator buttonWithdrawal;
    private final Locator labelMessage;

    public CustomerHomePage(Page page) {
        this.lblCustomerName = page.locator("//span[contains(@class, 'fontBig')]");
        this.drpAccounts = page.locator("//select[@id='accountSelect']");
        this.textAccountSummary = page.locator("//div[contains(@class, 'center')]", new Page.LocatorOptions().setHasText("Account Number"));
        this.buttonTransactions = page.getByText("Transactions");
        this.buttonDeposit = page.getByText("Deposit");
        this.buttonWithdrawal = page.getByText("Withdrawl");
        this.labelMessage = page.locator("//span[contains(@class, 'error')]");
    }

}
