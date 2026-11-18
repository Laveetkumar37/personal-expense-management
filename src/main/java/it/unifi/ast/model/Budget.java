
package it.unifi.ast.model;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Budget {

    private final BigDecimal amount;
    private final String category;
    private final YearMonth month;

    public Budget(BigDecimal amount, String category, YearMonth month) {

        if (amount == null) {
            throw new IllegalArgumentException(
                "Budget amount cannot be null"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Budget amount must be greater than zero"
            );
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException(
                "Budget category cannot be empty"
            );
        }

        if (month == null) {
            throw new IllegalArgumentException(
                "Budget month cannot be null"
            );
        }

        this.amount = amount;
        this.category = category;
        this.month = month;
    }

    // Getter methods

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public YearMonth getMonth() {
        return month;
    }
}
