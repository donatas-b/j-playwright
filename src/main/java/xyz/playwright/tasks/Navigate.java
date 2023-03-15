package xyz.playwright.tasks;

import com.microsoft.playwright.Page;
import xyz.playwright.userInterface.ManagerHomePage;

public class Navigate {
    public static void toBankPage(Page page) {
        page.navigate("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    public static void toAddCustomer(Page page) {
        ManagerHomePage managerHomePage = new ManagerHomePage(page);
        managerHomePage.getBtnAddCustomer().click();
    }

    public static void toOpenAccount(Page page) {
        ManagerHomePage managerHomePage = new ManagerHomePage(page);
        managerHomePage.getBtnOpenAccount().click();
    }

    public static void toCustomers(Page page) {
        ManagerHomePage managerHomePage = new ManagerHomePage(page);
        managerHomePage.getBtnCustomers().click();
    }
}
