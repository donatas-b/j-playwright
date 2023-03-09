package xyz.playwright.tasks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import xyz.playwright.model.CustomerInformation;
import xyz.playwright.userInterface.AddCustomerPage;
import xyz.playwright.userInterface.CustomersPage;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Customer {
    public static void enterInformation(Page page, CustomerInformation customer) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.inputFirstName.fill(customer.getFirstName());
        addCustomerPage.inputLastName.fill(customer.getLastName());
        addCustomerPage.inputPostCode.fill(customer.getLastName());
    }

    public static void addcustomer(Page page) {
        AddCustomerPage addCustomerPage = new AddCustomerPage(page);
        addCustomerPage.btnAddCustomer.click();
    }

    public static boolean areFieldsCleared(Page page) {
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
}
