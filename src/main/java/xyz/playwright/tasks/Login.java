package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.userInterface.CustomerLoginPage;
import xyz.playwright.userInterface.Header;
import xyz.playwright.userInterface.LoginPage;

public class Login {
    public static void asManager(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnBankManagerLogin.click();
    }

    public static void asCustomer(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnCustomerLogin.click();
        asCustomerAgain(page);
    }

    public static void asCustomerAgain(Page page) {
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(page);
        customerLoginPage.drpYourName.selectOption("Ron Weasly");
        customerLoginPage.buttonLogin.click();
    }

    public static void logout(Page page) {
        Header header = new Header(page);
        header.buttonLogout.click();
    }
}
