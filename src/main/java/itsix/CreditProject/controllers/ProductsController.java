package itsix.CreditProject.controllers;

import java.util.List;

import javax.swing.AbstractListModel;

import itsix.CreditProject.builders.FixedInterestProductBuilder;
import itsix.CreditProject.builders.IFixedInterestProductBuilder;
import itsix.CreditProject.builders.IIntervalBuilder;
import itsix.CreditProject.builders.IVariableInterestProductBuilder;
import itsix.CreditProject.builders.IntervalBuilder;
import itsix.CreditProject.builders.VariableInterestProductBuilder;
import itsix.CreditProject.customs.CreditsList;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.ProductsView;
import itsix.CreditProject.views.EditProductView;
import itsix.CreditProject.views.NewProductView;

public class ProductsController implements ICreditsController {

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
	public void goToNewCreditView() {

		// Initialize interval and credit builders
		IIntervalBuilder intervalBuilder = new IntervalBuilder();
		IFixedInterestProductBuilder fixedInterestBuilder = new FixedInterestProductBuilder(intervalBuilder);
		IVariableInterestProductBuilder variableInterestBuilder = new VariableInterestProductBuilder(intervalBuilder, repository);

		// Initializing creditValidator
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

		IProductValidator creditValidator = new ProductValidator(validator);

		INewCreditController controller = new NewCreditController(repository, fixedInterestBuilder,
				variableInterestBuilder, creditValidator);
		NewProductView newCreditView = new NewProductView(controller);

		controller.setView(newCreditView);
		newCreditView.setVisible(true);
	}

	@Override
	public List<IProduct> getCreditsList() {
		return repository.getCreditRepository().getProducts();
	}

	@Override
	public void setDescriptionText(String description) {
		view.setDescription(description);
	}

	@Override
	public void goToEditProduct() {

		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

		IProductValidator creditValidator = new ProductValidator(validator);

		IEditCreditController editCreditController = new EditCreditController(repository.getCurrencyRepository(),
				repository.getCreditRepository(), view.getProductList().getSelectedValue(), creditValidator);

		EditProductView editCreditView = new EditProductView(editCreditController);
		editCreditController.setView(editCreditView);

		editCreditController.populateFields();

		editCreditView.setVisible(true);
	}

	@Override
	public void delete(IProduct credit) {
		repository.getCreditRepository().delete(credit);
	}

	@Override
	public void clearDescription() {
		view.clearDescription();
	}

	@Override
	public AbstractListModel<IProduct> createCreditList() {
		return new CreditsList(repository.getCreditRepository().getProducts());
	}

}
