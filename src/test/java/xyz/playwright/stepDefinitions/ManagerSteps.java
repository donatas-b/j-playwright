package xyz.playwright.stepDefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import xyz.playwright.model.Currency;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.model.CustomerSortColumn;
import xyz.playwright.model.SortOrder;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Manager;
import xyz.playwright.tasks.Navigate;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ManagerSteps {
    private final Page page = ScenarioContext.getPage();
    private CustomerInformation currentCustomer;
    private String createdCustomerAccountNumber;


    @Given("Manager has logged in")
    public void managerHasLoggedIn() {
        Navigate.toBankPage(page);
        Login.asManager(page);
    }

    @When("he enters new Customer data")
    public void heEntersNewCustomerData() {
        Navigate.toAddCustomer(page);
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(page, currentCustomer);
    }

    @And("he tries to save it")
    public void heTriesToSaveIt() {
        Manager.addCustomer(page);
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        assertTrue("Customer fields were not cleared", Manager.areCustomerFieldsCleared(page));
    }

    @And("Customer appears in Customer List")
    @And("Customer should appear in Customer List")
    public void customerShouldAppearInCustomerList() {
        Navigate.toCustomers(page);
        assertTrue(String.format("Customer '%s' is not in the list", currentCustomer.toStringShort()),
                Manager.isCustomerInTheList(page, currentCustomer));
    }

    @Given("there is a Customer")
    public void thereIsACustomer() {
        Navigate.toAddCustomer(page);
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(page, currentCustomer);
        Manager.addCustomer(page);
    }

    @When("Manager opens {string} Account for Customer")
    public void managerOpensAccountForCustomer(String currency) {
        Navigate.toOpenAccount(page);
        String alertMessage = Manager.openCustomerAccount(page, currentCustomer, Currency.byValue(currency));
        createdCustomerAccountNumber = alertMessage.substring(alertMessage.indexOf(":") + 1);
        currentCustomer.addAccount(createdCustomerAccountNumber);
        assertTrue(alertMessage.contains("Account created successfully with account Number"));
    }

    @Then("Customer Account should appear in Customer List")
    public void customerAccountShouldAppearInCustomerList() {
        Navigate.toCustomers(page);
        assertTrue(String.format("Customer '%s' is not in the list or it does not has account '%s'", currentCustomer.toStringShort(), createdCustomerAccountNumber),
                Manager.isCustomerWithAccountInTheList(page, currentCustomer, createdCustomerAccountNumber));
    }

    @When("Manager does Search for Customer")
    public void managerDoesSearchForCustomer() {
        Navigate.toCustomers(page);
        Manager.searchCustomers(page, currentCustomer);
    }

    @And("Customer List should contain {int} Customer")
    public void customerListShouldContainCustomer(int expectedCustomerCount) {
        int actualCustomerCount = Manager.customerCount(page);
        log.info("Actual Customer count: {}", actualCustomerCount);
        assertEquals(String.format("Expected Customer count %s but was %s", expectedCustomerCount, actualCustomerCount), expectedCustomerCount, actualCustomerCount);
    }

    @When("Manager deletes the Customer")
    public void managerDeletesTheCustomer() {
        Navigate.toCustomers(page);
        Manager.deleteCustomer(page, currentCustomer);
    }

    @Then("Customer should no longer appear in Customer List")
    public void customerShouldNoLongerAppearInCustomerList() {
        Manager.clearCustomerSearch(page);
        assertFalse(String.format("Customer '%s' was not deleted", currentCustomer.toStringShort()),
                Manager.isCustomerInTheList(page, currentCustomer));
    }

    @When("Manager Sorts Customer List by {string} in {string} order")
    public void managerSortsCustomerListByInOrder(String columnName, String sortOrder) {
        Navigate.toCustomers(page);
        Manager.sortCustomers(page, CustomerSortColumn.byValue(columnName), SortOrder.byValue(sortOrder));
    }

    @Then("Customer list should be sorted by {string} in {string} order")
    public void customerListShouldBeSortedByInOrder(String columnName, String sortOrder) {
        List<CustomerInformation> actualCustomerList = Manager.getCustomerList(page);
        log.info("before sort: {}", actualCustomerList);
        switch (SortOrder.byValue(sortOrder)) {
            case ASC -> {
                switch (CustomerSortColumn.byValue(columnName)) {
                    case FIRST_NAME -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getFirstName));
                    case LAST_NAME -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getLastName));
                    case POST_CODE -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getPostCode));
                }
            }
            case DESC -> {
                switch (CustomerSortColumn.byValue(columnName)) {
                    case FIRST_NAME ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getFirstName).reversed());
                    case LAST_NAME ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getLastName).reversed());
                    case POST_CODE ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getPostCode).reversed());
                }
            }
        }
        log.info("after sort: {}", actualCustomerList);

        List<String> actualCustomerListStrings = Manager.getCustomerList(page).stream().map(CustomerInformation::toString).toList();
        log.info("actualCustomerListStrings: {}", actualCustomerListStrings);
        List<String> expectedCustomerListStrings = actualCustomerList.stream().map(CustomerInformation::toString).toList();
        log.info("expectedCustomerListStrings: {}", expectedCustomerListStrings);

        assertEquals("Customer List is not sorted as expected", expectedCustomerListStrings, actualCustomerListStrings);
    }
}
