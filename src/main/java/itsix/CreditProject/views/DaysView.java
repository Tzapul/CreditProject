package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import itsix.CreditProject.controllers.interfaces.IDaysController;
import javax.swing.SpinnerNumberModel;

public class DaysView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JSpinner spinner;

	private IDaysController daysController;

	public DaysView(IDaysController daysController) {
		this.daysController = daysController;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JButton btnAddDays = new JButton("Add day/s");
		btnAddDays.setBounds(156, 42, 89, 23);
		add(btnAddDays);
		btnAddDays.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				daysController.addDays(getSpinnerValue());
			}
		});

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 360, 1));
		spinner.setBounds(53, 43, 53, 20);
		add(spinner);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}

	protected Integer getSpinnerValue() {
		return (Integer) spinner.getValue();
	}
}
