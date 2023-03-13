package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CustomerLoginPage {
    public Locator drpYourName;
    public Locator buttonLogin;

    public CustomerLoginPage(Page page) {
        this.drpYourName = page.locator("//select[@id='userSelect']");
        this.buttonLogin = page.getByText("Login");
    }
}
