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

import itsix.CreditProject.controllers.interfaces.IEditProductController;
import itsix.CreditProject.customs.DoubleJTextField;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.implementations.Interval;
import itsix.CreditProject.models.implementations.MoneyInterval;
import itsix.CreditProject.models.implementations.PeriodInterval;
import itsix.CreditProject.models.implementations.Product;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;

public class EditProductView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	private JTextField interestRateTextField;
	private JTextField minPeriodTextField;

	private JComboBox<ICurrency> currencyComboBox;

	private IEditProductController controller;
	private JTextField maxPeriodTextField;

	public EditProductView(IEditProductController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Edit Product");
		setBounds(100, 100, 300, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(25, 25, 99, 14);
		add(lblName);

		JLabel lblMinValue = new JLabel("Min. Money :");
		lblMinValue.setBounds(25, 66, 99, 14);
		add(lblMinValue);

		JLabel lblMaxValue = new JLabel("Max. Money :");
		lblMaxValue.setBounds(25, 106, 99, 14);
		add(lblMaxValue);

		JLabel lblInterestRate = new JLabel("Interest rate (%) :");
		lblInterestRate.setBounds(25, 149, 99, 14);
		add(lblInterestRate);

		nameTextField = new JTextField();
		nameTextField.setBounds(170, 22, 86, 20);
		add(nameTextField);
		nameTextField.setColumns(10);

		minValueTextField = new IntegerJTextField();
		minValueTextField.setBounds(170, 63, 86, 20);
		add(minValueTextField);
		minValueTextField.setColumns(10);

		maxValueTextField = new IntegerJTextField();
		maxValueTextField.setBounds(170, 103, 86, 20);
		add(maxValueTextField);
		maxValueTextField.setColumns(10);

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setBounds(170, 146, 86, 20);
		add(interestRateTextField);
		interestRateTextField.setColumns(10);

		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(25, 189, 99, 14);
		add(lblCurrency);

		currencyComboBox = new JComboBox<>();
		currencyComboBox.setEnabled(false);
		currencyComboBox.setModel(new DefaultComboBoxModel<ICurrency>(controller.getCurrencies()));
		currencyComboBox.setBounds(170, 186, 85, 20);
		add(currencyComboBox);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(89, 325, 100, 25);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updateCredit();
			}
		});

		add(btnEdit);

		JLabel lblMinPeriod = new JLabel("Min. Period (months) :");
		lblMinPeriod.setBounds(25, 232, 118, 14);
		add(lblMinPeriod);

		minPeriodTextField = new IntegerJTextField();
		minPeriodTextField.setBounds(170, 229, 86, 20);
		add(minPeriodTextField);
		minPeriodTextField.setColumns(10);

		JLabel lblMaxPeriod = new JLabel("Max. Period (months) :");
		lblMaxPeriod.setBounds(25, 275, 118, 14);
		add(lblMaxPeriod);

		maxPeriodTextField = new JTextField();
		maxPeriodTextField.setBounds(170, 272, 86, 20);
		add(maxPeriodTextField);
		maxPeriodTextField.setColumns(10);

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

	public void setCreditName(String name) {
		nameTextField.setText(name);
	}

	public void setMaxValue(Integer maxValue) {
		maxValueTextField.setText(Integer.toString(maxValue));
	}

	public void setMinimumSize(Integer minValue) {
		minValueTextField.setText(Integer.toString(minValue));
	}

	public void setInterestRate(Double interestRate) {
		interestRateTextField.setText(Double.toString(interestRate));
	}

	public void setCurrency(ICurrency currency) {
		currencyComboBox.setSelectedItem(currency);
	}

	public void setPeriod(Integer period) {
		minPeriodTextField.setText(Integer.toString(period));
	}

	public IProduct getUpdatedProduct() {
		String name = nameTextField.getText();

		Integer minValue = Integer.valueOf(minValueTextField.getText());
		Integer maxValue = Integer.valueOf(maxValueTextField.getText());
		IInterval moneyInterval = new MoneyInterval(new Interval(minValue, maxValue));

		ICurrency currency = (ICurrency) currencyComboBox.getSelectedItem();
		Double interestRate = Double.valueOf(interestRateTextField.getText());

		Integer minPeriod = Integer.valueOf(minPeriodTextField.getText());
		Integer maxPeriod = Integer.valueOf(maxPeriodTextField.getText());
		IInterval period = new PeriodInterval(new Interval(minPeriod, maxPeriod));

		IProduct credit = new Product(name, moneyInterval, currency, interestRate, period);

		return credit;
	}

	public void setMinPeriod(Integer minPeriod) {
		minPeriodTextField.setText(String.valueOf(minPeriod));
	}

	public void setMaxPeriod(Integer maxPeriod) {
		maxPeriodTextField.setText(String.valueOf(maxPeriod));
	}

}
