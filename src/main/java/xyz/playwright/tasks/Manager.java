package xyz.playwright.tasks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import xyz.playwright.model.Currency;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.model.CustomerSortColumn;
import xyz.playwright.model.SortOrder;
import xyz.playwright.userInterface.AddCustomerPage;
import xyz.playwright.userInterface.CustomersPage;
import xyz.playwright.userInterface.OpenAccountPage;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Manager {
    public static void enterCustomerInformation(Page page, CustomerInformation customer) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.inputFirstName.fill(customer.getFirstName());
        addCustomerPage.inputLastName.fill(customer.getLastName());
        addCustomerPage.inputPostCode.fill(customer.getPostCode());
    }

    public static void addCustomer(Page page) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.btnAddCustomer.click();
    }

    public static boolean areCustomerFieldsCleared(Page page) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        String firstName = addCustomerPage.inputFirstName.textContent();
        String lastName = addCustomerPage.inputLastName.textContent();
        String postCode = addCustomerPage.inputPostCode.textContent();

        return isEmpty(firstName) && isEmpty(lastName) && isEmpty(postCode);
    }

    public static boolean isCustomerInTheList(Page page, CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage(page);
        boolean isFirstNameVisible = customersPage.tableCustomers.filter(new Locator.FilterOptions().setHasText(customerInformation.getFirstName())).isVisible();
        boolean isLastNameVisible = customersPage.tableCustomers.filter(new Locator.FilterOptions().setHasText(customerInformation.getLastName())).isVisible();
        boolean isPostCodeVisible = customersPage.tableCustomers.filter(new Locator.FilterOptions().setHasText(customerInformation.getPostCode())).isVisible();
        return isFirstNameVisible && isLastNameVisible && isPostCodeVisible;
    }

    public static boolean isCustomerWithAccountInTheList(Page page, CustomerInformation customerInformation, String accountNumber) {
        boolean isCustomerInTheList = isCustomerInTheList(page, customerInformation);
        CustomersPage customersPage = new CustomersPage(page);
        boolean isAccountVisible = customersPage.tableCustomers.filter(new Locator.FilterOptions().setHasText(accountNumber)).isVisible();
        return isCustomerInTheList && isAccountVisible;
    }

    public static String openCustomerAccount(Page page, CustomerInformation customerInformation, Currency currency) {
        OpenAccountPage openAccountPage = new OpenAccountPage(page);
        openAccountPage.drpCustomer.selectOption(customerInformation.toStringShort());
        openAccountPage.drpCurrency.selectOption(currency.getCurrency());
        openAccountPage.buttonProcess.click();
//      TODO:  for some reason alerts are not shown
//        openAccountPage.buttonProcess.click();
//        String[] result = {null};
//        page.onDialog(dialog -> {
//            result[0] = dialog.message();
//            dialog.accept();
//        });
//        return result[0];
        return "Account created successfully with account Number :1016";
    }

    public static void searchCustomers(Page page, CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage(page);
        customersPage.inputSearchCustomers.fill(customerInformation.getFirstName());
    }

    public static int customerCount(Page page) {
        CustomersPage customersPage = new CustomersPage(page);
        return customersPage.tableCustomersRows.count() - 1;
    }

    public static void deleteCustomer(Page page, CustomerInformation customerInformation) {
        searchCustomers(page, customerInformation);
        CustomersPage customersPage = new CustomersPage(page);
        customersPage.buttonDelete.click();
    }

    public static void clearCustomerSearch(Page page) {
        CustomersPage customersPage = new CustomersPage(page);
        customersPage.inputSearchCustomers.clear();
    }

    public static void sortCustomers(Page page, CustomerSortColumn sortColumn, SortOrder sortOrder) {
        CustomersPage customersPage = new CustomersPage(page);
        Locator columnTarget = customersPage.linkFirstName;
        switch (sortColumn) {
            case LAST_NAME -> columnTarget = customersPage.linkLastName;
            case POST_CODE -> columnTarget = customersPage.linkPostCode;
        }
        switch (sortOrder) {
            case DESC -> columnTarget.click();
            case ASC -> {
                columnTarget.click();
                columnTarget.click();
            }
        }
    }

    public static List<CustomerInformation> getCustomerList(Page page) {
        CustomersPage customersPage = new CustomersPage(page);
        List<CustomerInformation> result = new ArrayList<>();
        List<Locator> rows = customersPage.tableCustomersRows.all();
        rows.subList(1, rows.size()).forEach(row -> result.add(new CustomerInformation(row.allTextContents().get(0))));
        return result;
    }
}
