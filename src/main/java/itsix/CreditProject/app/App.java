package itsix.CreditProject.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import itsix.CreditProject.builders.implementations.AccountBuilder;
import itsix.CreditProject.builders.implementations.ClientBuilder;
import itsix.CreditProject.builders.implementations.CurrencyBuilder;
import itsix.CreditProject.builders.implementations.FixedInterestProductBuilder;
import itsix.CreditProject.builders.implementations.IntervalBuilder;
import itsix.CreditProject.builders.implementations.OperationBuilder;
import itsix.CreditProject.builders.implementations.PaymentBuilder;
import itsix.CreditProject.builders.implementations.VariableInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IAccountBuilder;
import itsix.CreditProject.builders.interfaces.IClientBuilder;
import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.builders.interfaces.IFixedInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.builders.interfaces.IVariableInterestProductBuilder;
import itsix.CreditProject.controllers.implementation.AccountController;
import itsix.CreditProject.controllers.implementation.ClientsController;
import itsix.CreditProject.controllers.implementation.DaysController;
import itsix.CreditProject.controllers.implementation.EditFixedProductController;
import itsix.CreditProject.controllers.implementation.EditVariableProductController;
import itsix.CreditProject.controllers.implementation.NewAccountController;
import itsix.CreditProject.controllers.implementation.NewClientController;
import itsix.CreditProject.controllers.implementation.NewProductController;
import itsix.CreditProject.controllers.implementation.ProductsController;
import itsix.CreditProject.controllers.implementation.StartingController;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.IDaysController;
import itsix.CreditProject.controllers.interfaces.IEditFixedProductController;
import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.controllers.interfaces.INewClientController;
import itsix.CreditProject.controllers.interfaces.INewProductController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.implementations.VariableInterestProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.ClientRepository;
import itsix.CreditProject.repositories.CurrencyRepository;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.repositories.IIndicator;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.Indicator;
import itsix.CreditProject.repositories.MainRepository;
import itsix.CreditProject.repositories.ProductRepository;
import itsix.CreditProject.validator.ClientValidator;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.DaysView;
import itsix.CreditProject.views.EditFixedProductView;
import itsix.CreditProject.views.EditVariableProductView;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.NewAccountView;
import itsix.CreditProject.views.NewClientView;
import itsix.CreditProject.views.NewProductView;
import itsix.CreditProject.views.ProductsView;
import itsix.CreditProject.views.StartingView;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// Initializing repositories
				ICurrencyRepository currencyRepository = new CurrencyRepository();

				IIndicator indicator = new Indicator(2.5);

				IClientRepository clientRepository = initializeClientRepository();

				IProductRepository productRepository = initializeProductRepository();

				IRepository mainRepository = initializeMainRepository(currencyRepository, indicator, clientRepository,
						productRepository);

				productRepository.insertCredits(mainRepository);

				//
				IClientBuilder clientBuilder = new ClientBuilder(clientRepository);

				// initializing map for editViews
				IProductValidator productValidator = initializeProductValidator();
				Map<Class<?>, IEditProductView> editViews = new HashMap<>();
				initializeMap(editViews, productValidator, mainRepository);

				// Initializing new product view and controller
				INewProductController newProductController = initializeNewProductController(mainRepository,
						productValidator);
				NewProductView newProductView = new NewProductView(newProductController);
				newProductController.setView(newProductView);

				// Initializing products view and controller
				ProductsController productsController = new ProductsController(mainRepository, editViews,
						newProductView);
				ProductsView productsView = new ProductsView(productsController, mainRepository);
				productsController.setView(productsView);

				newProductView.setProductsController(productsController);

				// Initializing client validator
				IClientValidator clientValidator = initializeClientValidator();

				// Initializing new client view and controller
				INewClientController newClientController = new NewClientController(clientRepository, clientBuilder,
						clientValidator);
				NewClientView newClientView = new NewClientView(newClientController);
				newClientController.setView(newClientView);

				// Initializing new account view and controller
				IOpertationBuilder operationBuilder = new OperationBuilder();

				IPaymentBuilder paymentBuilder = new PaymentBuilder();

				IAccountController accountController = new AccountController(null, null, mainRepository,
						operationBuilder, paymentBuilder);
				AccountView accountView = new AccountView(accountController);
				accountController.setView(accountView);

				// Initializing new account controller
				INewAccountController newAccountController = new NewAccountController(null, currencyRepository);
				NewAccountView newAccountView = new NewAccountView(newAccountController);
				newAccountController.setView(newAccountView);

				// Initializing clients view and controller
				IClientsController clientsController = new ClientsController(currencyRepository, mainRepository,
						clientValidator, clientBuilder, newClientView, accountView, newAccountView);
				ClientView clientsView = new ClientView(clientsController);
				clientsController.setView(clientsView);
				newAccountView.setClientsController(clientsController);

				// Initializing starting view and controller
				IStartingController startingController = new StartingController(productsView, clientsView);
				StartingView view = new StartingView(startingController);
				view.setVisible(true);

				// Initializing days view and controller
				IDaysController daysController = new DaysController(mainRepository);
				DaysView daysView = new DaysView(daysController);
				daysView.setVisible(true);

			}

			public INewProductController initializeNewProductController(IRepository mainRepository,
					IProductValidator productValidator) {
				IIntervalBuilder intervalBuilder = new IntervalBuilder();
				IFixedInterestProductBuilder fixedInterestBuilder = new FixedInterestProductBuilder(intervalBuilder);
				IVariableInterestProductBuilder variableInterestBuilder = new VariableInterestProductBuilder(
						intervalBuilder, mainRepository);

				INewProductController newProductController = new NewProductController(mainRepository,
						fixedInterestBuilder, variableInterestBuilder, productValidator);
				return newProductController;
			}

			public IRepository initializeMainRepository(ICurrencyRepository currencyRepository, IIndicator indicator,
					IClientRepository clientRepository, IProductRepository creditRepository) {
				IRepository mainRepository = new MainRepository(creditRepository, currencyRepository, indicator,
						clientRepository, 1);
				return mainRepository;
			}

			public IProductRepository initializeProductRepository() {
				List<ISubscriber> subscribers = new ArrayList<>();
				IInnerPublisher publisher = new Publisher(subscribers);
				IProductRepository creditRepository = new ProductRepository(publisher);
				return creditRepository;
			}

			public IClientRepository initializeClientRepository() {
				ICurrencyBuilder currencyBuilder = new CurrencyBuilder();
				IAccountBuilder accountBuilder = new AccountBuilder(currencyBuilder);
				IClientRepository clientRepository = new ClientRepository(accountBuilder);
				return clientRepository;
			}

			private IProductValidator initializeProductValidator() {

				IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
				StringBuilder errorMessageBuilder = new StringBuilder();
				IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

				IProductValidator productValidator = new ProductValidator(validator);
				return productValidator;
			}

			private void initializeMap(Map<Class<?>, IEditProductView> editViews, IProductValidator productValidator,
					IRepository mainRepository) {

				IEditVariableProductController editVariableController = new EditVariableProductController(
						mainRepository, productValidator);
				IEditProductView editVariableView = new EditVariableProductView(editVariableController);
				editVariableController.setView(editVariableView);
				editViews.put(VariableInterestProduct.class, editVariableView);

				IEditFixedProductController editFixedController = new EditFixedProductController(
						mainRepository.getCurrencyRepository(), mainRepository.getProductRepository(),
						productValidator);
				IEditProductView editFixedView = new EditFixedProductView(editFixedController);
				editFixedController.setView(editFixedView);
				editViews.put(FixedInterestProduct.class, editFixedView);
			}

			public IClientValidator initializeClientValidator() {

				StringBuilder errorMessageBuilder = new StringBuilder();
				IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
				IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
				IClientValidator clientValidator = new ClientValidator(validator);

				return clientValidator;
			}
		});
	}
}