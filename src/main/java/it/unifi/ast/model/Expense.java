
package it.unifi.ast.model;

import java.math.BigDecimal;

public class Expense {

    // Stores the expense amount
    private final BigDecimal amount;

    // Constructor: creates a new expense
    public Expense(BigDecimal amount) {

        // Reject null amounts
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        // Reject zero and negative amounts
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Expense amount must be greater than zero"
            );
        }

        // Save the valid amount
        this.amount = amount;
    }

    // Returns the expense amount
    public BigDecimal getAmount() {
        return amount;
    }
}
