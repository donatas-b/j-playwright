package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CustomersPage {
    public Locator inputSearchCustomers;
    public Locator tableCustomers;
    public Locator tableCustomersRows;
    public Locator buttonDelete;
    public Locator linkFirstName;
    public Locator linkLastName;
    public Locator linkPostCode;

    public CustomersPage(Page page) {
        this.inputSearchCustomers = page.getByPlaceholder("Search Customer");
        this.tableCustomers = page.getByRole(AriaRole.TABLE);
        this.tableCustomersRows = page.locator("tr");
        this.buttonDelete = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
        this.linkFirstName = page.getByText("First Name");
        this.linkLastName = page.getByText("Last Name");
        this.linkPostCode = page.getByText("Post Code");
    }
}
