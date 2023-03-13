package xyz.playwright.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Given("Customer deposits {int} {string} into his {string} account")
    public void customerDepositsIntoHisAccount(Integer amount, String currency, String accountNumber) {
        Customer.selectAccount(context.getPage(), accountNumber);
        Customer.deposit(context.getPage(), amount);
    }

    @Then("he should see success message {string}")
    public void heShouldSeeSuccessMessage(String message) {
        String actualMessage = Customer.getSuccessMessage(context.getPage());
        assertEquals(message, actualMessage);
    }

    @And("he logs out")
    public void heLogsOut() {
        Login.logout(context.getPage());

    }

    @And("he logs in again")
    public void heLogsInAgain() {
        Login.asCustomerAgain(context.getPage());
    }

    @When("Customer withdraws {int} {string} from his {string} account")
    public void customerWithdrawsFromHisAccount(Integer amount, String currency, String accountNumber) {
        Customer.selectAccount(context.getPage(), accountNumber);
        Customer.withdraw(context.getPage(), amount);
    }
}
