package xyz.playwright.stepDefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.en.Given;
import xyz.playwright.userInterface.LoginPage;

import java.util.Arrays;

public class ManagerSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginPage loginPage;

    @Given("Manager has logged in")
    public void managerHasLoggedIn() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized", "--enable-automation", "--no-sandbox", "--disable-popup-blocking", "--disable-default-apps", "--disable-infobars", "--disable-gpu", "--disable-extensions"))
                .setSlowMo(1000));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
        page.navigate("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        loginPage = new LoginPage(page);
        loginPage.asManager();
    }
}
