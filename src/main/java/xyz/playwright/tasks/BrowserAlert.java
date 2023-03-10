package xyz.playwright.tasks;

import com.microsoft.playwright.Page;

public class BrowserAlert {

    public static String accept(Page page) {
        String[] result = {null};
        page.onDialog(dialog -> {
            result[0] = dialog.message();
            dialog.accept();
        });
        return result[0];
    }
}
