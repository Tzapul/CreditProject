package itsix.CreditProject.models;

import itsix.CreditProject.builders.CreditBuilder;
import itsix.CreditProject.builders.ICreditBuilder;
import itsix.CreditProject.builders.IIntervalBuilder;
import itsix.CreditProject.builders.IntervalBuilder;

public class VariableInterestCredit implements ICredit {

	private ICredit credit;
	
	private IIntervalBuilder intervalBuilder = new IntervalBuilder();
	private ICreditBuilder creditBuilder = new CreditBuilder(intervalBuilder);
	
	public VariableInterestCredit(String name, IInterval interval, Double interestRate, ICurrency currency, Integer period) {
		this.credit = creditBuilder.build(name, interval.getMin(), interval.getMax(), interestRate, currency, period);
	}

	@Override
	public String getDescription() {
		return credit.getDescription();
	}

	@Override
	public String getName() {
		return credit.getName();
	}

	@Override
	public Double getMinValue() {
		return credit.getMinValue();
	}

	@Override
	public Double getMaxValue() {
		return credit.getMaxValue();
	}

	@Override
	public Double getInterestRate() {
		return credit.getInterestRate();
	}

	@Override
	public ICurrency getCurrency() {
		return credit.getCurrency();
	}

	@Override
	public Integer getPeriod() {
		return credit.getPeriod();
	}
	
	@Override
	public String toString() {
		return credit.getName();
	}

	@Override
	public boolean hasCurrency(ICurrency currency) {
		return credit.hasCurrency(currency);
	}

	@Override
	public void updateFields(ICredit credit) {
		this.credit.updateFields(credit);
	}

	@Override
	public IInterval getInterval() {
		return credit.getInterval();
	}
}
