package itsix.CreditProject.controllers.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import itsix.CreditProject.controllers.interfaces.INewAccountController;
import itsix.CreditProject.models.Account;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.IClient;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.ISold;
import itsix.CreditProject.models.Sold;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.views.NewAccountView;

public class NewAccountController implements INewAccountController {

	private IClient client;

	private NewAccountView view;

	private ICurrencyRepository currencyRepository;

	public NewAccountController(ICurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public void createNewAccount() {
		List<ICredit> credits = new ArrayList<>();
		ISold sold = new Sold(view.getSold());
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);

		IAccount account = new Account(view.getCurrency(), credits, sold, publisher);

		client.addAccount(account);
		view.dispose();
	}

	@Override
	public void setView(NewAccountView view) {
		this.view = view;
	}

	@Override
	public Vector<ICurrency> getRemainingCurrencies() {
		return currencyRepository.getRemainingCurrencies(client.getCurrencies());
	}

	@Override
	public void showWindow(IClient client) {
		this.client = client;
		view.setComboBoxModel();
		view.setVisible(true);
	}

}
