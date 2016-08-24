package itsix.CreditProject.controllers;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.views.ProductsView;

public interface ICreditsController {

	void goToNewCreditView();

	List<IProduct> getCreditsList();

	void setDescriptionText(String description);

	void setView(ProductsView view);

	void goToEditProduct();

	void delete(IProduct credit);

	void clearDescription();

	AbstractListModel<IProduct> createCreditList();
}
