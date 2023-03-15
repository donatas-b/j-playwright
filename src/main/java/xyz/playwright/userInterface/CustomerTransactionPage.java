package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class CustomerTransactionPage {
    private final Locator inputAmount;
    private final Locator buttonTransaction;

    public CustomerTransactionPage(Page page) {
        this.inputAmount = page.locator("//input[contains(@ng-model, 'amount')]");
        this.buttonTransaction = page.locator("//button[contains(@class, 'btn-default')]");

    }
}
