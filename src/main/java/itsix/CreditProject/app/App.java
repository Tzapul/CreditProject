package itsix.CreditProject.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import itsix.CreditProject.builders.implementations.AccountBuilder;
import itsix.CreditProject.builders.implementations.CurrencyBuilder;
import itsix.CreditProject.builders.interfaces.IAccountBuilder;
import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.controllers.implementation.DaysController;
import itsix.CreditProject.controllers.implementation.EditFixedProductController;
import itsix.CreditProject.controllers.implementation.EditVariableProductController;
import itsix.CreditProject.controllers.implementation.StartingController;
import itsix.CreditProject.controllers.interfaces.IDaysController;
import itsix.CreditProject.controllers.interfaces.IEditFixedProductController;
import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.controllers.interfaces.IRepository;
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
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.ProductValidator;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.DaysView;
import itsix.CreditProject.views.EditFixedProductView;
import itsix.CreditProject.views.EditVariableProductView;
import itsix.CreditProject.views.IEditProductView;
import itsix.CreditProject.views.StartingView;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ICurrencyRepository currencyRepository = new CurrencyRepository();

				IIndicator indicator = new Indicator(2.5);

				ICurrencyBuilder currencyBuilder = new CurrencyBuilder();
				IAccountBuilder accountBuilder = new AccountBuilder(currencyBuilder);
				IClientRepository clientRepository = new ClientRepository(accountBuilder);

				List<ISubscriber> subscribers = new ArrayList<>();
				IInnerPublisher publisher = new Publisher(subscribers);
				IProductRepository creditRepository = new ProductRepository(publisher);

				IRepository mainRepository = new MainRepository(creditRepository, currencyRepository, indicator,
						clientRepository, 1);

				creditRepository.insertCredits(mainRepository);

				Map<Class<?>, IEditProductView> editViews = new HashMap<>();

				IProductValidator productValidator = initializeProductValidator();

				initializeMap(editViews, productValidator, mainRepository);

				StartingController controller = new StartingController(mainRepository, editViews);
				StartingView view = new StartingView(controller);
				view.setVisible(true);

				IDaysController daysController = new DaysController(mainRepository);
				DaysView daysView = new DaysView(daysController);

				daysView.setVisible(true);

			}

			private IProductValidator initializeProductValidator() {

				IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
				StringBuilder errorMessageBuilder = new StringBuilder();
				IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

				IProductValidator productValidator = new ProductValidator(validator);
				return productValidator;
			}

			private void initializeMap(Map<Class<?>, IEditProductView> editViews, IProductValidator productValidator, IRepository mainRepository) {

				IEditVariableProductController editVariableController = new EditVariableProductController(mainRepository,
						productValidator);
				IEditProductView editVariableView = new EditVariableProductView(editVariableController);
				editVariableController.setView(editVariableView);
				editViews.put(VariableInterestProduct.class, editVariableView);

				IEditFixedProductController editFixedController = new EditFixedProductController(
						mainRepository.getCurrencyRepository(), mainRepository.getProductRepository(), productValidator);
				IEditProductView editFixedView = new EditFixedProductView(editFixedController);
				editFixedController.setView(editFixedView);
				editViews.put(FixedInterestProduct.class, editFixedView);
			}
		});
	}
}