package itsix.CreditProject.controllers.implementation;

import java.util.Map;

import itsix.CreditProject.controllers.interfaces.IClientController;
import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.validator.ClientValidator;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private IRepository repository;

	private Map<Class<?>, IEditProductView> editViews;

	public StartingController(IRepository repository, Map<Class<?>, IEditProductView> editViews) {
		this.repository = repository;
		this.editViews = editViews;
	}

	@Override
	public void goToProductsWindow() {

		IProductsController productController = new ProductsController(repository, editViews);
		ProductsView creditView = new ProductsView(productController, repository);

		productController.setView(creditView);

		creditView.setVisible(true);
	}

	@Override
	public void goToClientsWindow() {

		IClientValidator clientValidator = initializeCreditValidator();

		IClientController controller = new ClientController(repository.getCurrencyRepository(), repository,
				clientValidator);
		ClientView clientView = new ClientView(controller);
		
		clientView.setVisible(true);
		controller.setView(clientView);
	}

	public IClientValidator initializeCreditValidator() {

		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		IClientValidator clientValidator = new ClientValidator(validator);

		return clientValidator;
	}

}
