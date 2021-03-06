package itsix.CreditProject.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.CreditProject.builders.implementations.ClientBuilder;
import itsix.CreditProject.builders.implementations.CreditBuilder;
import itsix.CreditProject.builders.implementations.DaysPeriodBuilder;
import itsix.CreditProject.builders.implementations.FixedInterestProductBuilder;
import itsix.CreditProject.builders.implementations.IntervalBuilder;
import itsix.CreditProject.builders.implementations.MoneyBuilder;
import itsix.CreditProject.builders.implementations.OperationBuilder;
import itsix.CreditProject.builders.implementations.PaymentBuilder;
import itsix.CreditProject.builders.implementations.RateBuilder;
import itsix.CreditProject.builders.implementations.VariableInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IClientBuilder;
import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.builders.interfaces.IFixedInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.builders.interfaces.IVariableInterestProductBuilder;
import itsix.CreditProject.controllers.implementation.AccountController;
import itsix.CreditProject.controllers.implementation.ClientsController;
import itsix.CreditProject.controllers.implementation.CreditController;
import itsix.CreditProject.controllers.implementation.DaysController;
import itsix.CreditProject.controllers.implementation.EditFixedProductController;
import itsix.CreditProject.controllers.implementation.EditVariableProductController;
import itsix.CreditProject.controllers.implementation.NewAccountController;
import itsix.CreditProject.controllers.implementation.NewClientController;
import itsix.CreditProject.controllers.implementation.NewCreditController;
import itsix.CreditProject.controllers.implementation.NewProductController;
import itsix.CreditProject.controllers.implementation.ProductsController;
import itsix.CreditProject.controllers.implementation.StartingController;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.controllers.interfaces.IDaysController;
import itsix.CreditProject.controllers.interfaces.IEditFixedProductController;
import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.controllers.interfaces.INewClientController;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.controllers.interfaces.INewProductController;
import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.customs.IKahanCalculator;
import itsix.CreditProject.customs.KahanCalculator;
import itsix.CreditProject.customs.RepositoryParser;
import itsix.CreditProject.dispatcher.FixedProductDispatcher;
import itsix.CreditProject.dispatcher.IDispatcher;
import itsix.CreditProject.dispatcher.VariableProductDispatcher;
import itsix.CreditProject.models.implementations.CashPayment;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.implementations.SoldPayment;
import itsix.CreditProject.models.implementations.VariableInterestProduct;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.repositories.IParser;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.validator.ClientValidator;
import itsix.CreditProject.validator.CreditValidator;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.CreditView;
import itsix.CreditProject.views.DaysView;
import itsix.CreditProject.views.EditFixedProductView;
import itsix.CreditProject.views.EditVariableProductView;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.NewAccountView;
import itsix.CreditProject.views.NewClientView;
import itsix.CreditProject.views.NewCreditView;
import itsix.CreditProject.views.NewProductView;
import itsix.CreditProject.views.ProductsView;
import itsix.CreditProject.views.StartingView;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				IParser parser = new RepositoryParser();
				// Initializing repositories

				IRepository mainRepository = null;
				try {
					mainRepository = parser.parse();
				} catch (ClassNotFoundException | NullPointerException | IOException e) {
					e.printStackTrace();
				}
				
				IClientRepository clientRepository = mainRepository.getClientRepository();
				ICurrencyRepository currencyRepository = mainRepository.getCurrencyRepository();

				// Initializing clientBuilder
				IClientBuilder clientBuilder = new ClientBuilder(clientRepository);

				// Initializing creditBuilder
				ICreditBuilder creditBuilder = initializeCreditBuilder();

				// Initializing creditValidator
				ICreditValidator creditValidator = initializeCreditValidator();

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

				// Initializing credit view and controller

				IPayment soldPayment = new SoldPayment();
				IPayment cashPayment = new CashPayment();

				ICreditController creditController = new CreditController(cashPayment, soldPayment);
				CreditView creditView = new CreditView(creditController);
				creditController.setView(creditView);

				// Initializing new account view and controller
				IOpertationBuilder operationBuilder = new OperationBuilder();

				IPaymentBuilder paymentBuilder = new PaymentBuilder();

				// Initializing map for dispatchers

				Map<Class<?>, IDispatcher> dispatchers = new HashMap<>();

				dispatchers.put(FixedInterestProduct.class, new FixedProductDispatcher());
				dispatchers.put(VariableInterestProduct.class, new VariableProductDispatcher());

				// Initializing make credit controller and view
				INewCreditController newCreditController = new NewCreditController(mainRepository, creditBuilder,
						creditValidator, dispatchers);

				// Initializing account controller and view
				IAccountController accountController = new AccountController(mainRepository, operationBuilder,
						paymentBuilder, creditView);
				AccountView accountView = new AccountView(accountController);
				accountController.setView(accountView);

				NewCreditView newCreditView = new NewCreditView(newCreditController, accountController);
				newCreditController.setView(newCreditView);
				newCreditController.setAccountView(accountView);
				accountController.setNewCreditView(newCreditView);

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

				RepositoryParser serializer = new RepositoryParser();

				// Initializing starting view and controller
				IStartingController startingController = new StartingController(productsView, clientsView, serializer,
						mainRepository);
				StartingView view = new StartingView(startingController);
				view.setVisible(true);

				// Initializing days view and controller
				IDaysController daysController = new DaysController(mainRepository);
				DaysView daysView = new DaysView(daysController);
				daysView.setVisible(true);
				

			}

			public ICreditBuilder initializeCreditBuilder() {

				IRateBuilder feeBuilder = new RateBuilder();
				IMoneyBuilder moneyBuilder = new MoneyBuilder();
				IPeriodBuilder periodBuilder = new DaysPeriodBuilder();
				ICreditBuilder creditBuilder = new CreditBuilder(moneyBuilder, periodBuilder, feeBuilder);

				return creditBuilder;
			}

			public ICreditValidator initializeCreditValidator() {

				StringBuilder errorMessageBuilder = new StringBuilder();
				IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
				IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
				ICreditValidator creditValidator = new CreditValidator(validator);

				return creditValidator;
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

			private IProductValidator initializeProductValidator() {

				IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
				StringBuilder errorMessageBuilder = new StringBuilder();
				IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

				IProductValidator productValidator = new ProductValidator(validator);
				return productValidator;
			}

			private void initializeMap(Map<Class<?>, IEditProductView> editViews, IProductValidator productValidator,
					IRepository mainRepository) {

				IKahanCalculator kahanCalculator = new KahanCalculator();
				
				IEditVariableProductController editVariableController = new EditVariableProductController(
						mainRepository, productValidator, kahanCalculator);
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