package xyz.playwright.stepDefinitions;

import com.microsoft.playwright.*;
import lombok.Getter;

import java.util.Arrays;

public class ScenarioContext {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    @Getter
    private static Page page;

    public ScenarioContext() {
        init();
    }

    public static void init() {
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
                .setSlowMo(500)
        );
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();

    }

    public static void close() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
