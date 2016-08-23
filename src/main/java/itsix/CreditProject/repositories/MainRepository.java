package itsix.CreditProject.repositories;

import itsix.CreditProject.controllers.IRepository;

public class MainRepository implements IRepository {

	private ICreditRepository creditRepository;
	private ICurrencyRepository currencyRepository;
	private IClientRepository clientRepository;

	private IIndicator indicator;

	public MainRepository(ICreditRepository creditRepository, ICurrencyRepository currencyRepository,
			IIndicator indicator, IClientRepository clientRepository) {
		this.creditRepository = creditRepository;
		this.currencyRepository = currencyRepository;
		this.indicator = indicator;
		this.clientRepository = clientRepository;
	}

	@Override
	public Double getIndicator() {
		return indicator.getValue();
	}

	@Override
	public ICreditRepository getCreditRepository() {
		return creditRepository;
	}

	@Override
	public ICurrencyRepository getCurrencyRepository() {
		return currencyRepository;
	}

	@Override
	public IClientRepository getClientRepository() {
		return clientRepository;
	}

}