package xyz.playwright.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CustomerInformation {
    private String firstName;
    private String lastName;
    private String postCode;
    private List<String> accountNumbers;

    public CustomerInformation(String tableRow) {
        List<String> cells = Arrays.asList(tableRow.split("\n"));
        this.firstName = cells.get(1).trim();
        this.lastName = cells.get(2).trim();
        this.postCode = cells.get(3).trim();
        String accounts = cells.get(4).trim();
        if (isEmpty(accounts)) {
            this.accountNumbers = null;
        } else {
            this.accountNumbers = Arrays.asList(accounts.split(" "));
        }
    }

    public static CustomerInformation random() {
        String randomString = RandomStringUtils.randomAlphanumeric(5);
        return CustomerInformation.builder()
                .firstName(String.format("FN_%s", randomString))
                .lastName(String.format("LN_%s", randomString))
                .postCode(String.format("PC_%s", randomString))
                .build();
    }

    public String toStringShort() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public void addAccount(String accountNumber) {
        if (this.accountNumbers == null) {
            this.accountNumbers = new ArrayList<>();
        }
        this.accountNumbers.add(accountNumber);
    }

    public String getAccountNumbersAsString() {
        if (this.accountNumbers != null) {
            return String.join(" ", this.accountNumbers);
        }
        return null;
    }
}
