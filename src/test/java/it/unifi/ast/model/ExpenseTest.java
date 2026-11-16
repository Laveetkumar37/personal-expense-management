
package it.unifi.ast.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.Test;

public class ExpenseTest {

    // Test 1: Negative amounts must be rejected
    @Test
    public void shouldRejectNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(new BigDecimal("-10.00"), "Groceries");
        });
    }

    // Test 2: Positive amounts must be stored correctly
    @Test
    public void shouldStorePositiveAmount() {
        BigDecimal amount = new BigDecimal("25.50");

        Expense expense = new Expense(amount, "Groceries");

        assertEquals(0, amount.compareTo(expense.getAmount()));
    }

    // Test 3: Zero amounts must be rejected
    @Test
    public void shouldRejectZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(BigDecimal.ZERO, "Groceries");
        });
    }

    // Test 4: Null amounts must be rejected
    @Test
    public void shouldRejectNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(null, "Groceries");
        });
    }

    // Test 5: Empty descriptions must be rejected
    @Test
    public void shouldRejectEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(new BigDecimal("25.50"), "");
        });
    }
    

    // Test 6: Null descriptions must be rejected
    @Test
    public void shouldRejectNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(new BigDecimal("25.50"), null);
        });
    }

    // Test 7: Blank descriptions must be rejected
    @Test
    public void shouldRejectBlankDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(new BigDecimal("25.50"), "   ");
        });
    }

    // Test 8: Valid descriptions must be stored correctly
    @Test
    public void shouldStoreDescription() {
        Expense expense = new Expense(
            new BigDecimal("25.50"), "Groceries"
        );

        assertEquals("Groceries", expense.getDescription());
    }

}
