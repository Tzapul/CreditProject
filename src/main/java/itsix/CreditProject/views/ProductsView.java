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
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.customs.ProductList;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.ISubscriber;

public class ProductsView extends JFrame implements ISubscriber {

	private static final long serialVersionUID = 1L;

	private JList<IProduct> productsList;
	private JTextPane descriptionTextPane;
	private IProductsController productsController;
	
	private JButton btnEditProduct;

	public ProductsView(IProductsController controller, IRepository repository) {
		this.productsController = controller;
		repository.getProductRepository().subscribe(this);
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
				productsController.goToNewProductView();
			}
		});
		setLayout(null);
		add(btnNewProduct);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(200, 11, 61, 14);
		add(lblDescription);

		descriptionTextPane = new JTextPane();
		descriptionTextPane.setEditable(false);
		descriptionTextPane.setBounds(200, 36, 269, 264);

		add(descriptionTextPane);

		btnEditProduct = new JButton("Edit Product");
		btnEditProduct.setBounds(245, 413, 100, 23);
		btnEditProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.goToEditProduct();
			}
		});
		add(btnEditProduct);

		JScrollPane creditsScrollPane = new JScrollPane();
		creditsScrollPane.setBounds(48, 36, 133, 353);
		add(creditsScrollPane);

		productsList = new JList<>();
		creditsScrollPane.setViewportView(productsList);

		AbstractListModel<IProduct> model = new ProductList(productsController.getCreditsList());
		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsList.setModel(model);
		productsList.setSelectedIndex(0);
		descriptionTextPane.setText(getSelectedProductDescription());
		productsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!productsList.isSelectionEmpty()) {
					productsController.setDescriptionText(getSelectedProductDescription());
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(120, 413, 100, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.clearDescription();
				productsController.delete(productsList.getSelectedValue());
				productsController.toggleEditButton();
			}
		});
		
		productsController.toggleEditButton();

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
				setVisible(false);
			}
		});
	}

	public String getSelectedProductDescription() {
		IProduct selectedProduct = productsList.getSelectedValue();
		return "Type : " + selectedProduct.getType() + "\n" + selectedProduct.getDescription();
	}

	@Override
	public void update() {
		AbstractListModel<IProduct> model = productsController.createCreditList();
		productsList.setModel(model);
		productsList.setSelectedIndex(0);
	}

	public void setDescription(String description) {
		descriptionTextPane.setText(description);
	}

	public JList<IProduct> getProductList() {
		return productsList;
	}

	public void clearDescription() {
		descriptionTextPane.setText("");
	}

	public IProduct getProductsListSelectedValue() {
		return productsList.getSelectedValue();
	}

	public void setEditDisabled() {
		btnEditProduct.setEnabled(false);
	}
	
	public void setEditEnabled() {
		btnEditProduct.setEnabled(true);
	}
}
