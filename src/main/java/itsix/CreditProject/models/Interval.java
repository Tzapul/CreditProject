package itsix.CreditProject.models;

public class Interval implements IInterval {

	private Integer minValue;
	private Integer maxValue;

	public Interval(Integer min, Integer max) {
		this.minValue = min;
		this.maxValue = max;
	}

	@Override
	public Integer getMin() {
		return minValue;
	}

	@Override
	public Integer getMax() {
		return maxValue;
	}

	@Override
	public String toString() {
		return minValue + "-" + maxValue;
	}

}
