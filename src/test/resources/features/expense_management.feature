Feature: Expense Management
  As a user
  I want to manage my expenses
  So that I can track my spending

  Scenario: Successfully add a valid expense
    Given I have an expense with description "Lunch"
    And the expense amount is 15.50
    And the expense category is "Food"
    And the expense date is "2026-11-28"
    When I create the expense
    Then the expense should be created successfully
    And the expense amount should be 15.50

  Scenario: Reject an expense with a negative amount
    Given I have an expense with description "Shopping"
    And the expense amount is -20.00
    And the expense category is "Shopping"
    And the expense date is "2026-11-28"
    When I create the expense
    Then the expense should be rejected

  Scenario: Reject an expense with a blank description
    Given I have an expense with description ""
    And the expense amount is 10.00
    And the expense category is "Food"
    And the expense date is "2026-11-28"
    When I create the expense
    Then the expense should be rejected