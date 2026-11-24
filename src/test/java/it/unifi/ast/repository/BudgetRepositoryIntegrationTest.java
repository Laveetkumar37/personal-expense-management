
package it.unifi.ast.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import it.unifi.ast.model.Budget;
import it.unifi.ast.persistence.JpaUtil;

@Testcontainers
class BudgetRepositoryIntegrationTest {

    @Container
    private static final MySQLContainer<?> MYSQL =
            new MySQLContainer<>("mysql:8.4")
                    .withDatabaseName("budget_test_db")
                    .withUsername("testuser")
                    .withPassword("testpass");

    private final BudgetRepository repository = new BudgetRepository();

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

    private Budget createBudget() {
        return new Budget(
                new BigDecimal("500.00"),
                "Food-" + UUID.randomUUID(),
                YearMonth.now()
        );
    }

    @Test
    void shouldSaveBudget() {
        Budget budget = repository.save(createBudget());

        assertNotNull(budget.getId());
    }

    @Test
    void shouldFindBudgetById() {
        Budget saved = repository.save(createBudget());

        var result = repository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getId(), result.get().getId());
        assertEquals(saved.getAmount(), result.get().getAmount());
    }

    @Test
    void shouldFindAllBudgets() {
        Budget saved = repository.save(createBudget());

        var budgets = repository.findAll();

        assertTrue(budgets.stream()
                .anyMatch(b -> b.getId().equals(saved.getId())));
    }

    @Test
    void shouldDeleteBudget() {
        Budget saved = repository.save(createBudget());

        boolean deleted = repository.deleteById(saved.getId());

        assertTrue(deleted);
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}
