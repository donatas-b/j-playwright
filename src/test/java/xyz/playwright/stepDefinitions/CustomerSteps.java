package xyz.playwright.stepDefinitions;

import io.cucumber.java.en.Given;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Navigate;

public class CustomerSteps {

    private final ScenarioContext context;

    public CustomerSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("Customer has logged in")
    public void customerHasLoggedIn() {
        Navigate.toBankPage(context.getPage());
        Login.asCustomer(context.getPage());
    }
}
