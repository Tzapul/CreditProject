package itsix.CreditProject.controllers.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.IClientBuilder;
import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
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

	private ClientView clientView;
	private NewClientView newClientView;
	private AccountView accountView;
	private NewAccountView newAccountView;

	private IClient currentClient;

	private IClientValidator clientValidator;

	private IClientBuilder clientBuilder;

	public ClientsController(ICurrencyRepository currencyRepository, IRepository repository,
			IClientValidator clientValidator, IClientBuilder clientBuilder, NewClientView newClientView,
			AccountView accountView, NewAccountView newAccountView) {
		this.currencyRepository = currencyRepository;
		this.repository = repository;
		this.clientValidator = clientValidator;
		this.clientBuilder = clientBuilder;
		this.newClientView = newClientView;
		this.accountView = accountView;
		this.newAccountView = newAccountView;
	}

	public void setView(ClientView clientView) {
		this.clientView = clientView;
	}

	@Override
	public void searchForClient() {

		try {
			currentClient = repository.getClientRepository().searchForClient(clientView.getSearchSSN());

			clientView.subscribe();
			clientView.paintClient(currentClient);
			clientView.clearSearchTextField();
			clientView.setUpdateClientEnabled();

		} catch (Exception e) {
			clientView.resetClient();
			JOptionPane.showMessageDialog(null, "Client not found!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void updateClient() {

		IClient updatedClient = clientBuilder.build(clientView.getsSN(), clientView.getFirstname(),
				clientView.getLastname(), clientView.getAddress(), currentClient.getPublisher());

		IValidatorResult result = clientValidator.validateFields(updatedClient);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		currentClient.update(updatedClient.getFirstname(), updatedClient.getLastname(), updatedClient.getAddress());
		JOptionPane.showMessageDialog(null, "Client was successfully updated!", "Client Updated",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void goToNewClientView() {
		newClientView.setVisible(true);
	}

	@Override
	public void goToAccountView() {

		IAccount account = clientView.getSelectedAccount();

		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);

		account.setPublisher(publisher);
		account.subscribe(clientView);

		accountView.show(currentClient, account);

	}

	@Override
	public void goToNewAccountView() {
		newAccountView.show(currentClient);
	}

	@Override
	public void updateTableModel() {
		clientView.paintClient(currentClient);
	}

	@Override
	public IClient getCurrentClient() {
		return currentClient;
	}

	@Override
	public void hasAllAccounts() {
		if (currencyRepository.hasAllCurrenciesOf(currentClient)) {
			clientView.setNewAccountDisabled();
		} else {
			clientView.setNewAccountEnabled();
		}
	}

}
