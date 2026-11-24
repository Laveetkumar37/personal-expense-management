
package it.unifi.ast.model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Convert(converter = YearMonthConverter.class)
    @Column(name = "budget_month", nullable = false, length = 7)
    private YearMonth month;

    // Required by JPA
    protected Budget() {
    }

    public Budget(BigDecimal amount, String category, YearMonth month) {

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Budget amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Budget amount must be greater than zero");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException(
                    "Budget category cannot be empty");
        }

        if (month == null) {
            throw new IllegalArgumentException(
                    "Budget month cannot be null");
        }

        this.amount = amount;
        this.category = new Category(category);
        this.month = month;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category.getName();
    }

    public Category getCategoryEntity() {
        return category;
    }

    public void setCategoryEntity(Category category) {
        if (category == null) {
            throw new IllegalArgumentException(
                    "Category cannot be null");
        }
        this.category = category;
    }

    public YearMonth getMonth() {
        return month;
    }

    public BigDecimal calculateSpent(List<Expense> expenses) {

        if (expenses == null) {
            throw new IllegalArgumentException(
                    "Expense list cannot be null");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Expense expense : expenses) {

            if (expense != null
                    && category.getName().equals(expense.getCategory())
                    && YearMonth.from(expense.getDate()).equals(month)) {

                total = total.add(expense.getAmount());
            }
        }

        return total;
    }

    public BigDecimal calculateRemaining(List<Expense> expenses) {
        return amount.subtract(calculateSpent(expenses));
    }
}
