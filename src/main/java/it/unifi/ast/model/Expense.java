
package it.unifi.ast.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private final BigDecimal amount;
    private final String description;
    private final LocalDate date;
    private final String category;

    // Existing constructor: uses today's date and default category
    public Expense(BigDecimal amount, String description) {
        this(amount, description, LocalDate.now());
    }

    // Existing constructor: uses default category
    public Expense(BigDecimal amount, String description, LocalDate date) {
        this(amount, description, date, "Uncategorized");
    }

    // Constructor with an explicitly supplied category
    public Expense(BigDecimal amount, String description,
                   LocalDate date, String category) {

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

        if (category == null) {
            throw new IllegalArgumentException(
                "Expense category cannot be null"
            );
        }

        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
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

    public String getCategory() {
        return category;
    }
}
