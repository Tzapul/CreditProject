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
	
}
