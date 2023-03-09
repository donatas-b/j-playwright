package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddCustomerPage {
    public Locator inputFirstName;
    public Locator inputLastName;
    public Locator inputPostCode;
    public Locator btnAddCustomer;

    public AddCustomerPage(Page page) {
        this.inputFirstName = page.getByPlaceholder("First Name");
        this.inputLastName = page.getByPlaceholder("Last Name");
        this.inputPostCode = page.getByPlaceholder("Post Code");
        this.btnAddCustomer = page.locator("//button[contains(@class, 'btn-default')]");
    }
}
