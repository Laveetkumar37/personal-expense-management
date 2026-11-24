
package it.unifi.ast.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(name = "expense_date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Required by JPA
    protected Expense() {
    }

    public Expense(BigDecimal amount, String description) {
        this(amount, description, LocalDate.now());
    }

    public Expense(BigDecimal amount, String description, LocalDate date) {
        this(amount, description, date, "Uncategorized");
    }

    public Expense(BigDecimal amount, String description,
                   LocalDate date, String category) {

        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Expense amount must be greater than zero");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "Expense description cannot be empty");
        }

        if (date == null) {
            throw new IllegalArgumentException(
                    "Expense date cannot be null");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException(
                    "Expense category cannot be empty");
        }

        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = new Category(category);
    }

    public Long getId() {
        return id;
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
}
