package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Header {
    public Locator buttonHome;
    public Locator buttonLogout;
    public Locator labelHeading;

    public Header(Page page) {
        this.buttonHome = page.getByText("Home");
        this.buttonLogout = page.getByText("Logout");
        this.labelHeading = page.getByLabel("XYZ Bank");
    }
}
