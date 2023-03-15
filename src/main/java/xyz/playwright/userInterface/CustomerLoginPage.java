package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class CustomerLoginPage {
    private final Locator drpYourName;
    private final Locator buttonLogin;

    public CustomerLoginPage(Page page) {
        this.drpYourName = page.locator("//select[@id='userSelect']");
        this.buttonLogin = page.getByText("Login");
    }
}
