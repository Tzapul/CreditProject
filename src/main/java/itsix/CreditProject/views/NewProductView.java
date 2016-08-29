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

import itsix.CreditProject.controllers.INewProductController;
import itsix.CreditProject.customs.DoubleJTextField;
import itsix.CreditProject.customs.IntegerJTextField;
import itsix.CreditProject.models.ICurrency;

public class NewProductView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	private JTextField interestRateTextField;
	private JTextField minPeriodTextField;

	private JLabel lblRealInterestRate;
	private JLabel lblRealInterestRateValue;

	private ButtonGroup buttonGroup;

	private JComboBox<ICurrency> currencyComboBox;

	private INewProductController controller;
	private JTextField maxPeriodTextField;

	public NewProductView(INewProductController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setTitle("Create Product");
		setBounds(100, 100, 300, 504);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(25, 25, 99, 14);
		getContentPane().add(lblName);

		JLabel lblMinValue = new JLabel("Min. Money :");
		lblMinValue.setBounds(25, 66, 99, 14);
		getContentPane().add(lblMinValue);

		JLabel lblMaxValue = new JLabel("Max. Money:");
		lblMaxValue.setBounds(25, 106, 99, 14);
		getContentPane().add(lblMaxValue);

		JLabel lblInterestRate = new JLabel("Interest rate (%) :");
		lblInterestRate.setBounds(25, 149, 99, 14);
		getContentPane().add(lblInterestRate);

		nameTextField = new JTextField();
		nameTextField.setBounds(179, 19, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		minValueTextField = new IntegerJTextField();
		minValueTextField.setBounds(179, 60, 86, 20);
		getContentPane().add(minValueTextField);
		minValueTextField.setColumns(10);
		minValueTextField.setText("0");

		maxValueTextField = new IntegerJTextField();
		maxValueTextField.setBounds(179, 100, 86, 20);
		getContentPane().add(maxValueTextField);
		maxValueTextField.setColumns(10);
		maxValueTextField.setText("0");

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setBounds(178, 143, 86, 20);
		getContentPane().add(interestRateTextField);
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
		getContentPane().add(lblCurrency);

		currencyComboBox = new JComboBox<>();
		currencyComboBox.setModel(new DefaultComboBoxModel<ICurrency>(controller.getCurrencies()));
		currencyComboBox.setBounds(179, 183, 85, 20);
		getContentPane().add(currencyComboBox);

		buttonGroup = new ButtonGroup();

		JRadioButton rdbtnFixedInterest = new JRadioButton("Fixed Interest");
		rdbtnFixedInterest.setSelected(true);
		rdbtnFixedInterest.setBounds(17, 369, 109, 23);
		rdbtnFixedInterest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeToFixedBuilder();
				controller.setLabelsInvisible();
			}
		});
		getContentPane().add(rdbtnFixedInterest);

		JRadioButton rdbtnVariableInterest = new JRadioButton("Variable Interest");
		rdbtnVariableInterest.setBounds(156, 369, 109, 23);
		rdbtnVariableInterest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeToVariableBuilder();
				controller.setLabelsVisible();
			}
		});
		getContentPane().add(rdbtnVariableInterest);

		buttonGroup.add(rdbtnVariableInterest);
		buttonGroup.add(rdbtnFixedInterest);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(92, 412, 100, 25);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createNewCredit();
			}
		});

		getContentPane().add(btnCreate);

		JLabel lblMinPeriod = new JLabel("Min. Period (months) :");
		lblMinPeriod.setBounds(25, 232, 113, 14);
		getContentPane().add(lblMinPeriod);

		minPeriodTextField = new IntegerJTextField();
		minPeriodTextField.setBounds(179, 226, 86, 20);
		getContentPane().add(minPeriodTextField);
		minPeriodTextField.setColumns(10);
		minPeriodTextField.setText("0");

		lblRealInterestRate = new JLabel("Real interest rate (%) :");
		lblRealInterestRate.setBounds(25, 326, 113, 14);
		getContentPane().add(lblRealInterestRate);

		lblRealInterestRateValue = new JLabel("");
		lblRealInterestRateValue.setBounds(154, 326, 67, 14);
		getContentPane().add(lblRealInterestRateValue);
		
		JLabel lblMaxPeriod = new JLabel("Max. Period (months) :");
		lblMaxPeriod.setBounds(25, 277, 113, 14);
		getContentPane().add(lblMaxPeriod);
		
		maxPeriodTextField = new IntegerJTextField();
		maxPeriodTextField.setText("0");
		maxPeriodTextField.setBounds(179, 274, 86, 20);
		getContentPane().add(maxPeriodTextField);
		maxPeriodTextField.setColumns(10);

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

	public Integer getMinValue() {
		return Integer.valueOf(minValueTextField.getText());
	}

	public Integer getMaxValue() {
		return Integer.valueOf(maxValueTextField.getText());
	}

	public Double getInterestRate() {
		return Double.valueOf(interestRateTextField.getText());
	}

	public ICurrency getCurrency() {
		return (ICurrency) currencyComboBox.getSelectedItem();
	}

	public Integer getPeriod() {
		return Integer.valueOf(minPeriodTextField.getText());
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

	public Integer getMinPeriod() {
		return Integer.valueOf(minPeriodTextField.getText());
	}
	
	public Integer getMaxPeriod() {
		return Integer.valueOf(maxPeriodTextField.getText());
	}
}
