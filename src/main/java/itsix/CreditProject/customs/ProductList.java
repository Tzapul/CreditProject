package itsix.CreditProject.customs;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.models.interfaces.IProduct;

public class ProductList extends AbstractListModel<IProduct> {

	private static final long serialVersionUID = 1L;

	private List<IProduct> products;

	public ProductList(List<IProduct> products) {
		super();
		this.products = products;
	}

	@Override
	public IProduct getElementAt(int index) {
		return products.get(index);
	}

	@Override
	public int getSize() {
		return products.size();
	}

}
