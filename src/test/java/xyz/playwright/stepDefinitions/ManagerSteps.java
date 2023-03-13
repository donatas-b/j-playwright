package xyz.playwright.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import xyz.playwright.model.Currency;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Manager;
import xyz.playwright.tasks.Navigate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ManagerSteps {
    private final ScenarioContext context;
    private CustomerInformation currentCustomer;
    private String createdCustomerAccountNumber;

    public ManagerSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("Manager has logged in")
    public void managerHasLoggedIn() {
        Navigate.toBankPage(context.getPage());
        Login.asManager(context.getPage());
    }

    @When("he enters new Customer data")
    public void heEntersNewCustomerData() {
        Navigate.toAddCustomer(context.getPage());
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(context.getPage(), currentCustomer);
    }

    @And("he tries to save it")
    public void heTriesToSaveIt() {
        Manager.addCustomer(context.getPage());
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        assertTrue("Customer fields were not cleared", Manager.areCustomerFieldsCleared(context.getPage()));
    }

    @And("Customer appears in Customer List")
    @And("Customer should appear in Customer List")
    public void customerShouldAppearInCustomerList() {
        Navigate.toCustomers(context.getPage());
        assertTrue(String.format("Customer '%s' is not in the list", currentCustomer.toStringShort()),
                Manager.isCustomerInTheList(context.getPage(), currentCustomer));
    }

    @Given("there is a Customer")
    public void thereIsACustomer() {
        Navigate.toAddCustomer(context.getPage());
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(context.getPage(), currentCustomer);
        Manager.addCustomer(context.getPage());
    }

    @When("Manager opens {string} Account for Customer")
    public void managerOpensAccountForCustomer(String currency) {
        Navigate.toOpenAccount(context.getPage());
        String alertMessage = Manager.openCustomerAccount(context.getPage(), currentCustomer, Currency.byValue(currency));
        createdCustomerAccountNumber = alertMessage.substring(alertMessage.indexOf(":") + 1);
        currentCustomer.addAccount(createdCustomerAccountNumber);
        assertTrue(alertMessage.contains("Account created successfully with account Number"));
    }

    @Then("Customer Account should appear in Customer List")
    public void customerAccountShouldAppearInCustomerList() {
        Navigate.toCustomers(context.getPage());
        assertTrue(String.format("Customer '%s' is not in the list or it does not has account '%s'", currentCustomer.toStringShort(), createdCustomerAccountNumber),
                Manager.isCustomerWithAccountInTheList(context.getPage(), currentCustomer, createdCustomerAccountNumber));
    }

    @When("Manager does Search for Customer")
    public void managerDoesSearchForCustomer() {
        Navigate.toCustomers(context.getPage());
        Manager.searchCustomers(context.getPage(), currentCustomer);
    }

    @And("Customer List should contain {int} Customer")
    public void customerListShouldContainCustomer(int expectedCustomerCount) {
        int actualCustomerCount = Manager.customerCount(context.getPage());
        log.info("Actual Customer count: {}", actualCustomerCount);
        assertEquals(String.format("Expected Customer count %s but was %s", expectedCustomerCount, actualCustomerCount), expectedCustomerCount, actualCustomerCount);
    }

    @When("Manager deletes the Customer")
    public void managerDeletesTheCustomer() {
        Navigate.toCustomers(context.getPage());
        Manager.deleteCustomer(context.getPage(), currentCustomer);
    }

    @Then("Customer should no longer appear in Customer List")
    public void customerShouldNoLongerAppearInCustomerList() {
        Manager.clearCustomerSearch(context.getPage());
        assertFalse(String.format("Customer '%s' was not deleted", currentCustomer.toStringShort()),
                Manager.isCustomerInTheList(context.getPage(), currentCustomer));
    }
}
