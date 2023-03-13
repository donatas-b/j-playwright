package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.userInterface.CustomerLoginPage;
import xyz.playwright.userInterface.LoginPage;

public class Login {
    public static void asManager(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnBankManagerLogin.click();
    }

    public static void asCustomer(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnCustomerLogin.click();
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(page);
        customerLoginPage.drpYourName.selectOption("Ron Weasly");
        customerLoginPage.buttonLogin.click();
    }
}
