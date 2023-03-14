package xyz.playwright.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import xyz.playwright.model.*;
import xyz.playwright.tasks.Customer;
import xyz.playwright.tasks.Login;
import xyz.playwright.tasks.Navigate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    @When("Customer Resets his {string} account")
    public void customerResetsHisAccount(String accountNumber) {
        Customer.selectAccount(context.getPage(), accountNumber);
        Customer.resetAccount(context.getPage());
    }

    @When("Customer Sorts his {string} Account Transactions by Date in {string} order")
    public void customerSortsHisAccountTransactionsByDateInOrder(String accountNumber, String sortOrder) {
        Customer.selectAccount(context.getPage(), accountNumber);
        Customer.sortTransactions(context.getPage(), SortOrder.byValue(sortOrder));
    }

    @Then("Customer Account Transactions should be sorted by Date in {string} order")
    public void customerAccountTransactionsShouldBeSortedByDateInOrder(String sortOrder) {
        List<CustomerTransaction> transactions = Customer.getTransactions(context.getPage());
        switch (SortOrder.byValue(sortOrder)) {
            case ASC -> transactions.sort(Comparator.comparing(CustomerTransaction::getDateTime));
            case DESC -> transactions.sort(Comparator.comparing(CustomerTransaction::getDateTime).reversed());
        }

        List<String> actualTransactions = Customer.getTransactions(context.getPage()).stream().map(CustomerTransaction::toString).toList();
        List<String> expectedTransactions = transactions.stream().map(CustomerTransaction::toString).toList();

        assertEquals("Transaction List is not sorted as expected", expectedTransactions, actualTransactions);
    }

    @Then("Customer {string} Account Transactions should contain following records")
    public void customerAccountTransactionsShouldContainFollowingRecords(String accountNumber, DataTable expectedRecords) {
        List<String> expectedTransactions = new ArrayList<>();
        List<Map<String, String>> rows = expectedRecords.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            expectedTransactions.add(CustomerTransaction.builder()
                    .amount(Integer.valueOf(columns.get("Amount")))
                    .transactionType(TransactionType.byValue(columns.get("TransactionType")))
                    .build()
                    .toStringNoDate());
        }
        Customer.selectAccount(context.getPage(), accountNumber);
        Customer.goToTransactions(context.getPage());

        List<String> actualTransactions = Customer.getTransactions(context.getPage()).stream().map(CustomerTransaction::toStringNoDate).toList();

        assertEquals("Transaction List is not as expected", expectedTransactions, actualTransactions);
    }
}
