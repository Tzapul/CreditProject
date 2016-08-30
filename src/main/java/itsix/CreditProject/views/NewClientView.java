package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import itsix.CreditProject.controllers.interfaces.INewClientController;
import itsix.CreditProject.customs.IntegerJTextField;

public class NewClientView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField ssnTextField;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField addressTextField;

	private INewClientController controller;

	public NewClientView(INewClientController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 250, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("New Client");

		JLabel lblSsn = new JLabel("SSN :");
		lblSsn.setBounds(58, 38, 40, 14);
		add(lblSsn);

		JLabel lblFirstname = new JLabel("Firstname :");
		lblFirstname.setBounds(31, 80, 67, 14);
		add(lblFirstname);

		JLabel lblLastname = new JLabel("Lastname :");
		lblLastname.setBounds(31, 120, 65, 14);
		add(lblLastname);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(40, 161, 58, 14);
		add(lblAddress);

		ssnTextField = new IntegerJTextField();
		ssnTextField.setBounds(108, 35, 86, 20);
		add(ssnTextField);
		ssnTextField.setColumns(10);
		ssnTextField.setText("0");

		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(108, 77, 86, 20);
		add(firstnameTextField);
		firstnameTextField.setColumns(10);

		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(108, 117, 86, 20);
		add(lastnameTextField);
		lastnameTextField.setColumns(10);

		addressTextField = new JTextField();
		addressTextField.setBounds(108, 158, 86, 20);
		add(addressTextField);
		addressTextField.setColumns(10);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(71, 211, 89, 23);
		add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addClient();
			}
		});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel"); //$NON-NLS-1$
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public String getAddress() {
		return addressTextField.getText();
	}

	public String getLastname() {
		return lastnameTextField.getText();
	}

	public String getFirstname() {
		return firstnameTextField.getText();
	}

	public Integer getSSN() {
		return Integer.valueOf(ssnTextField.getText());
	}
}
