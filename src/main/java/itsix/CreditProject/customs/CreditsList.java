package itsix.CreditProject.customs;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.models.ICredit;

public class CreditsList extends AbstractListModel<ICredit> {

	private static final long serialVersionUID = 1L;

	private List<ICredit> products;

	public CreditsList(List<ICredit> products) {
		super();
		this.products = products;
	}

	@Override
	public ICredit getElementAt(int index) {
		return products.get(index);
	}

	@Override
	public int getSize() {
		return products.size();
	}

}
