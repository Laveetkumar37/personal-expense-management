package it.unifi.ast.ui;

import java.awt.EventQueue;
import javax.swing.JTable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class PersonalExpenseSwingView extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalExpenseSwingView frame = new PersonalExpenseSwingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonalExpenseSwingView() {
	    setTitle("Personal Expense and Budget Management System");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(900, 600);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    
	    JPanel Header = new JPanel();
	    getContentPane().add(Header, BorderLayout.NORTH);
	    
	    JLabel lblNewLabel_1 = new JLabel("Personal Expense and Budget Management System");
	    lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
	    Header.add(lblNewLabel_1);
	    
	    JPanel Navigation_panel = new JPanel();
	    getContentPane().add(Navigation_panel, BorderLayout.WEST);
	    Navigation_panel.setLayout(new GridLayout(3, 1, 0, 5));
	    
	    JButton dashboardButton = new JButton("Dashboard");
	    Navigation_panel.add(dashboardButton);
	    
	    JButton expensesButton = new JButton("Expenses");
	    Navigation_panel.add(expensesButton);
	    
	    JButton budgetsButton = new JButton("Budgets");
	    Navigation_panel.add(budgetsButton);
	    
	    JPanel Main_content_panel = new JPanel();
	    getContentPane().add(Main_content_panel, BorderLayout.CENTER);
	    Main_content_panel.setLayout(new BorderLayout(0, 0));
	    
	    JLabel dashboardTitleLabel = new JLabel("Dashboard Overview");
	    dashboardTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    Main_content_panel.add(dashboardTitleLabel, BorderLayout.NORTH);
	    
	    JPanel Summary_panel = new JPanel();
	    Main_content_panel.add(Summary_panel, BorderLayout.CENTER);
	    Summary_panel.setLayout(new GridLayout(1, 3, 10, 10));
	    
	    JPanel totalExpensesPanel = new JPanel();
	    totalExpensesPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
	    Summary_panel.add(totalExpensesPanel);
	    totalExpensesPanel.setLayout(new BorderLayout(0, 0));
	    
	    JLabel totalExpensesTitleLabel = new JLabel("Total Expenses");
	    totalExpensesTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    totalExpensesTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    totalExpensesPanel.add(totalExpensesTitleLabel, BorderLayout.NORTH);
	    
	    JLabel totalExpensesAmountLabel = new JLabel("€0.00");
	    totalExpensesAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    totalExpensesAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    totalExpensesPanel.add(totalExpensesAmountLabel, BorderLayout.CENTER);
	    
	    JPanel monthlyBudgetPanel = new JPanel();
	    monthlyBudgetPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
	    Summary_panel.add(monthlyBudgetPanel);
	    monthlyBudgetPanel.setLayout(new BorderLayout(0, 0));
	    
	    JLabel monthlyBudgetTitleLabel = new JLabel("Monthly Budget");
	    monthlyBudgetTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    monthlyBudgetTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    monthlyBudgetPanel.add(monthlyBudgetTitleLabel, BorderLayout.NORTH);
	    
	    JLabel monthlyBudgetAmountLabel = new JLabel("€0.00");
	    monthlyBudgetAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    monthlyBudgetAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    monthlyBudgetPanel.add(monthlyBudgetAmountLabel);
	    
	    JPanel remainingBalancePanel = new JPanel();
	    remainingBalancePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
	    Summary_panel.add(remainingBalancePanel);
	    remainingBalancePanel.setLayout(new BorderLayout(0, 0));
	    
	    JLabel remainingBalanceTitleLabel = new JLabel("Remaining Balance");
	    remainingBalanceTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    remainingBalanceTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    remainingBalancePanel.add(remainingBalanceTitleLabel, BorderLayout.NORTH);
	    
	    JLabel remainingBalanceAmountLabel = new JLabel("€0.00");
	    remainingBalanceAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    remainingBalanceAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    remainingBalancePanel.add(remainingBalanceAmountLabel);
	    
	    JPanel recentExpensesPanel = new JPanel();
	    Main_content_panel.add(recentExpensesPanel, BorderLayout.SOUTH);
	    recentExpensesPanel.setLayout(new BorderLayout(0, 0));
	    
	    JLabel recentExpensesTitleLabel = new JLabel("Recent Expenses");
	    recentExpensesTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    recentExpensesPanel.add(recentExpensesTitleLabel, BorderLayout.NORTH);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    recentExpensesPanel.add(scrollPane, BorderLayout.CENTER);
	    
	    JTable table = new JTable();
	    table.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Date", "Description", "Category", "Amount"
	    	}
	    ));
	    
	    scrollPane.setViewportView(table);

	}

}
