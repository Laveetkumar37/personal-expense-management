
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
            new Expense(new BigDecimal("-10.00"));
        });
    }

    // Test 2: Positive amounts must be stored correctly
    @Test
    public void shouldStorePositiveAmount() {

        BigDecimal amount = new BigDecimal("25.50");

        Expense expense = new Expense(amount);

        assertEquals(0, amount.compareTo(expense.getAmount()));
    }

    // Test 3: Zero amounts must be rejected
    @Test
    public void shouldRejectZeroAmount() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(BigDecimal.ZERO);
        });
    }
    
 // Test 4: Null amounts must be rejected
    @Test
    public void shouldRejectNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expense(null);
        });
    }
}
