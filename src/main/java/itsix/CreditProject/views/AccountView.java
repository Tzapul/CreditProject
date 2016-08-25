package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import itsix.CreditProject.controllers.IAccountController;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.pubSub.ISubscriber;

public class AccountView extends JFrame implements ISubscriber{

	private static final long serialVersionUID = 1L;

	private IAccountController controller;
	private JLabel lblCurrenyValue;

	private JTextField soldTextField;
	private JTextField moneyTextField;
	private JTable creditsTable;

	public AccountView(IAccountController controller) {
		this.controller = controller;
		controller.getAccount().subscribe(this);
		initialize();
	}

	private void initialize() {
		new JFrame();
		setBounds(100, 100, 437, 460);
		getContentPane().setLayout(null);

		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(30, 32, 51, 14);
		getContentPane().add(lblCurrency);

		JLabel lblSold = new JLabel("Sold :");
		lblSold.setBounds(54, 81, 27, 14);
		getContentPane().add(lblSold);

		lblCurrenyValue = new JLabel("");
		lblCurrenyValue.setBounds(129, 32, 86, 14);
		getContentPane().add(lblCurrenyValue);

		soldTextField = new JTextField();
		soldTextField.setEditable(false);
		soldTextField.setBounds(129, 78, 86, 20);
		getContentPane().add(soldTextField);
		soldTextField.setColumns(10);

		moneyTextField = new IntegerJTextField();
		moneyTextField.setBounds(298, 32, 86, 20);
		getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);
		moneyTextField.setText("0");

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(295, 77, 89, 23);
		getContentPane().add(btnDeposit);
		btnDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.depositMoney();
				moneyTextField.setText("0");
			}
		});

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(295, 126, 89, 23);
		getContentPane().add(btnWithdraw);
		btnWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.withdrawMoney();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 225, 354, 140);
		getContentPane().add(scrollPane);

		creditsTable = new JTable();
		creditsTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Name", "Monthly Fee", "Interest Rate", "Period" }));
		scrollPane.setViewportView(creditsTable);

		JLabel lblCurrentCredits = new JLabel("Current Credits");
		lblCurrentCredits.setBounds(165, 194, 76, 14);
		getContentPane().add(lblCurrentCredits);

		JButton btnMakeCredit = new JButton("Make Credit");
		btnMakeCredit.setBounds(152, 376, 89, 23);
		getContentPane().add(btnMakeCredit);
	}

	public void setSold(Integer sold) {
		soldTextField.setText(String.valueOf(sold));
	}

	public void setCurrency(String currencyName) {
		lblCurrenyValue.setText(currencyName);
	}

	public Integer getMoney() {
		return Integer.valueOf(moneyTextField.getText());
	}

	@Override
	public void update() {
		soldTextField.setText(String.valueOf(controller.getAccount().getSold()));
	}
}
