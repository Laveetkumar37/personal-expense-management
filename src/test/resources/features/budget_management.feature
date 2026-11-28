Feature: Budget Management
  As a user
  I want to create monthly budgets
  So that I can control my spending

  Scenario: Successfully create a valid budget
    Given I have a budget amount of 300.00
    And the budget category is "Food"
    And the budget month is "2026-11"
    When I create the budget
    Then the budget should be created successfully
    And the budget amount should be 300.00

  Scenario: Reject a budget with a negative amount
    Given I have a budget amount of -50.00
    And the budget category is "Food"
    And the budget month is "2026-11"
    When I create the budget
    Then the budget should be rejected

  Scenario: Reject a budget with a zero amount
    Given I have a budget amount of 0.00
    And the budget category is "Transport"
    And the budget month is "2026-11"
    When I create the budget
    Then the budget should be rejected