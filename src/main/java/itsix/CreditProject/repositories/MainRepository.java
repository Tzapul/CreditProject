package itsix.CreditProject.repositories;

public class MainRepository implements IRepository {

	private static final long serialVersionUID = 1L;
	
	private IProductRepository productRepository;
	private ICurrencyRepository currencyRepository;
	private IClientRepository clientRepository;

	private IIndicator indicator;
	private Integer currentDay;
	
	public MainRepository(IProductRepository productRepository, ICurrencyRepository currencyRepository,
			IIndicator indicator, IClientRepository clientRepository, Integer currentDay) {
		this.productRepository = productRepository;
		this.currencyRepository = currencyRepository;
		this.indicator = indicator;
		this.clientRepository = clientRepository;
		this.currentDay = currentDay;
	}

	@Override
	public Double getIndicator() {
		return indicator.getValue();
	}

	@Override
	public IProductRepository getProductRepository() {
		return productRepository;
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
		return currentDay;
	}

	@Override
	public void addDays(Integer days) {
		currentDay += days;
		for (int i = 0; i < days; i++) {
			clientRepository.passDay();
		}
	}

}