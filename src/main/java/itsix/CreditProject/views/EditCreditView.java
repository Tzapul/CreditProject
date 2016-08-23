package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import itsix.CreditProject.controllers.IEditCreditController;
import itsix.CreditProject.customs.DoubleJTextField;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.Credit;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.Interval;

public class EditCreditView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	private JTextField interestRateTextField;
	private JTextField periodTextField;

	private JComboBox<ICurrency> currencyComboBox;

	private IEditCreditController controller;

	public EditCreditView(IEditCreditController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Edit Credit");
		setBounds(100, 100, 300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(25, 25, 99, 14);
		getContentPane().add(lblName);

		JLabel lblMinValue = new JLabel("Min. Value :");
		lblMinValue.setBounds(25, 66, 99, 14);
		getContentPane().add(lblMinValue);

		JLabel lblMaxValue = new JLabel("Max. Value :");
		lblMaxValue.setBounds(25, 106, 99, 14);
		getContentPane().add(lblMaxValue);

		JLabel lblInterestRate = new JLabel("Interest rate (%) :");
		lblInterestRate.setBounds(25, 149, 99, 14);
		getContentPane().add(lblInterestRate);

		nameTextField = new JTextField();
		nameTextField.setBounds(135, 22, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		minValueTextField = new IntegerJTextField();
		minValueTextField.setBounds(135, 63, 86, 20);
		getContentPane().add(minValueTextField);
		minValueTextField.setColumns(10);

		maxValueTextField = new IntegerJTextField();
		maxValueTextField.setBounds(135, 103, 86, 20);
		getContentPane().add(maxValueTextField);
		maxValueTextField.setColumns(10);

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setBounds(134, 146, 86, 20);
		getContentPane().add(interestRateTextField);
		interestRateTextField.setColumns(10);

		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(25, 189, 99, 14);
		getContentPane().add(lblCurrency);

		currencyComboBox = new JComboBox<>();
		currencyComboBox.setEnabled(false);
		currencyComboBox.setModel(new DefaultComboBoxModel<ICurrency>(controller.getCurrencies()));
		currencyComboBox.setBounds(135, 186, 85, 20);
		getContentPane().add(currencyComboBox);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(89, 325, 100, 25);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updateCredit();
			}
		});

		getContentPane().add(btnEdit);

		JLabel lblPeriod = new JLabel("Period (months) :");
		lblPeriod.setBounds(25, 232, 99, 14);
		getContentPane().add(lblPeriod);

		periodTextField = new IntegerJTextField();
		periodTextField.setBounds(135, 229, 86, 20);
		getContentPane().add(periodTextField);
		periodTextField.setColumns(10);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
	}

	public void setCreditName(String name) {
		nameTextField.setText(name);
	}

	public void setMaxValue(Double maxValue) {
		maxValueTextField.setText(Double.toString(maxValue));
	}

	public void setMinimumSize(Double minValue) {
		minValueTextField.setText(Double.toString(minValue));
	}

	public void setInterestRate(Double interestRate) {
		interestRateTextField.setText(Double.toString(interestRate));
	}

	public void setCurrency(ICurrency currency) {
		currencyComboBox.setSelectedItem(currency);
	}

	public void setPeriod(Integer period) {
		periodTextField.setText(Integer.toString(period));
	}

	public ICredit getUpdatedCredit() {
		String name = nameTextField.getText();
		Double minValue = Double.valueOf(minValueTextField.getText());
		Double maxValue = Double.valueOf(maxValueTextField.getText());
		IInterval interval = new Interval(minValue, maxValue);
		ICurrency currency = (ICurrency) currencyComboBox.getSelectedItem();
		Double interestRate = Double.valueOf(interestRateTextField.getText());
		Integer period = Integer.valueOf(periodTextField.getText());

		ICredit credit = new Credit(name, interval, currency, interestRate, period);

		return credit;
	}

}
