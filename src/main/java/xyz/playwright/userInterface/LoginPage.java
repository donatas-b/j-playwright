package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class LoginPage {
    private final Locator btnCustomerLogin;
    private final Locator btnBankManagerLogin;

    public LoginPage(Page page) {
        this.btnCustomerLogin = page.getByText("Customer Login");
        this.btnBankManagerLogin = page.getByText("Bank Manager Login");
    }
}
