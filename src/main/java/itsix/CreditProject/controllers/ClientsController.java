package itsix.CreditProject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.ClientBuilder;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.validator.ClientValidator;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.ClientsView;
import itsix.CreditProject.views.NewAccountView;
import itsix.CreditProject.views.NewClientView;

public class ClientsController implements IClientsController {

	private IClientRepository clientRepository;

	private ICurrencyRepository currencyRepository;

	private ClientsView view;

	private IClient currentClient;

	public ClientsController(IClientRepository clientRepository, ICurrencyRepository currencyRepository) {
		this.clientRepository = clientRepository;
		this.currencyRepository = currencyRepository;
	}

	public void setView(ClientsView view) {
		this.view = view;
	}

	@Override
	public void searchForClient() {

		try {
			currentClient = clientRepository.searchForClient(view.getSearchSSN());

			view.subscribe();
			view.paintClient(currentClient);
			view.clearSearchTextField();
			view.setUpdateClientEnabled();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Client not found!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void updateClient() {
		currentClient.update(view.getFirstname(), view.getLastname(), view.getAddress());
	}

	@Override
	public void goToNewClientView() {

		IClientBuilder clientBuilder = new ClientBuilder(clientRepository);

		
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		IClientValidator clientValidator = new ClientValidator(validator);
		
		INewClientController controller = new NewClientController(clientRepository, clientBuilder, clientValidator);

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

		IAccountController controller = new AccountController(clientRepository, account);

		AccountView accountView = new AccountView(controller);

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
