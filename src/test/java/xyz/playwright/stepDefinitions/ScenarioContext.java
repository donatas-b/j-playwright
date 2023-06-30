package xyz.playwright.stepDefinitions;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import lombok.Getter;

import java.io.ByteArrayInputStream;
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
                .setHeadless(true)
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

    public static void takeScreenshot(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(ScenarioContext.getPage().screenshot()));
    }
}
