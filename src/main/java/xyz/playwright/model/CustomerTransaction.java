package xyz.playwright.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class CustomerTransaction {
    private String dateTime;
    private Integer amount;
    private TransactionType transactionType;

    public CustomerTransaction(Map<Object, String> tableRow) {
        this.dateTime = tableRow.get(1);
        this.amount = Integer.valueOf(tableRow.get(2));
        this.transactionType = TransactionType.byValue(tableRow.get(3));
    }

    public CustomerTransaction(String tableRow) {
        List<String> cells = Arrays.asList(tableRow.split("\n"));
        this.dateTime = cells.get(1).trim();
        this.amount = Integer.valueOf(cells.get(2).trim());
        this.transactionType = TransactionType.byValue(cells.get(3).trim());
    }

    public String toStringNoDate() {
        return String.format("CustomerTransaction(amount=%s, transactionType=%s)", this.amount, this.transactionType);
    }
}
