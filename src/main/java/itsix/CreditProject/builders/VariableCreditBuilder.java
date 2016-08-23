package itsix.CreditProject.builders;

import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.VariableInterestCredit;

public class VariableCreditBuilder implements IVariableCreditBuilder {

	private IIntervalBuilder intervalBuilder;
	
	private IRepository repository;
	
	public VariableCreditBuilder(IIntervalBuilder intervalBuilder, IRepository repository) {
		this.intervalBuilder = intervalBuilder;
		this.repository = repository;
	}
	
	@Override
	public ICredit build(String name, Double minValue, Double maxValue, Double interestRate, ICurrency currency,
			Integer period) {
		
		IInterval interval = intervalBuilder.build(minValue, maxValue);
		interestRate += repository.getIndicator();
		return new VariableInterestCredit(name, interval, interestRate, currency, period);
	}

}
