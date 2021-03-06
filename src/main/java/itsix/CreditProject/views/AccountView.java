package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.customs.CreditsTableModel;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.pubSub.ISubscriber;

public class AccountView extends JFrame implements ISubscriber {

	private static final long serialVersionUID = 1L;

	private IAccountController accountController;
	private JLabel lblCurrenyValue;

	private JTextField soldTextField;
	private JTextField moneyTextField;

	private JTable creditsTable;
	private CreditsTableModel creditsModel;

	private JButton btnWithdraw;
	private JButton btnDeposit;

	public AccountView(IAccountController controller) {
		this.accountController = controller;
		initialize();
	}

	private void initialize() {
		new JFrame();
		setBounds(100, 100, 599, 470);
		getContentPane().setLayout(null);
		setTitle("My account");

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
		moneyTextField.setBounds(446, 32, 86, 20);
		getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);
		moneyTextField.setText("0");
		moneyTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			public void update() {
				accountController.toggleOperationButtons();
			}
		});

		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(443, 77, 89, 23);
		btnDeposit.setEnabled(false);
		getContentPane().add(btnDeposit);
		btnDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountController.depositMoney();
			}
		});

		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(443, 126, 89, 23);
		btnWithdraw.setEnabled(false);
		getContentPane().add(btnWithdraw);
		btnWithdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountController.withdrawMoney();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 225, 521, 140);
		getContentPane().add(scrollPane);

		creditsTable = new JTable();
		creditsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					accountController.goToCreditView();
				}
			}
		});
		scrollPane.setViewportView(creditsTable);

		JLabel lblCurrentCredits = new JLabel("Current Credits");
		lblCurrentCredits.setBounds(252, 189, 89, 14);
		getContentPane().add(lblCurrentCredits);

		JButton btnMakeCredit = new JButton("Make Credit");
		btnMakeCredit.setBounds(252, 386, 89, 23);

		getContentPane().add(btnMakeCredit);
		btnMakeCredit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountController.goToMakeCreditView();
			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void setSold(Double sold) {
		DecimalFormat df = new DecimalFormat("#.###");
		soldTextField.setText(String.valueOf(df.format(sold)));
	}

	public void setCurrency(String currencyName) {
		lblCurrenyValue.setText(currencyName);
	}

	public Double getMoney() {
		return Double.valueOf(moneyTextField.getText());
	}

	@Override
	public void update() {
		IAccount account = accountController.getAccount();
		DecimalFormat df = new DecimalFormat("#.###");
		soldTextField.setText(String.valueOf(df.format(account.getSold())));
		creditsModel = new CreditsTableModel(account.getCredits());
		creditsTable.setModel(creditsModel);
	}

	public boolean moneyValueisZero() {
		return moneyTextField.getText().equals("0");
	}

	public void disableButtons() {
		btnDeposit.setEnabled(false);
		btnWithdraw.setEnabled(false);
	}

	public void enableButtons() {
		btnDeposit.setEnabled(true);
		btnWithdraw.setEnabled(true);
	}

	public void setTableModel(List<ICredit> credits) {
		creditsModel = new CreditsTableModel(credits);
		creditsTable.setModel(creditsModel);
	}

	public ICredit getSelectedCredit() {
		return creditsModel.getRow(creditsTable.getSelectedRow());
	}

	public void show(IClient currentClient, IAccount account) {
		account.subscribe(this);
		setVisible(true);
		accountController.setClient(currentClient);
		accountController.setAccount(account);
		accountController.updateFields();
	}

	public void setResetMoneyText() {
		moneyTextField.setText("0");
	}

}
