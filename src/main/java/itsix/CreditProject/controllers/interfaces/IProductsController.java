package itsix.CreditProject.controllers.interfaces;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.views.ProductsView;

public interface IProductsController {

	void goToNewProductView();

	List<IProduct> getCreditsList();

	void setDescriptionText(String description);

	void setView(ProductsView view);

	void goToEditProduct();

	void delete(IProduct credit);

	void clearDescription();

	AbstractListModel<IProduct> createCreditList();

	void toggleEditButton();
}
