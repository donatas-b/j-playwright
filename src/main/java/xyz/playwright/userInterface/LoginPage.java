package xyz.playwright.userInterface;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final String BTN_CUSTOMER_LOGIN = "//button[text()='Customer Login']";
    private final String BTN_BANK_MANAGER_LOGIN = "//button[text()='Bank Manager Login']";
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void asManager() {
        page.click(BTN_BANK_MANAGER_LOGIN);
    }

    public void asCustomer() {
        page.click(BTN_CUSTOMER_LOGIN);
    }
}
