package it.unifi.ast.model;

import java.math.BigDecimal;

public class Expense {

    private final BigDecimal amount;
    private final String description;

    public Expense(BigDecimal amount, String description) {

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

        this.amount = amount;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}