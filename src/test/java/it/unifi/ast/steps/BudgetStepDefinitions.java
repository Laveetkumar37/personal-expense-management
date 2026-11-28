
package it.unifi.ast.steps;

import java.math.BigDecimal;
import java.time.YearMonth;

import it.unifi.ast.model.Budget;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetStepDefinitions {

    private BigDecimal amount;
    private String category;
    private YearMonth month;

    private Budget budget;
    private IllegalArgumentException exception;

    @Given("I have a budget amount of {double}")
    public void i_have_a_budget_amount_of(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    @Given("the budget category is {string}")
    public void the_budget_category_is(String category) {
        this.category = category;
    }

    @Given("the budget month is {string}")
    public void the_budget_month_is(String month) {
        this.month = YearMonth.parse(month);
    }

    @When("I create the budget")
    public void i_create_the_budget() {
        try {
            budget = new Budget(amount, category, month);
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }
    }

    @Then("the budget should be created successfully")
    public void the_budget_should_be_created_successfully() {
        assertNotNull(budget);
        assertNull(exception);
    }

    @Then("the budget amount should be {double}")
    public void the_budget_amount_should_be(double expectedAmount) {
        assertNotNull(budget);
        assertEquals(0, budget.getAmount().compareTo(
                BigDecimal.valueOf(expectedAmount)));
    }

    @Then("the budget should be rejected")
    public void the_budget_should_be_rejected() {
        assertNull(budget);
        assertNotNull(exception);
    }
}
