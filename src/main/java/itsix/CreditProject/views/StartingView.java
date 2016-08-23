package itsix.CreditProject.views;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.CreditProject.controllers.IStartingController;

public class StartingView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblNewLabel;

	private IStartingController controller;
	
	public StartingView(IStartingController controller) {

		this.controller = controller;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

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
				controller.goToProductsWindow();
			}
		});

		btnProducts.setBounds(70, 382, 130, 40);
		add(btnProducts);

		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goToClientsWindow();
			}
		});
		btnClients.setBounds(311, 382, 130, 40);
		add(btnClients);

	}
}
