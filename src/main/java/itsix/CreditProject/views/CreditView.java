package itsix.CreditProject.views;

import javax.swing.JFrame;
import javax.swing.JLabel;

import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.customs.DoubleJTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CreditView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblNameValue;
	private JLabel lblPeriodValue;
	private JLabel lblDailyRateValue;
	private JLabel lblRemainingValue;

	private ICreditController creditController;
	private JTextField advancedMoneyTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public CreditView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 340);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(39, 40, 46, 14);
		add(lblName);

		lblNameValue = new JLabel("");
		lblNameValue.setBounds(168, 40, 112, 14);
		add(lblNameValue);

		JLabel lblPerioddays = new JLabel("Period(days) :");
		lblPerioddays.setBounds(39, 75, 75, 14);
		add(lblPerioddays);

		lblPeriodValue = new JLabel("");
		lblPeriodValue.setBounds(168, 75, 112, 14);
		add(lblPeriodValue);

		JLabel lblDailyRate = new JLabel("Daily Rate :");
		lblDailyRate.setBounds(39, 114, 63, 14);
		add(lblDailyRate);

		lblDailyRateValue = new JLabel("");
		lblDailyRateValue.setBounds(168, 114, 112, 14);
		add(lblDailyRateValue);

		JLabel lblRemainingMoney = new JLabel("Remaining Money :");
		lblRemainingMoney.setBounds(39, 156, 97, 14);
		add(lblRemainingMoney);

		lblRemainingValue = new JLabel("");
		lblRemainingValue.setBounds(168, 156, 112, 14);
		add(lblRemainingValue);

		JButton btnAdvancedPayment = new JButton("Advanced Payment");
		btnAdvancedPayment.setBounds(155, 253, 140, 23);
		add(btnAdvancedPayment);
		
		advancedMoneyTextField = new DoubleJTextField();
		advancedMoneyTextField.setBounds(155, 203, 140, 20);
		add(advancedMoneyTextField);
		advancedMoneyTextField.setColumns(10);
		advancedMoneyTextField.setText("0");
		
		JRadioButton rdbtnCashPayment = new JRadioButton("Cash Payment");
		rdbtnCashPayment.setSelected(true);
		buttonGroup.add(rdbtnCashPayment);
		rdbtnCashPayment.setBounds(27, 225, 109, 23);
		add(rdbtnCashPayment);
		rdbtnCashPayment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				creditController.changeToCashPayment();
			}
		});
		
		JRadioButton rdbtnSoldPayment = new JRadioButton("Sold Payment");
		buttonGroup.add(rdbtnSoldPayment);
		rdbtnSoldPayment.setBounds(319, 225, 109, 23);
		add(rdbtnSoldPayment);
		rdbtnSoldPayment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				creditController.changeToSoldPayment();
			}
		});
		
		btnAdvancedPayment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				creditController.payInAdvance();
			}
		});
	}

	public Double getAdvancedPaymentMoney() {
		return Double.valueOf(advancedMoneyTextField.getText());
	}
}
