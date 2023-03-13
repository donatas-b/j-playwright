package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CustomerHomePage {
    public Locator lblCustomerName;
    public Locator drpAccounts;
    public Locator textAccountSummary;
    public Locator buttonTransactions;
    public Locator buttonDeposit;
    public Locator buttonWithdrawal;
    public Locator labelMessage;

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
