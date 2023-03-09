package xyz.playwright.stepDefinitions;

import com.microsoft.playwright.*;
import lombok.Getter;

import java.util.Arrays;

public class ScenarioContext {

    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext browserContext;
    @Getter
    private final Page page;

    public ScenarioContext() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized",
                        "--enable-automation", "--no-sandbox",
                        "--disable-popup-blocking",
                        "--disable-default-apps",
                        "--disable-infobars",
                        "--disable-gpu",
                        "--disable-extensions"))
                .setSlowMo(1000));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
    }

    public void close() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
