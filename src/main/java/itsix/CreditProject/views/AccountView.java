package itsix.CreditProject.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import itsix.CreditProject.controllers.IAccountController;

public class AccountView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private IAccountController controller;
	private JTextField debitTextField;
	private JTextField moneyTextField;
	private JTable creditsTable;

	public AccountView(IAccountController controller) {
		this.controller = controller;
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(47, 37, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(30, 83, 51, 14);
		getContentPane().add(lblCurrency);
		
		JLabel lblSold = new JLabel("Sold :");
		lblSold.setBounds(54, 132, 27, 14);
		getContentPane().add(lblSold);
		
		JLabel lblNameValue = new JLabel("");
		lblNameValue.setBounds(129, 37, 86, 14);
		getContentPane().add(lblNameValue);
		
		JLabel lblCurrenyValue = new JLabel("");
		lblCurrenyValue.setBounds(129, 83, 86, 14);
		getContentPane().add(lblCurrenyValue);
		
		debitTextField = new JTextField();
		debitTextField.setEditable(false);
		debitTextField.setBounds(129, 129, 86, 20);
		getContentPane().add(debitTextField);
		debitTextField.setColumns(10);
		
		moneyTextField = new JTextField();
		moneyTextField.setBounds(298, 32, 86, 20);
		getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(295, 77, 89, 23);
		getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(295, 126, 89, 23);
		getContentPane().add(btnWithdraw);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 225, 354, 140);
		getContentPane().add(scrollPane);
		
		creditsTable = new JTable();
		creditsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Name", "Monthly Fee", "Interest Rate", "Period"
			}
		));
		scrollPane.setViewportView(creditsTable);
		
		JLabel lblCurrentCredits = new JLabel("Current Credits");
		lblCurrentCredits.setBounds(165, 194, 76, 14);
		getContentPane().add(lblCurrentCredits);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		new JFrame();
		setBounds(100, 100, 437, 453);
	}
}
