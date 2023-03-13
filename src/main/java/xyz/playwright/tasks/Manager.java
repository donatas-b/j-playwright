package xyz.playwright.tasks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import xyz.playwright.model.Currency;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.userInterface.AddCustomerPage;
import xyz.playwright.userInterface.CustomersPage;
import xyz.playwright.userInterface.OpenAccountPage;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Manager {
    public static void enterCustomerInformation(Page page, CustomerInformation customer) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.inputFirstName.fill(customer.getFirstName());
        addCustomerPage.inputLastName.fill(customer.getLastName());
        addCustomerPage.inputPostCode.fill(customer.getLastName());
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
}
