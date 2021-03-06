package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;

public class NewAccountView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField soldTextField;

	private INewAccountController newAccountController;

	private JComboBox<ICurrency> currencyComboBox;
	
	private IClientsController clientsController;

	public NewAccountView(INewAccountController newAccountController) {
		this.newAccountController = newAccountController;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 254, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("New Account");
		
		JLabel lblSold = new JLabel("Sold :");
		lblSold.setBounds(24, 64, 46, 14);
		add(lblSold);

		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(24, 22, 61, 14);
		add(lblCurrency);

		currencyComboBox = new JComboBox<ICurrency>();
		currencyComboBox.setBounds(103, 19, 86, 20);
		add(currencyComboBox);

		soldTextField = new IntegerJTextField();
		soldTextField.setBounds(103, 61, 86, 20);
		add(soldTextField);
		soldTextField.setColumns(10);
		soldTextField.setText("0");

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(74, 110, 89, 23);
		add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newAccountController.createNewAccount();
				clientsController.hasAllAccounts();
			}
		});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				resetView();
				setVisible(false);
			}
		});
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel"); //$NON-NLS-1$
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				resetView();
				setVisible(false);
				
			}
		});
	}

	protected void resetView() {
		soldTextField.setText("0");
	}

	public Double getSold() {
		return Double.valueOf(soldTextField.getText());
	}

	public ICurrency getCurrency() {
		return (ICurrency) currencyComboBox.getSelectedItem();
	}
	
	public void setClientsController(IClientsController clientsController) {
		this.clientsController = clientsController;
	}

	public void show(IClient currentClient) {
		setVisible(true);
		newAccountController.setClient(currentClient);
		currencyComboBox.setModel(new DefaultComboBoxModel<ICurrency>(newAccountController.getRemainingCurrencies()));

	}
}
