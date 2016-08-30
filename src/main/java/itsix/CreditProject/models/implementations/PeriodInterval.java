package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IInterval;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodInterval other = (PeriodInterval) obj;
		if (interval == null) {
			if (other.interval != null)
				return false;
		} else if (!interval.equals(other.interval))
			return false;
		return true;
	}
}
