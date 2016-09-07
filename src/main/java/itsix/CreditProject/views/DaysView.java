package itsix.CreditProject.views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class DaysView extends JFrame {

	private static final long serialVersionUID = 1L;

	public DaysView() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnAddDays = new JButton("Add day/s");
		btnAddDays.setBounds(156, 42, 89, 23);
		getContentPane().add(btnAddDays);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(53, 43, 53, 20);
		getContentPane().add(spinner);
	}
}
