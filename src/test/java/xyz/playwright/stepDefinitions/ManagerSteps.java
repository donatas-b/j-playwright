package xyz.playwright.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.tasks.Customer;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Navigate;

import static org.junit.Assert.assertTrue;

public class ManagerSteps {
    private final ScenarioContext context;
    private CustomerInformation currentCustomer;

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
        Customer.enterInformation(context.getPage(), currentCustomer);
    }

    @And("he tries to save it")
    public void heTriesToSaveIt() {
        Customer.addcustomer(context.getPage());
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        assertTrue("Customer fields were not cleared", Customer.areFieldsCleared(context.getPage()));
    }

    @And("Customer should appear in Customer List")
    public void customerShouldAppearInCustomerList() {
        Navigate.toCustomers(context.getPage());
        assertTrue(String.format("Customer '%s' is not in the list", currentCustomer.toStringShort()), Customer.isCustomerInTheList(context.getPage(), currentCustomer));
    }
}
