package itsix.CreditProject.controllers.implementation;

import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;

import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.customs.ProductList;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.NewProductView;
import itsix.CreditProject.views.ProductsView;

public class ProductsController implements IProductsController {

	private static final long serialVersionUID = 1L;

	private IRepository mainRepository;

	private ProductsView productsView;

	private NewProductView newProductsView;
	private IEditProductView editProductView;

	private Map<Class<?>, IEditProductView> editViews;

	public ProductsController(IRepository mainRepository, Map<Class<?>, IEditProductView> editViews,
			NewProductView newProductsView) {
		this.mainRepository = mainRepository;
		this.editViews = editViews;
		this.newProductsView = newProductsView;
	}

	@Override
	public void setView(ProductsView productsView) {
		this.productsView = productsView;
		toggleEditButton();
	}

	@Override
	public void goToNewProductView() {
		newProductsView.setVisible(true);
	}

	@Override
	public List<IProduct> getCreditsList() {
		return mainRepository.getProductRepository().getProducts();
	}

	@Override
	public void setDescriptionText(String description) {
		productsView.setDescription(description);
	}

	@Override
	public void goToEditProduct() {

		IProduct product = productsView.getProductsListSelectedValue();
		editProductView = editViews.get(product.getClass());

		editProductView.setControllerProduct(product);
		editProductView.setVisible(true);
	}

	@Override
	public void delete(IProduct credit) {
		mainRepository.getProductRepository().delete(credit);
	}

	@Override
	public void clearDescription() {
		productsView.clearDescription();
	}

	@Override
	public AbstractListModel<IProduct> createCreditList() {
		return new ProductList(mainRepository.getProductRepository().getProducts());
	}

	public IProductValidator initializeProductValidator() {

		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		IProductValidator creditValidator = new ProductValidator(validator);

		return creditValidator;
	}

	@Override
	public void toggleEditButton() {
		if (mainRepository.getProductRepository().hasNoCredits()) {
			productsView.setEditDisabled();
		} else {
			productsView.setEditEnabled();
		}
	}

}
