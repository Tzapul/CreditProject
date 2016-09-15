package itsix.CreditProject.controllers.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.implementations.ClientBuilder;
import itsix.CreditProject.builders.implementations.OperationBuilder;
import itsix.CreditProject.builders.implementations.PaymentBuilder;
import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.IClientBuilder;
import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.controllers.interfaces.INewClientController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.models.implementations.Client;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.NewAccountView;
import itsix.CreditProject.views.NewClientView;

public class ClientsController implements IClientsController {

	private IRepository repository;

	private ICurrencyRepository currencyRepository;

	private ClientView view;

	private IClient currentClient;

	private IClientValidator clientValidator;

	public ClientsController(ICurrencyRepository currencyRepository, IRepository repository,
			IClientValidator clientValidator) {
		this.currencyRepository = currencyRepository;
		this.repository = repository;
		this.clientValidator = clientValidator;
	}

	public void setView(ClientView view) {
		this.view = view;
	}

	@Override
	public void searchForClient() {

		try {
			currentClient = repository.getClientRepository().searchForClient(view.getSearchSSN());

			view.subscribe();
			view.paintClient(currentClient);
			view.clearSearchTextField();
			view.setUpdateClientEnabled();

		} catch (Exception e) {
			view.resetClient();
			JOptionPane.showMessageDialog(null, "Client not found!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void updateClient() {

		IClient updatedClient = new Client(view.getsSN(), view.getFirstname(), view.getLastname(), view.getAddress(),
				null);

		IValidatorResult result = clientValidator.validateFields(updatedClient);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		currentClient.update(view.getFirstname(), view.getLastname(), view.getAddress());
		JOptionPane.showMessageDialog(null, "Client was successfully updated!", "Client Updated", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void goToNewClientView() {

		IClientBuilder clientBuilder = new ClientBuilder(repository.getClientRepository());

		INewClientController controller = new NewClientController(repository.getClientRepository(), clientBuilder,
				clientValidator);

		NewClientView newClientView = new NewClientView(controller);

		newClientView.setVisible(true);
		controller.setView(newClientView);

	}

	@Override
	public void goToAccountView() {

		IAccount account = view.getSelectedAccount();

		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		account.setPublisher(publisher);

		IOpertationBuilder operationBuilder = new OperationBuilder();

		IPaymentBuilder paymentBuilder = new PaymentBuilder();

		IAccountController controller = new AccountController(currentClient, account, repository, operationBuilder,
				paymentBuilder);

		AccountView accountView = new AccountView(controller);

		account.subscribe(view);
		accountView.setVisible(true);
		controller.setView(accountView);
		controller.updateFields();

	}

	@Override
	public void goToNewAccountView() {

		INewAccountController controller = new NewAccountController(currentClient, currencyRepository);

		NewAccountView view = new NewAccountView(controller, this);

		controller.setView(view);

		view.setVisible(true);

	}

	@Override
	public void updateTableModel() {
		view.paintClient(currentClient);
	}

	@Override
	public IClient getCurrentClient() {
		return currentClient;
	}

	@Override
	public void hasAllAccounts() {
		if (currencyRepository.hasAllCurrenciesOf(currentClient)) {
			view.setNewAccountDisabled();
		} else {
			view.setNewAccountEnabled();
		}
	}

}
