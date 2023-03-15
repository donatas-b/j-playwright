package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class Header {
    private final Locator buttonHome;
    private final Locator buttonLogout;
    private final Locator labelHeading;

    public Header(Page page) {
        this.buttonHome = page.getByText("Home");
        this.buttonLogout = page.getByText("Logout");
        this.labelHeading = page.getByLabel("XYZ Bank");
    }
}
