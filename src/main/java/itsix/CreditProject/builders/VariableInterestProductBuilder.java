package itsix.CreditProject.builders;

import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.VariableInterestProduct;

public class VariableInterestProductBuilder implements IVariableInterestProductBuilder {

	private IIntervalBuilder intervalBuilder;
	
	private IRepository repository;
	
	public VariableInterestProductBuilder(IIntervalBuilder intervalBuilder, IRepository repository) {
		this.intervalBuilder = intervalBuilder;
		this.repository = repository;
	}
	
	@Override
	public IProduct build(String name, Integer minValue, Integer maxValue, Double interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod) {
		
		IInterval moneyInterval = intervalBuilder.buildPeriodInterval(minValue, maxValue);
		IInterval periodInterval = intervalBuilder.buildPeriodInterval(minPeriod, maxPeriod);
		interestRate += repository.getIndicator();
		return new VariableInterestProduct(name, moneyInterval, interestRate, currency, periodInterval);
	}

}
