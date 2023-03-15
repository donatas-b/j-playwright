package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class CustomersPage {
    private final Locator inputSearchCustomers;
    private final Locator tableCustomers;
    private final Locator tableCustomersRows;
    private final Locator buttonDelete;
    private final Locator linkFirstName;
    private final Locator linkLastName;
    private final Locator linkPostCode;

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
