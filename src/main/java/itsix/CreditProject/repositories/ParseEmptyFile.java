package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.implementations.AccountBuilder;
import itsix.CreditProject.builders.implementations.CurrencyBuilder;
import itsix.CreditProject.builders.interfaces.IAccountBuilder;
import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;

public class ParseEmptyFile implements IParser {

	@Override
	public IRepository parse() {

		ICurrencyRepository currencyRepository = new CurrencyRepository();

		IIndicator indicator = new Indicator(2.5);

		IClientRepository clientRepository = initializeClientRepository();

		IProductRepository productRepository = initializeProductRepository();

		return new MainRepository(productRepository, currencyRepository, indicator, clientRepository, 0);
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

}
