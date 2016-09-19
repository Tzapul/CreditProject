package itsix.CreditProject.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.customs.AccountTableModel;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.ISubscriber;

public class ClientView extends JFrame implements ISubscriber {

	private static final long serialVersionUID = 1L;
	private JTextField searchTextField;
	private JTextField ssnTextField;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField addressTextField;

	private JTable accountsTable;

	private IClientsController clientController;

	private AccountTableModel tableModel;

	private JButton btnNewAccount;
	private JButton btnSaveCredentials;

	public ClientView(IClientsController controller) {
		this.clientController = controller;
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("Clients");
		setBounds(100, 100, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblSsn = new JLabel("SSN :");
		lblSsn.setBounds(47, 108, 26, 14);
		add(lblSsn);

		ssnTextField = new IntegerJTextField();
		ssnTextField.setBounds(98, 105, 163, 20);
		ssnTextField.setEditable(false);
		add(ssnTextField);
		ssnTextField.setColumns(10);

		JLabel lblFirstname = new JLabel("Firstname :");
		lblFirstname.setBounds(312, 108, 59, 14);
		add(lblFirstname);

		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(387, 105, 163, 20);
		add(firstnameTextField);
		firstnameTextField.setColumns(10);

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 594, 78);
		searchPanel.setBackground(Color.WHITE);
		add(searchPanel);
		searchPanel.setLayout(null);

		JLabel lblSSNSearch = new JLabel("SSN :");
		lblSSNSearch.setBounds(42, 27, 26, 14);
		searchPanel.add(lblSSNSearch);

		searchTextField = new IntegerJTextField();
		searchTextField.setBounds(82, 24, 190, 20);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		searchTextField.setText("0");

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(288, 24, 89, 20);
		searchPanel.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(getClass().getClassLoader().getResource("search-icon.png")));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clientController.searchForClient();
			}
		});

		JLabel lblLastname = new JLabel("Lastname :");
		lblLastname.setBounds(312, 159, 62, 14);
		add(lblLastname);

		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(387, 156, 163, 20);
		add(lastnameTextField);
		lastnameTextField.setColumns(10);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(28, 159, 46, 14);
		add(lblAddress);

		addressTextField = new JTextField();
		addressTextField.setBounds(98, 156, 163, 20);
		add(addressTextField);
		addressTextField.setColumns(10);

		btnSaveCredentials = new JButton("Save Credentials");
		btnSaveCredentials.setEnabled(false);
		btnSaveCredentials.setBounds(155, 205, 120, 25);
		add(btnSaveCredentials);
		btnSaveCredentials.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clientController.updateClient();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 301, 508, 152);
		add(scrollPane);

		tableModel = new AccountTableModel(new ArrayList<IAccount>());
		accountsTable = new JTable(tableModel);
		accountsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					clientController.goToAccountView();
				}
			}
		});

		scrollPane.setViewportView(accountsTable);

		JLabel lblAccounts = new JLabel("Accounts :");
		lblAccounts.setBounds(147, 264, 59, 14);
		add(lblAccounts);

		btnNewAccount = new JButton("New Account");
		btnNewAccount.setEnabled(false);
		btnNewAccount.setBounds(234, 260, 120, 23);
		add(btnNewAccount);
		btnNewAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientController.goToNewAccountView();
			}
		});

		JButton btnNewClient = new JButton("New Client");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientController.goToNewClientView();
			}
		});
		btnNewClient.setBounds(312, 206, 120, 23);
		add(btnNewClient);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	public Integer getSearchSSN() {
		return Integer.valueOf(searchTextField.getText());
	}

	public void paintClient(IClient currentClient) {
		ssnTextField.setText(String.valueOf(currentClient.getSSN()));
		firstnameTextField.setText(currentClient.getFirstname());
		lastnameTextField.setText(currentClient.getLastname());
		addressTextField.setText(currentClient.getAddress());

		tableModel = new AccountTableModel(currentClient.getAccounts());
		accountsTable.setModel(tableModel);

		clientController.hasAllAccounts();
	}

	public void clearSearchTextField() {
		searchTextField.setText("0");
	}

	public String getFirstname() {
		return firstnameTextField.getText();
	}

	public String getLastname() {
		return lastnameTextField.getText();
	}

	public String getAddress() {
		return addressTextField.getText();
	}

	public IAccount getSelectedAccount() {
		return tableModel.getRow(accountsTable.getSelectedRow());
	}

	public void subscribe() {
		clientController.getCurrentClient().subscribe(this);
	}

	@Override
	public void update() {
		clientController.updateTableModel();
	}

	public void setNewAccountDisabled() {
		btnNewAccount.setEnabled(false);
	}

	public void setNewAccountEnabled() {
		btnNewAccount.setEnabled(true);
	}

	public void setUpdateClientEnabled() {
		btnSaveCredentials.setEnabled(true);
	}

	public void setUpdateClientDisabled() {
		btnSaveCredentials.setEnabled(false);
	}

	public void resetClient() {
		ssnTextField.setText("");
		firstnameTextField.setText("");
		lastnameTextField.setText("");
		addressTextField.setText("");

		btnNewAccount.setEnabled(false);
		btnSaveCredentials.setEnabled(false);

		searchTextField.setText("0");

		tableModel = new AccountTableModel(new ArrayList<IAccount>());
		accountsTable.setModel(tableModel);
	}

	public Integer getsSN() {
		return Integer.valueOf(ssnTextField.getText());
	}
}
