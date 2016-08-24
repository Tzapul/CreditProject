package itsix.CreditProject.models;

public class PeriodInterval implements IInterval {

	private IInterval interval;
	
	public PeriodInterval(IInterval interval) {
		super();
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
