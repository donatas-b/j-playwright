package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ManagerHomePage {
    public Locator btnAddCustomer;
    public Locator btnOpenAccount;
    public Locator btnCustomers;

    public ManagerHomePage(Page page) {
        this.btnAddCustomer = page.getByText("Add Customer");
        this.btnOpenAccount = page.getByText("Open Account");
        this.btnCustomers = page.getByText("Customers");
    }
}
