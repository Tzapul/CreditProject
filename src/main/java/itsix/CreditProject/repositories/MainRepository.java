package itsix.CreditProject.repositories;

import itsix.CreditProject.controllers.interfaces.IRepository;

public class MainRepository implements IRepository {

	private IProductRepository creditRepository;
	private ICurrencyRepository currencyRepository;
	private IClientRepository clientRepository;

	private IIndicator indicator;
	private Integer currentDate;
	
	public MainRepository(IProductRepository creditRepository, ICurrencyRepository currencyRepository,
			IIndicator indicator, IClientRepository clientRepository, Integer currentDate) {
		this.creditRepository = creditRepository;
		this.currencyRepository = currencyRepository;
		this.indicator = indicator;
		this.clientRepository = clientRepository;
		this.currentDate = currentDate;
	}

	@Override
	public Double getIndicator() {
		return indicator.getValue();
	}

	@Override
	public IProductRepository getProductRepository() {
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

	@Override
	public Integer getCurrentDay() {
		return currentDate;
	}

}