package itsix.CreditProject.repositories;

public class Indicator implements IIndicator {

	private static final long serialVersionUID = 1L;
	
	private Double value;

	public Indicator(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

}
