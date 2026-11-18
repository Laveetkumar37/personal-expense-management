
package it.unifi.ast.model;

import static org.junit.Assert.assertThrows;
import java.math.BigDecimal;
import java.time.YearMonth;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BudgetTest {

    // Test 14: Null budget amounts must be rejected
    @Test
    public void shouldRejectNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                null,
                "Food",
                YearMonth.of(2026, 11)
            );
        });
    }
    
 // Test 15: Negative budget amounts must be rejected
    @Test
    public void shouldRejectNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                new BigDecimal("-100.00"),
                "Food",
                YearMonth.of(2026, 11)
            );
        });
    }
    
 // Test 16: Zero budget amounts must be rejected
    @Test
    public void shouldRejectZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                BigDecimal.ZERO,
                "Food",
                YearMonth.of(2026, 11)
            );
        });
    }
    
 // Test 17: Null budget categories must be rejected
    @Test
    public void shouldRejectNullCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                new BigDecimal("200.00"),
                null,
                YearMonth.of(2026, 11)
            );
        });
    }
    
 // Test 18: Null budget months must be rejected
    @Test
    public void shouldRejectNullMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                new BigDecimal("200.00"),
                "Food",
                null
            );
        });
    }
    
 // Test 19: Empty budget categories must be rejected
    @Test
    public void shouldRejectEmptyCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                new BigDecimal("200.00"),
                "",
                YearMonth.of(2026, 11)
            );
        });
    }
    
 // Test 20: Valid budget information must be stored correctly
    @Test
    public void shouldStoreBudgetDetails() {
        BigDecimal amount = new BigDecimal("200.00");
        YearMonth month = YearMonth.of(2026, 11);

        Budget budget = new Budget(amount, "Food", month);

        assertEquals(0, amount.compareTo(budget.getAmount()));
        assertEquals("Food", budget.getCategory());
        assertEquals(month, budget.getMonth());
    }
    
 // Test 21: Whitespace-only categories must be rejected
    @Test
    public void shouldRejectBlankCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Budget(
                new BigDecimal("200.00"),
                "   ",
                YearMonth.of(2026, 11)
            );
        });
    }
}
