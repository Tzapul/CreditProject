package itsix.CreditProject.models;

public class Interval implements IInterval {

	private Double minValue;
	private Double maxValue;

	public Interval(Double min, Double max) {
		this.minValue = min;
		this.maxValue = max;
	}

	@Override
	public Double getMin() {
		return minValue;
	}

	@Override
	public Double getMax() {
		return maxValue;
	}

}
