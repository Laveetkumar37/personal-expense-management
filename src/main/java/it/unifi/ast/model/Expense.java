
package it.unifi.ast.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private final BigDecimal amount;
    private final String description;
    private final LocalDate date;

    // Existing constructor: uses today's date by default
    public Expense(BigDecimal amount, String description) {
        this(amount, description, LocalDate.now());
    }

    // Constructor with an explicitly supplied date
    public Expense(BigDecimal amount, String description, LocalDate date) {

        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Expense amount must be greater than zero"
            );
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                "Expense description cannot be empty"
            );
        }

        if (date == null) {
            throw new IllegalArgumentException(
                "Expense date cannot be null"
            );
        }

        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
