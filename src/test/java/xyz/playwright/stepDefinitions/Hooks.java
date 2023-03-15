package xyz.playwright.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
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
            ScenarioContext.takeScreenshot(scenario.getName());
        }
        ScenarioContext.close();
    }

    @AfterStep
    public void afterStep() {
        ScenarioContext.takeScreenshot("afterStep");
    }

}
