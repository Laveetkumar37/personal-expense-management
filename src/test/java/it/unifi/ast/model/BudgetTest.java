
package it.unifi.ast.model;

import static org.junit.Assert.assertThrows;
import java.math.BigDecimal;
import java.time.YearMonth;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
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
    

 // Test 22: Calculate expenses matching the budget category and month
 @Test
 public void shouldCalculateSpentForMatchingCategoryAndMonth() {

     Budget budget = new Budget(
         new BigDecimal("200.00"),
         "Food",
         YearMonth.of(2026, 11)
     );

     List<Expense> expenses = Arrays.asList(
         new Expense(
             new BigDecimal("50.00"),
             "Groceries",
             LocalDate.of(2026, 11, 5),
             "Food"
         ),
         new Expense(
             new BigDecimal("30.00"),
             "Restaurant",
             LocalDate.of(2026, 11, 10),
             "Food"
         ),
         new Expense(
             new BigDecimal("20.00"),
             "Bus ticket",
             LocalDate.of(2026, 11, 12),
             "Transport"
         ),
         new Expense(
             new BigDecimal("15.00"),
             "Groceries",
             LocalDate.of(2026, 12, 2),
             "Food"
         )
     );

     assertEquals(
         0,
         new BigDecimal("80.00").compareTo(
             budget.calculateSpent(expenses)
         )
     );
 }
 
//Test 23: Return zero when no expenses match the budget
@Test
public void shouldReturnZeroWhenNoExpensesMatch() {

  Budget budget = new Budget(
      new BigDecimal("200.00"),
      "Food",
      YearMonth.of(2026, 11)
  );

  List<Expense> expenses = Arrays.asList(
      new Expense(
          new BigDecimal("30.00"),
          "Bus ticket",
          LocalDate.of(2026, 11, 10),
          "Transport"
      ),
      new Expense(
          new BigDecimal("40.00"),
          "Groceries",
          LocalDate.of(2026, 12, 5),
          "Food"
      )
  );

  assertEquals(
      0,
      BigDecimal.ZERO.compareTo(budget.calculateSpent(expenses))
  );
}

//Test 24: Empty expense list must return zero
@Test
public void shouldReturnZeroForEmptyExpenseList() {

 Budget budget = new Budget(
     new BigDecimal("200.00"),
     "Food",
     YearMonth.of(2026, 11)
 );

 List<Expense> expenses = Arrays.asList();

 assertEquals(
     0,
     BigDecimal.ZERO.compareTo(budget.calculateSpent(expenses))
 );
}

//Test 25: Calculate remaining budget
@Test
public void shouldCalculateRemainingBudget() {

 Budget budget = new Budget(
     new BigDecimal("200.00"),
     "Food",
     YearMonth.of(2026, 11)
 );

 List<Expense> expenses = Arrays.asList(
     new Expense(
         new BigDecimal("50.00"),
         "Groceries",
         LocalDate.of(2026, 11, 5),
         "Food"
     ),
     new Expense(
         new BigDecimal("30.00"),
         "Restaurant",
         LocalDate.of(2026, 11, 10),
         "Food"
     )
 );

 assertEquals(
     0,
     new BigDecimal("120.00").compareTo(
         budget.calculateRemaining(expenses)
     )
 );
}


//Test 26: Overspending must produce a negative remaining budget
@Test
public void shouldReturnNegativeRemainingBudgetWhenOverspent() {

 Budget budget = new Budget(
     new BigDecimal("200.00"),
     "Food",
     YearMonth.of(2026, 11)
 );

 List<Expense> expenses = Arrays.asList(
     new Expense(
         new BigDecimal("150.00"),
         "Groceries",
         LocalDate.of(2026, 11, 5),
         "Food"
     ),
     new Expense(
         new BigDecimal("100.00"),
         "Restaurant",
         LocalDate.of(2026, 11, 10),
         "Food"
     )
 );

 assertEquals(
     0,
     new BigDecimal("-50.00").compareTo(
         budget.calculateRemaining(expenses)
     )
 );
}

//Test 27: Null expense lists must be rejected
@Test
public void shouldRejectNullExpenseList() {
 assertThrows(IllegalArgumentException.class, () -> {
     Budget budget = new Budget(
         new BigDecimal("200.00"),
         "Food",
         YearMonth.of(2026, 11)
     );

     budget.calculateSpent(null);
 });
}
}
