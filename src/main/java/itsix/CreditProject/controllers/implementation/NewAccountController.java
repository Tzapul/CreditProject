package itsix.CreditProject.controllers.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.models.implementations.Account;
import itsix.CreditProject.models.implementations.Sold;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.ISold;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.views.NewAccountView;

public class NewAccountController implements INewAccountController {

	private IClient currentClient;

	private NewAccountView view;

	private ICurrencyRepository currencyRepository;

	public NewAccountController(IClient client, ICurrencyRepository currencyRepository) {
		this.currentClient = client;
		this.currencyRepository = currencyRepository;
	}

	@Override
	public void createNewAccount() {
		List<ICredit> credits = new ArrayList<>();
		ISold sold = new Sold(view.getSold());
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);

		IAccount account = new Account(view.getCurrency(), credits, sold, publisher);

		currentClient.addAccount(account);
		view.dispose();
	}

	@Override
	public void setView(NewAccountView view) {
		this.view = view;
	}

	@Override
	public Vector<ICurrency> getRemainingCurrencies() {
		return currencyRepository.getRemainingCurrencies(currentClient.getCurrencies());
	}

	@Override
	public void setClient(IClient currentClient) {
		this.currentClient = currentClient;
	}

}
