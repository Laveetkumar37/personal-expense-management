
package it.unifi.ast.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import it.unifi.ast.model.Expense;
import it.unifi.ast.model.Budget;
import it.unifi.ast.repository.BudgetRepository;

public class BudgetPanel extends JPanel {
	private final List<Expense> expenses;
    private JTextField amountField;
    private JTextField monthField;
    private JComboBox<String> categoryComboBox;
    private JTable budgetsTable;
    private DefaultTableModel tableModel;
    private List<Budget> budgets = new ArrayList<>();
    private final BudgetRepository budgetRepository = new BudgetRepository();
    public BudgetPanel(List<Expense> expenses) {
        this.expenses = expenses;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Screen title
        JLabel titleLabel = new JLabel("Manage Budgets");
        add(titleLabel, BorderLayout.NORTH);

        // Budget form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        amountField = new JTextField();

        categoryComboBox = new JComboBox<>(new String[] {
            "Food",
            "Transport",
            "Shopping",
            "Bills",
            "Health",
            "Education",
            "Entertainment",
            "Other"
        });

        monthField = new JTextField(YearMonth.now().toString());

        JButton addBudgetButton = new JButton("Add Budget");

        formPanel.add(new JLabel("Budget Amount (€):"));
        formPanel.add(amountField);

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryComboBox);

        formPanel.add(new JLabel("Month (yyyy-MM):"));
        formPanel.add(monthField);

        formPanel.add(new JLabel(""));
        formPanel.add(addBudgetButton);

        // Budget table
        tableModel = new DefaultTableModel(
        	    new String[] {
        	        "Month",
        	        "Category",
        	        "Budget Amount (€)",
        	        "Total Spent (€)",
        	        "Remaining (€)"
        	    }, 0
        	);

        budgetsTable = new JTable(tableModel);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(budgetsTable), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        loadBudgets();
        // Add budget action
        addBudgetButton.addActionListener(e -> {

            try {

                BigDecimal amount = new BigDecimal(
                    amountField.getText().trim()
                );

                String category = (String)
                    categoryComboBox.getSelectedItem();

                YearMonth month = YearMonth.parse(
                    monthField.getText().trim()
                );

                Budget budget = new Budget(
                    amount, category, month
                );

             // Save budget in MySQL
                budgetRepository.save(budget);

                // Update the local budget list only once
                budgets.add(budget);

                // Refresh the table
                refreshBudgets();

                amountField.setText("");

                JOptionPane.showMessageDialog(
                    this,
                    "Budget added successfully!"
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid budget amount."
                );

            } catch (DateTimeParseException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter the month in yyyy-MM format."
                );

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
                );

            } catch (RuntimeException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Could not save budget: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
    public void refreshBudgets() {

        tableModel.setRowCount(0);

        for (Budget budget : budgets) {

            BigDecimal spent = budget.calculateSpent(expenses);

            BigDecimal remaining =
                    budget.calculateRemaining(expenses);

            tableModel.addRow(new Object[] {
                budget.getMonth(),
                budget.getCategory(),
                budget.getAmount(),
                spent,
                remaining
            });
        }
    }
    private void loadBudgets() {
        try {
            List<Budget> savedBudgets = budgetRepository.findAll();

            budgets.clear();
            budgets.addAll(savedBudgets);

            refreshBudgets();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not load budgets: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
