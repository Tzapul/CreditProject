package itsix.CreditProject.controllers.implementation;

import java.util.HashMap;
import java.util.Map;

import itsix.CreditProject.controllers.interfaces.IClientController;
import itsix.CreditProject.controllers.interfaces.IEditFixedProductController;
import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.implementations.VariableInterestProduct;
import itsix.CreditProject.validator.ClientValidator;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.EditFixedProductView;
import itsix.CreditProject.views.EditVariableProductView;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private IRepository repository;

	public StartingController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void goToProductsWindow() {

		Map<Class<?>, IEditProductView> editViews = new HashMap<>();

		IProductValidator productValidator = initializeProductValidator();

		initializeMap(editViews, productValidator);

		IProductsController controller = new ProductsController(repository, editViews);
		ProductsView creditView = new ProductsView(controller, repository);

		controller.setView(creditView);

		creditView.setVisible(true);
	}

	@Override
	public void goToClientsWindow() {

		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		IClientValidator clientValidator = new ClientValidator(validator);

		IClientController controller = new ClientController(repository.getCurrencyRepository(), repository,
				clientValidator);
		ClientView clientView = new ClientView(controller);
		clientView.setVisible(true);
		controller.setView(clientView);
	}

	private IProductValidator initializeProductValidator() {

		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

		IProductValidator productValidator = new ProductValidator(validator);
		return productValidator;
	}

	private void initializeMap(Map<Class<?>, IEditProductView> editViews, IProductValidator productValidator) {

		IEditVariableProductController editVariableController = new EditVariableProductController(repository,
				productValidator);
		IEditProductView editVariableView = new EditVariableProductView(editVariableController);
		editVariableController.setView(editVariableView);
		editViews.put(VariableInterestProduct.class, editVariableView);

		IEditFixedProductController editFixedController = new EditFixedProductController(
				repository.getCurrencyRepository(), repository.getProductRepository(), productValidator);
		IEditProductView editFixedView = new EditFixedProductView(editFixedController);
		editFixedController.setView(editFixedView);
		editViews.put(FixedInterestProduct.class, editFixedView);
	}

}
