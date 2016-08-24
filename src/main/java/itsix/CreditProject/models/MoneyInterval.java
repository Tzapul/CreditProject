package itsix.CreditProject.models;

public class MoneyInterval implements IInterval {
	
	private IInterval interval;

	public MoneyInterval(IInterval interval) {
		this.interval = interval;
	}

	@Override
	public Integer getMin() {
		return interval.getMin();
	}

	@Override
	public Integer getMax() {
		return interval.getMax();
	}

}
