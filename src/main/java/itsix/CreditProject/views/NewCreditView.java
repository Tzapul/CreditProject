package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import itsix.CreditProject.controllers.INewCreditController;
import itsix.CreditProject.customs.DoubleJTextField;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.ICurrency;

public class NewCreditView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	private JTextField interestRateTextField;
	private JTextField periodTextField;

	private JLabel lblRealInterestRate;
	private JLabel lblRealInterestRateValue;

	private ButtonGroup buttonGroup;

	private JComboBox<ICurrency> currencyComboBox;

	private INewCreditController controller;

	public NewCreditView(INewCreditController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setTitle("Create Credit");
		setBounds(100, 100, 300, 439);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(25, 25, 99, 14);
		add(lblName);

		JLabel lblMinValue = new JLabel("Min. Value :");
		lblMinValue.setBounds(25, 66, 99, 14);
		add(lblMinValue);

		JLabel lblMaxValue = new JLabel("Max. Value :");
		lblMaxValue.setBounds(25, 106, 99, 14);
		add(lblMaxValue);

		JLabel lblInterestRate = new JLabel("Interest rate (%) :");
		lblInterestRate.setBounds(25, 149, 99, 14);
		add(lblInterestRate);

		nameTextField = new JTextField();
		nameTextField.setBounds(135, 22, 86, 20);
		add(nameTextField);
		nameTextField.setColumns(10);

		minValueTextField = new IntegerJTextField();
		minValueTextField.setBounds(135, 63, 86, 20);
		add(minValueTextField);
		minValueTextField.setColumns(10);
		minValueTextField.setText("0");

		maxValueTextField = new IntegerJTextField();
		maxValueTextField.setBounds(135, 103, 86, 20);
		add(maxValueTextField);
		maxValueTextField.setColumns(10);
		maxValueTextField.setText("0");

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setBounds(134, 146, 86, 20);
		add(interestRateTextField);
		interestRateTextField.setColumns(10);
		interestRateTextField.setText("0");
		interestRateTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			public void update() {
				if (interestIsNotNull()) {
					controller.updateRealInterestRate();
				}
			}
		});

		JLabel lblCurrency = new JLabel("Currency :");
		lblCurrency.setBounds(25, 189, 99, 14);
		add(lblCurrency);

		currencyComboBox = new JComboBox<>();
		currencyComboBox.setModel(new DefaultComboBoxModel<ICurrency>(controller.getCurrencies()));
		currencyComboBox.setBounds(135, 186, 85, 20);
		add(currencyComboBox);

		buttonGroup = new ButtonGroup();

		JRadioButton rdbtnFixedInterest = new JRadioButton("Fixed Interest");
		rdbtnFixedInterest.setSelected(true);
		rdbtnFixedInterest.setBounds(15, 321, 109, 23);
		rdbtnFixedInterest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeToFixedBuilder();
				controller.setLabelsInvisible();
			}
		});
		add(rdbtnFixedInterest);

		JRadioButton rdbtnVariableInterest = new JRadioButton("Variable Interest");
		rdbtnVariableInterest.setBounds(154, 321, 109, 23);
		rdbtnVariableInterest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeToVariableBuilder();
				controller.setLabelsVisible();
			}
		});
		add(rdbtnVariableInterest);

		buttonGroup.add(rdbtnVariableInterest);
		buttonGroup.add(rdbtnFixedInterest);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(90, 364, 100, 25);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createNewCredit();
			}
		});

		add(btnCreate);

		JLabel lblPeriod = new JLabel("Period (months) :");
		lblPeriod.setBounds(25, 232, 99, 14);
		add(lblPeriod);

		periodTextField = new IntegerJTextField();
		periodTextField.setBounds(135, 229, 86, 20);
		add(periodTextField);
		periodTextField.setColumns(10);
		periodTextField.setText("0");

		lblRealInterestRate = new JLabel("Real interest rate (%) :");
		lblRealInterestRate.setBounds(25, 277, 113, 14);
		add(lblRealInterestRate);

		lblRealInterestRateValue = new JLabel("");
		lblRealInterestRateValue.setBounds(154, 277, 67, 14);
		add(lblRealInterestRateValue);

		lblRealInterestRate.setVisible(false);
		lblRealInterestRateValue.setVisible(false);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
	}

	public Double getMinValue() {
		return Double.valueOf(minValueTextField.getText());
	}

	public Double getMaxValue() {
		return Double.valueOf(maxValueTextField.getText());
	}

	public Double getInterestRate() {
		return Double.valueOf(interestRateTextField.getText());
	}

	public ICurrency getCurrency() {
		return (ICurrency) currencyComboBox.getSelectedItem();
	}

	public Integer getPeriod() {
		return Integer.valueOf(periodTextField.getText());
	}

	public String getCreditName() {
		return nameTextField.getText();
	}

	public void setInterestRateVisible() {
		lblRealInterestRate.setVisible(true);
	}

	public void setInterestRateValueVisible() {
		lblRealInterestRateValue.setVisible(true);
	}

	public void assignInterestRateValue(Double value) {
		if (interestIsNotNull()) {
			DecimalFormat df = new DecimalFormat("#.###");
			lblRealInterestRateValue.setText(df.format(value));
		}
	}

	public void setInterestRateValueInvisible() {
		lblRealInterestRateValue.setVisible(false);
	}

	public void setInterestRateInvisible() {
		lblRealInterestRate.setVisible(false);
	}

	public boolean interestIsNotNull() {
		return !interestRateTextField.getText().equals(null) && interestRateTextField.getText().length() != 0;
	}

}
