package itsix.CreditProject.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import itsix.CreditProject.controllers.IClientsController;
import itsix.CreditProject.customs.AccountTableModel;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.IClient;

public class ClientsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField searchTextField;
	private JTextField ssnTextField;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField addressTextField;
	private JTable accountsTable;
	private IClientsController controller;

	public ClientsView(IClientsController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("Client");
		setBounds(100, 100, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblSsn = new JLabel("SSN :");
		lblSsn.setBounds(47, 108, 26, 14);
		getContentPane().add(lblSsn);

		ssnTextField = new IntegerJTextField();
		ssnTextField.setBounds(98, 105, 163, 20);
		ssnTextField.setEditable(false);
		getContentPane().add(ssnTextField);
		ssnTextField.setColumns(10);

		JLabel lblFirstname = new JLabel("Firstname :");
		lblFirstname.setBounds(312, 108, 59, 14);
		getContentPane().add(lblFirstname);

		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(387, 105, 163, 20);
		getContentPane().add(firstnameTextField);
		firstnameTextField.setColumns(10);

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 594, 78);
		searchPanel.setBackground(Color.WHITE);
		getContentPane().add(searchPanel);
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
				controller.searchForClient();
			}
		});

		JLabel lblLastname = new JLabel("Lastname :");
		lblLastname.setBounds(312, 159, 62, 14);
		getContentPane().add(lblLastname);

		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(387, 156, 163, 20);
		getContentPane().add(lastnameTextField);
		lastnameTextField.setColumns(10);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(28, 159, 46, 14);
		getContentPane().add(lblAddress);

		addressTextField = new JTextField();
		addressTextField.setBounds(98, 156, 163, 20);
		getContentPane().add(addressTextField);
		addressTextField.setColumns(10);

		JButton btnSaveCredentials = new JButton("Save Credentials");
		btnSaveCredentials.setBounds(155, 205, 120, 25);
		getContentPane().add(btnSaveCredentials);
		btnSaveCredentials.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.updateClient();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 301, 508, 152);
		getContentPane().add(scrollPane);

		accountsTable = new JTable();
		accountsTable.setModel(new DefaultTableModel(new Object[][] { { "LEI", "RON" }, { "EURO", "\u20AC" }, },
				new String[] { "Name", "Currency" }));
		accountsTable.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
			        if (me.getClickCount() == 2) {

			        }
			    }
		});
		
		scrollPane.setViewportView(accountsTable);

		JLabel lblAccounts = new JLabel("Accounts :");
		lblAccounts.setBounds(147, 264, 59, 14);
		getContentPane().add(lblAccounts);

		JButton btnNewAccount = new JButton("New Account");
		btnNewAccount.setBounds(234, 260, 120, 23);
		getContentPane().add(btnNewAccount);
		
		JButton btnNewClient = new JButton("New Client");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.goToNewClientView();
			}
		});
		btnNewClient.setBounds(312, 206, 120, 23);
		getContentPane().add(btnNewClient);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
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
		
		accountsTable.setModel(new AccountTableModel(currentClient.getAccounts()));
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
}
