package itsix.CreditProject.controllers.implementations;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.customs.ProductList;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.views.ProductsView;

public class ProductsController implements IProductsController {

	private IRepository repository;

	private ProductsView view;

	public ProductsController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void setView(ProductsView view) {
		this.view = view;
	}

	@Override
	public List<IProduct> getCreditsList() {
		return repository.getProductRepository().getProducts();
	}

	@Override
	public void setDescriptionText(String description) {
		view.setDescription(description);
	}

	@Override
	public void delete(IProduct credit) {
		repository.getProductRepository().delete(credit);
	}

	@Override
	public void clearDescription() {
		view.clearDescription();
	}

	@Override
	public AbstractListModel<IProduct> createCreditList() {
		return new ProductList(repository.getProductRepository().getProducts());
	}

	@Override
	public void showWindow() {
		view.setVisible(true);
	}

}
