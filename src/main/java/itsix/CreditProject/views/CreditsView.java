package itsix.CreditProject.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import itsix.CreditProject.controllers.ICreditsController;
import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.customs.CreditsList;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.pubSub.ISubscriber;

public class CreditsView extends JFrame implements ISubscriber {

	private static final long serialVersionUID = 1L;

	private JList<ICredit> productsList;
	private JTextPane descriptionTextPane;
	private ICreditsController controller;

	public CreditsView(ICreditsController controller, IRepository repository) {
		this.controller = controller;
		repository.getCreditRepository().subscribe(this);
		initialize();
	}

	private void initialize() {

		setTitle("Products");
		setResizable(false);
		setBounds(100, 100, 500, 500);

		JButton btnNewProduct = new JButton("New Product");
		btnNewProduct.setBounds(369, 412, 100, 25);
		btnNewProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goToNewCreditView();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewProduct);
		AbstractListModel<ICredit> model = new CreditsList(controller.getCreditsList());

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(200, 11, 61, 14);
		getContentPane().add(lblDescription);

		descriptionTextPane = new JTextPane();
		descriptionTextPane.setEditable(false);
		descriptionTextPane.setBounds(200, 36, 269, 264);

		getContentPane().add(descriptionTextPane);

		JButton btnEditProduct = new JButton("Edit Product");
		btnEditProduct.setBounds(245, 413, 100, 23);
		btnEditProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goToEditProduct();
			}
		});
		getContentPane().add(btnEditProduct);

		JScrollPane creditsScrollPane = new JScrollPane();
		creditsScrollPane.setBounds(48, 36, 133, 353);
		getContentPane().add(creditsScrollPane);

		productsList = new JList<>();
		creditsScrollPane.setViewportView(productsList);

		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsList.setModel(model);
		productsList.setSelectedIndex(0);
		descriptionTextPane.setText(getSelectedProductDescription());
		productsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!productsList.isSelectionEmpty()) {
					controller.setDescriptionText(getSelectedProductDescription());
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(120, 413, 100, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clearDescription();
				controller.delete(productsList.getSelectedValue());
			}
		});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});

	}

	public String getSelectedProductDescription() {
		return productsList.getSelectedValue().getDescription();
	}

	@Override
	public void update() {
		AbstractListModel<ICredit> model = new CreditsList(controller.getCreditsList());
		productsList.setModel(model);
		productsList.setSelectedIndex(0);
	}

	public void setDescription(String description) {
		descriptionTextPane.setText(description);
	}

	public JList<ICredit> getProductList() {
		return productsList;
	}

	public void clearDescription() {
		descriptionTextPane.setText("");
	}
}
