package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    public Locator btnCustomerLogin;
    public Locator btnBankManagerLogin;

    public LoginPage(Page page) {
        this.btnCustomerLogin = page.getByText("Customer Login");
        this.btnBankManagerLogin = page.getByText("Bank Manager Login");
    }
}
