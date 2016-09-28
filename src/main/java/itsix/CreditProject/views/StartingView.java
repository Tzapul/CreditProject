package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import itsix.CreditProject.controllers.interfaces.IStartingController;

public class StartingView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblNewLabel;

	private IStartingController startingController;

	public StartingView(IStartingController controller) {

		this.startingController = controller;

		initialize();
	}

	private void initialize() {

		setResizable(false);
		setTitle("Credit Bank");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bank-flat.png")));
		lblNewLabel.setBounds(123, 50, 256, 256);
		add(lblNewLabel);

		JButton btnProducts = new JButton("Products");
		btnProducts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startingController.showProductsWindow();
			}
		});

		btnProducts.setBounds(70, 382, 130, 40);
		add(btnProducts);

		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startingController.showClientsWindow();
			}
		});
		btnClients.setBounds(311, 382, 130, 40);
		add(btnClients);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent w) {
				startingController.serialize();
			}
		});

	}
}
