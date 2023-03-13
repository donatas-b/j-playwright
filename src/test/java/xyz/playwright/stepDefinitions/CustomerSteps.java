package xyz.playwright.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import xyz.playwright.model.AccountSummary;
import xyz.playwright.model.Currency;
import xyz.playwright.tasks.Customer;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Navigate;

import static org.junit.Assert.assertEquals;

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

    @Then("his {string} Account Summary should have {int} {string}")
    public void hisAccountSummaryShouldHave(String accountNumber, int balance, String currency) {
        AccountSummary expectedSummary = AccountSummary.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .currency(Currency.byValue(currency))
                .build();
        AccountSummary actualSummary = Customer.getSummary(context.getPage());
        assertEquals("Customer summary is not as expected", expectedSummary.toString(), actualSummary.toString());
    }
}
