package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.userInterface.CustomerLoginPage;
import xyz.playwright.userInterface.Header;
import xyz.playwright.userInterface.LoginPage;

public class Login {
    public static void asManager(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.getBtnBankManagerLogin().click();
    }

    public static void asCustomer(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.getBtnCustomerLogin().click();
        asCustomerAgain(page);
    }

    public static void asCustomerAgain(Page page) {
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(page);
        customerLoginPage.getDrpYourName().selectOption("Ron Weasly");
        customerLoginPage.getButtonLogin().click();
    }

    public static void logout(Page page) {
        Header header = new Header(page);
        header.getButtonLogout().click();
    }
}
