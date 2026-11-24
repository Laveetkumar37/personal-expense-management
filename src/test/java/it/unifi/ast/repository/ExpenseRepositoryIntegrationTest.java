package it.unifi.ast.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import it.unifi.ast.model.Expense;
import it.unifi.ast.persistence.JpaUtil;

@Testcontainers
class ExpenseRepositoryIntegrationTest {

    @Container
    private static final MySQLContainer<?> MYSQL =
            new MySQLContainer<>("mysql:8.4")
                    .withDatabaseName("expense_test_db")
                    .withUsername("testuser")
                    .withPassword("testpass");

    private final ExpenseRepository repository = new ExpenseRepository();

    @BeforeAll
    static void configureDatabase() {
        System.setProperty("DB_URL", MYSQL.getJdbcUrl());
        System.setProperty("DB_USER", MYSQL.getUsername());
        System.setProperty("DB_PASSWORD", MYSQL.getPassword());
    }

    @AfterAll
    static void closeDatabase() {
        JpaUtil.close();
        System.clearProperty("DB_URL");
        System.clearProperty("DB_USER");
        System.clearProperty("DB_PASSWORD");
    }

    private Expense createExpense() {
        return new Expense(
                new BigDecimal("25.50"),
                "Test expense " + UUID.randomUUID(),
                LocalDate.now(),
                "Food"
        );
    }

    @Test
    void shouldSaveExpense() {
        Expense expense = repository.save(createExpense());

        assertNotNull(expense.getId());
    }

    @Test
    void shouldFindExpenseById() {
        Expense saved = repository.save(createExpense());

        var result = repository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getId(), result.get().getId());
        assertEquals(saved.getDescription(), result.get().getDescription());
    }

    @Test
    void shouldFindAllExpenses() {
        Expense saved = repository.save(createExpense());

        var expenses = repository.findAll();

        assertTrue(expenses.stream()
                .anyMatch(expense -> expense.getId().equals(saved.getId())));
    }

    @Test
    void shouldDeleteExpense() {
        Expense saved = repository.save(createExpense());

        boolean deleted = repository.deleteById(saved.getId());

        assertTrue(deleted);
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}
