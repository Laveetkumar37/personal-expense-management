
package it.unifi.ast.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import it.unifi.ast.model.Expense;
import java.util.ArrayList;
import java.util.List;
public class ExpensesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final List<Expense> expenses;
    private JTextField descriptionTextField;
    private JTextField amountTextField;
    private JTextField dateTextField;
    private JTable expensesTable;

    public ExpensesPanel() {
        this(new ArrayList<>());
    }

    public ExpensesPanel(List<Expense> expenses) {

        this.expenses = expenses;

        setLayout(new BorderLayout());

        // Title and form container
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel expensesTitleLabel = new JLabel("Manage Expenses");
        expensesTitleLabel.setHorizontalAlignment(
                SwingConstants.CENTER);
        expensesTitleLabel.setFont(
                new Font("Tahoma", Font.BOLD, 22));

        topPanel.add(expensesTitleLabel, BorderLayout.NORTH);

        // Expense form
        JPanel addExpenseFormPanel = new JPanel();

        GridBagLayout gbl_addExpenseFormPanel = new GridBagLayout();
        gbl_addExpenseFormPanel.columnWidths =
                new int[]{0, 0, 0};
        gbl_addExpenseFormPanel.rowHeights =
                new int[]{0, 0, 0, 0, 0, 0};
        gbl_addExpenseFormPanel.columnWeights =
                new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_addExpenseFormPanel.rowWeights =
                new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
                        Double.MIN_VALUE};

        addExpenseFormPanel.setLayout(gbl_addExpenseFormPanel);

        // Description
        JLabel descriptionLabel = new JLabel("Description:");

        GridBagConstraints gbc_descriptionLabel =
                new GridBagConstraints();
        gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
        gbc_descriptionLabel.insets =
                new Insets(0, 0, 5, 5);
        gbc_descriptionLabel.gridx = 0;
        gbc_descriptionLabel.gridy = 0;

        addExpenseFormPanel.add(
                descriptionLabel, gbc_descriptionLabel);

        descriptionTextField = new JTextField();

        GridBagConstraints gbc_descriptionTextField =
                new GridBagConstraints();
        gbc_descriptionTextField.insets =
                new Insets(0, 0, 5, 0);
        gbc_descriptionTextField.weightx = 1.0;
        gbc_descriptionTextField.fill =
                GridBagConstraints.HORIZONTAL;
        gbc_descriptionTextField.gridx = 1;
        gbc_descriptionTextField.gridy = 0;

        addExpenseFormPanel.add(
                descriptionTextField, gbc_descriptionTextField);
        descriptionTextField.setColumns(30);

        // Amount
        JLabel amountLabel = new JLabel("Amount (€):");

        GridBagConstraints gbc_amountLabel =
                new GridBagConstraints();
        gbc_amountLabel.anchor = GridBagConstraints.EAST;
        gbc_amountLabel.insets =
                new Insets(0, 0, 5, 5);
        gbc_amountLabel.gridx = 0;
        gbc_amountLabel.gridy = 1;

        addExpenseFormPanel.add(amountLabel, gbc_amountLabel);

        amountTextField = new JTextField();

        GridBagConstraints gbc_amountTextField =
                new GridBagConstraints();
        gbc_amountTextField.insets =
                new Insets(0, 0, 5, 0);
        gbc_amountTextField.weightx = 1.0;
        gbc_amountTextField.fill =
                GridBagConstraints.HORIZONTAL;
        gbc_amountTextField.gridx = 1;
        gbc_amountTextField.gridy = 1;

        addExpenseFormPanel.add(
                amountTextField, gbc_amountTextField);
        amountTextField.setColumns(30);

        // Category
        JLabel categoryLabel = new JLabel("Category:");

        GridBagConstraints gbc_categoryLabel =
                new GridBagConstraints();
        gbc_categoryLabel.anchor = GridBagConstraints.EAST;
        gbc_categoryLabel.insets =
                new Insets(0, 0, 5, 5);
        gbc_categoryLabel.gridx = 0;
        gbc_categoryLabel.gridy = 2;

        addExpenseFormPanel.add(
                categoryLabel, gbc_categoryLabel);

        JComboBox<String> categoryComboBox = new JComboBox<>();

	     // Add expense categories
	     categoryComboBox.addItem("Food");
	     categoryComboBox.addItem("Transport");
	     categoryComboBox.addItem("Shopping");
	     categoryComboBox.addItem("Bills");
	     categoryComboBox.addItem("Health");
	     categoryComboBox.addItem("Education");
	     categoryComboBox.addItem("Entertainment");
	     categoryComboBox.addItem("Other");

        GridBagConstraints gbc_categoryComboBox =
                new GridBagConstraints();
        gbc_categoryComboBox.insets =
                new Insets(0, 0, 5, 0);
        gbc_categoryComboBox.weightx = 1.0;
        gbc_categoryComboBox.fill =
                GridBagConstraints.HORIZONTAL;
        gbc_categoryComboBox.gridx = 1;
        gbc_categoryComboBox.gridy = 2;

        addExpenseFormPanel.add(
                categoryComboBox, gbc_categoryComboBox);

        // Date
        JLabel dateLabel = new JLabel("Date:");

        GridBagConstraints gbc_dateLabel =
                new GridBagConstraints();
        gbc_dateLabel.anchor = GridBagConstraints.EAST;
        gbc_dateLabel.insets =
                new Insets(0, 0, 5, 5);
        gbc_dateLabel.gridx = 0;
        gbc_dateLabel.gridy = 3;

        addExpenseFormPanel.add(dateLabel, gbc_dateLabel);

        dateTextField = new JTextField();

        GridBagConstraints gbc_dateTextField =
                new GridBagConstraints();
        gbc_dateTextField.insets =
                new Insets(0, 0, 5, 0);
        gbc_dateTextField.weightx = 1.0;
        gbc_dateTextField.fill =
                GridBagConstraints.HORIZONTAL;
        gbc_dateTextField.gridx = 1;
        gbc_dateTextField.gridy = 3;

        addExpenseFormPanel.add(
                dateTextField, gbc_dateTextField);
        dateTextField.setColumns(30);

        // Add Expense button
        JButton addExpenseButton =
                new JButton("Add Expense");

        GridBagConstraints gbc_addExpenseButton =
                new GridBagConstraints();
        gbc_addExpenseButton.anchor =
                GridBagConstraints.EAST;
        gbc_addExpenseButton.insets =
                new Insets(0, 0, 0, 5);
        gbc_addExpenseButton.gridx = 1;
        gbc_addExpenseButton.gridy = 4;

        addExpenseFormPanel.add(
                addExpenseButton, gbc_addExpenseButton);

     // Add Expense button functionality
        addExpenseButton.addActionListener(e -> {
            try {
                String description = descriptionTextField.getText().trim();
                BigDecimal amount = new BigDecimal(
                        amountTextField.getText().trim());

                String category = (String) categoryComboBox.getSelectedItem();

                LocalDate date = LocalDate.parse(
                        dateTextField.getText().trim());

                // Create Expense object
                Expense expense = new Expense(
                        amount, description, date, category);

             // Store expense in shared list
                expenses.add(expense);
                
                // Add expense to table
                DefaultTableModel model =
                        (DefaultTableModel) expensesTable.getModel();

                model.addRow(new Object[] {
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getCategory(),
                        expense.getAmount()
                });

                // Clear input fields
                descriptionTextField.setText("");
                amountTextField.setText("");
                dateTextField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Expense added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount.");

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the date in yyyy-MM-dd format.");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });
        JPanel formContainer = new JPanel(
                new FlowLayout(FlowLayout.CENTER));

        formContainer.add(addExpenseFormPanel);

        topPanel.add(formContainer, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Expense records section
        JPanel expenseTablePanel = new JPanel();
        expenseTablePanel.setLayout(
                new BorderLayout(0, 0));

        JLabel expenseTableTitleLabel =
                new JLabel("Expense Records");
        expenseTableTitleLabel.setFont(
                new Font("Tahoma", Font.BOLD, 16));

        expenseTablePanel.add(
                expenseTableTitleLabel, BorderLayout.NORTH);

        JScrollPane expenseScrollPane =
                new JScrollPane();

        expenseTablePanel.add(
                expenseScrollPane, BorderLayout.CENTER);

        expensesTable = new JTable();

        expensesTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Date",
                        "Description",
                        "Category",
                        "Amount (€)"
                }
        ));

        expenseScrollPane.setViewportView(expensesTable);

        add(expenseTablePanel, BorderLayout.CENTER);
    }
}
