package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class AddCustomerPage {
    private final Locator inputFirstName;
    private final Locator inputLastName;
    private final Locator inputPostCode;
    private final Locator buttonAddCustomer;

    public AddCustomerPage(Page page) {
        this.inputFirstName = page.getByPlaceholder("First Name");
        this.inputLastName = page.getByPlaceholder("Last Name");
        this.inputPostCode = page.getByPlaceholder("Post Code");
        this.buttonAddCustomer = page.locator("//button[contains(@class, 'btn-default')]");
    }
}
