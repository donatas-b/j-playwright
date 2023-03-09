package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.userInterface.LoginPage;

public class Login {
    public static void asManager(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnBankManagerLogin.click();
    }

    public static void asCustomer(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.btnCustomerLogin.click();
    }
}
