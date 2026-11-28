
package it.unifi.ast.steps;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.unifi.ast.model.Expense;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseStepDefinitions {

    private String description;
    private BigDecimal amount;
    private String category;
    private LocalDate date;

    private Expense expense;
    private IllegalArgumentException exception;

    @Given("I have an expense with description {string}")
    public void i_have_an_expense_with_description(String description) {
        this.description = description;
    }

    @Given("the expense amount is {double}")
    public void the_expense_amount_is(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    @Given("the expense category is {string}")
    public void the_expense_category_is(String category) {
        this.category = category;
    }

    @Given("the expense date is {string}")
    public void the_expense_date_is(String date) {
        this.date = LocalDate.parse(date);
    }

    @When("I create the expense")
    public void i_create_the_expense() {
        try {
            expense = new Expense(
                    amount,
                    description,
                    date,
                    category
            );
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }
    }

    @Then("the expense should be created successfully")
    public void the_expense_should_be_created_successfully() {
        assertNotNull(expense);
        assertNull(exception);
    }

    @Then("the expense amount should be {double}")
    public void the_expense_amount_should_be(double expectedAmount) {
        assertNotNull(expense);

        assertEquals(
                0,
                expense.getAmount().compareTo(
                        BigDecimal.valueOf(expectedAmount)
                )
        );
    }

    @Then("the expense should be rejected")
    public void the_expense_should_be_rejected() {
        assertNull(expense);
        assertNotNull(exception);
    }
}
