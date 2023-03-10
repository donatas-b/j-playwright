package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OpenAccountPage {
    public Locator drpCustomer;
    public Locator drpCurrency;
    public Locator buttonProcess;

    public OpenAccountPage(Page page) {
        this.drpCustomer = page.locator("//select[@id='userSelect']");
        this.drpCurrency = page.locator("//select[@id='currency']");
        this.buttonProcess = page.getByText("Process");
    }

}
