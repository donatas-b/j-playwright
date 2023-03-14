package xyz.playwright.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before(order = 1)
    public void before() {
        ScenarioContext.init();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ScenarioContext.getPage().screenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        ScenarioContext.close();
    }

}
