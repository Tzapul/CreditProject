package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IRate;

public class Rate implements IRate {

	private Double rate;

	public Rate(Double rate) {
		this.rate = rate;
	}

	@Override
	public Double getRate() {
		return rate;
	}

	@Override
	public void recalculate(Double previousValue, Double remainingMoney, Integer remainingDays, Double interestRate) {
		rate = (remainingMoney / previousValue) / remainingDays;
	}

	@Override
	public void recalculate(Double remainingMoney, Integer remainingDays) {
		rate = remainingMoney / remainingDays;
	}

}
