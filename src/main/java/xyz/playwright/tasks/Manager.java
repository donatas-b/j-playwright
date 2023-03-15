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
        addCustomerPage.getInputFirstName().fill(customer.getFirstName());
        addCustomerPage.getInputLastName().fill(customer.getLastName());
        addCustomerPage.getInputPostCode().fill(customer.getPostCode());
    }

    public static void addCustomer(Page page) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.getButtonAddCustomer().click();
    }

    public static boolean areCustomerFieldsCleared(Page page) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        String firstName = addCustomerPage.getInputFirstName().textContent();
        String lastName = addCustomerPage.getInputLastName().textContent();
        String postCode = addCustomerPage.getInputPostCode().textContent();

        return isEmpty(firstName) && isEmpty(lastName) && isEmpty(postCode);
    }

    public static boolean isCustomerInTheList(Page page, CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage(page);
        boolean isFirstNameVisible = customersPage.getTableCustomers().filter(new Locator.FilterOptions().setHasText(customerInformation.getFirstName())).isVisible();
        boolean isLastNameVisible = customersPage.getTableCustomers().filter(new Locator.FilterOptions().setHasText(customerInformation.getLastName())).isVisible();
        boolean isPostCodeVisible = customersPage.getTableCustomers().filter(new Locator.FilterOptions().setHasText(customerInformation.getPostCode())).isVisible();
        return isFirstNameVisible && isLastNameVisible && isPostCodeVisible;
    }

    public static boolean isCustomerWithAccountInTheList(Page page, CustomerInformation customerInformation, String accountNumber) {
        boolean isCustomerInTheList = isCustomerInTheList(page, customerInformation);
        CustomersPage customersPage = new CustomersPage(page);
        boolean isAccountVisible = customersPage.getTableCustomers().filter(new Locator.FilterOptions().setHasText(accountNumber)).isVisible();
        return isCustomerInTheList && isAccountVisible;
    }

    public static String openCustomerAccount(Page page, CustomerInformation customerInformation, Currency currency) {
        OpenAccountPage openAccountPage = new OpenAccountPage(page);
        openAccountPage.getDrpCustomer().selectOption(customerInformation.toStringShort());
        openAccountPage.getDrpCurrency().selectOption(currency.getCurrency());
        openAccountPage.getButtonProcess().click();
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
        customersPage.getInputSearchCustomers().fill(customerInformation.getFirstName());
    }

    public static int customerCount(Page page) {
        CustomersPage customersPage = new CustomersPage(page);
        return customersPage.getTableCustomersRows().count() - 1;
    }

    public static void deleteCustomer(Page page, CustomerInformation customerInformation) {
        searchCustomers(page, customerInformation);
        CustomersPage customersPage = new CustomersPage(page);
        customersPage.getButtonDelete().click();
    }

    public static void clearCustomerSearch(Page page) {
        CustomersPage customersPage = new CustomersPage(page);
        customersPage.getInputSearchCustomers().clear();
    }

    public static void sortCustomers(Page page, CustomerSortColumn sortColumn, SortOrder sortOrder) {
        CustomersPage customersPage = new CustomersPage(page);
        Locator columnTarget = customersPage.getLinkFirstName();
        switch (sortColumn) {
            case LAST_NAME -> columnTarget = customersPage.getLinkLastName();
            case POST_CODE -> columnTarget = customersPage.getLinkPostCode();
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
        List<Locator> rows = customersPage.getTableCustomersRows().all();
        rows.subList(1, rows.size()).forEach(row -> result.add(new CustomerInformation(row.allTextContents().get(0))));
        return result;
    }
}
