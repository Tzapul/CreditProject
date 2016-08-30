package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IVariableInterestProductBuilder;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.models.implementations.VariableInterestProduct;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;

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
