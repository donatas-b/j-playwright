package xyz.playwright.model;

import com.microsoft.playwright.Locator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Customers extends ArrayList<CustomerInformation> {

    public Customers(List<Locator> rows) {
        rows.subList(1, rows.size()).forEach(row -> this.add(new CustomerInformation(row.allTextContents().get(0))));
    }

    public void sort(CustomerSortColumn column, SortOrder sortOrder) {
        switch (sortOrder) {
            case ASC -> {
                switch (column) {
                    case FIRST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getFirstName));
                    case LAST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getLastName));
                    case POST_CODE -> this.sort(Comparator.comparing(CustomerInformation::getPostCode));
                }
            }
            case DESC -> {
                switch (column) {
                    case FIRST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getFirstName).reversed());
                    case LAST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getLastName).reversed());
                    case POST_CODE -> this.sort(Comparator.comparing(CustomerInformation::getPostCode).reversed());
                }
            }
        }
    }
}
