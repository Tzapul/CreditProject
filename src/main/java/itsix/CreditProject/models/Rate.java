package itsix.CreditProject.models;

public class Rate implements IRate {

	private Double rate;

	public Rate(Double rate) {
		this.rate = rate;
	}

	@Override
	public Double getRate() {
		return rate / 360;
	}
	
}
