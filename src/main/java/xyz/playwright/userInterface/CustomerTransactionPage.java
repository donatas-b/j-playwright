package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CustomerTransactionPage {
    public Locator inputAmount;
    public Locator buttonTransaction;

    public CustomerTransactionPage(Page page) {
        this.inputAmount = page.locator("//input[contains(@ng-model, 'amount')]");
        this.buttonTransaction = page.locator("//button[contains(@class, 'btn-default')]");

    }
}
