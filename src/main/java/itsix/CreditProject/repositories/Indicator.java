package itsix.CreditProject.repositories;

public class Indicator implements IIndicator {

	private Double value;

	public Indicator(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

}
