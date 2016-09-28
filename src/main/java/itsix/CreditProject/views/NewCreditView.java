package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.customs.DoubleJTextField;
import itsix.CreditProject.customs.ProductList;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IProduct;

public class NewCreditView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<IProduct> productsList;
	private JTextPane descriptionTextPane;

	private INewCreditController newCreditController;
	private IAccountController accountController;

	private JTextField moneyTextField;
	private JTextField periodTextField;

	private JButton btnMakeCredit;
	private JLabel lblName;
	private JTextField nameTextField;

	public NewCreditView(INewCreditController newCreditController, IAccountController accountController) {
		this.newCreditController = newCreditController;
		this.accountController = accountController;
		initialize();
	}

	private void initialize() {

		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(200, 11, 61, 14);
		getContentPane().add(lblDescription);

		descriptionTextPane = new JTextPane();
		descriptionTextPane.setEditable(false);
		descriptionTextPane.setBounds(200, 36, 269, 264);

		getContentPane().add(descriptionTextPane);

		JScrollPane creditsScrollPane = new JScrollPane();
		creditsScrollPane.setBounds(48, 36, 133, 353);
		getContentPane().add(creditsScrollPane);

		productsList = new JList<>();
		creditsScrollPane.setViewportView(productsList);

		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		productsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!productsList.isSelectionEmpty()) {
					newCreditController.setDescriptionText(getSelectedProductDescription());
				}
			}
		});

		JLabel lblMoney = new JLabel("Money :");
		lblMoney.setBounds(220, 357, 46, 14);
		getContentPane().add(lblMoney);

		JLabel lblPeriod = new JLabel("Period :");
		lblPeriod.setBounds(220, 392, 46, 14);
		getContentPane().add(lblPeriod);

		moneyTextField = new DoubleJTextField();
		moneyTextField.setBounds(294, 354, 110, 20);
		getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);
		moneyTextField.setText("0");

		periodTextField = new DoubleJTextField();
		periodTextField.setBounds(294, 389, 110, 20);
		getContentPane().add(periodTextField);
		periodTextField.setColumns(10);
		periodTextField.setText("0");

		btnMakeCredit = new JButton("Make Credit");
		btnMakeCredit.setBounds(174, 437, 120, 23);
		getContentPane().add(btnMakeCredit);
		btnMakeCredit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newCreditController.makeCredit();
				accountController.updateFields();
			}
		});

		lblName = new JLabel("Name :");
		lblName.setBounds(220, 322, 46, 14);
		getContentPane().add(lblName);

		nameTextField = new JTextField();
		nameTextField.setBounds(294, 319, 110, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	public String getSelectedProductDescription() {
		IProduct selectedProduct = productsList.getSelectedValue();
		if (selectedProduct != null) {
			return "Type : " + selectedProduct.getType() + "\n" + selectedProduct.getDescription();
		}
		return "";
	}

	public void setDescription(String description) {
		descriptionTextPane.setText(description);
	}

	public void clearDescription() {
		descriptionTextPane.setText("");
	}

	public String getCreditName() {
		return nameTextField.getText();
	}

	public Double getMoney() {
		return Double.valueOf(moneyTextField.getText());
	}

	public MutableDouble getInterestRate() {
		return productsList.getSelectedValue().getInterestRate();
	}

	public Integer getPeriod() {
		return Integer.valueOf(periodTextField.getText());
	}

	public IProduct getSelectedProduct() {
		return productsList.getSelectedValue();
	}

	public void show(IAccount account) {
		setVisible(true);
		newCreditController.setAccount(account);
		AbstractListModel<IProduct> model = new ProductList(newCreditController.getCreditsList());
		productsList.setModel(model);
		productsList.setSelectedIndex(0);
		descriptionTextPane.setText(getSelectedProductDescription());
	}

	public void clear() {
		moneyTextField.setText("0");
		nameTextField.setText("0");
		periodTextField.setText("0");
	}

}
