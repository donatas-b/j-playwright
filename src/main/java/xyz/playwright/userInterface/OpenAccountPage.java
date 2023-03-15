package xyz.playwright.userInterface;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class OpenAccountPage {
    private final Locator drpCustomer;
    private final Locator drpCurrency;
    private final Locator buttonProcess;

    public OpenAccountPage(Page page) {
        this.drpCustomer = page.locator("//select[@id='userSelect']");
        this.drpCurrency = page.locator("//select[@id='currency']");
        this.buttonProcess = page.getByText("Process");
    }

}
