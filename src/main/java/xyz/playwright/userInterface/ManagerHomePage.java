package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class ManagerHomePage {
    private final Locator btnAddCustomer;
    private final Locator btnOpenAccount;
    private final Locator btnCustomers;

    public ManagerHomePage(Page page) {
        this.btnAddCustomer = page.getByText("Add Customer");
        this.btnOpenAccount = page.getByText("Open Account");
        this.btnCustomers = page.getByText("Customers");
    }
}
