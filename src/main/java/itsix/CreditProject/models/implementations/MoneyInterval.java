package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IInterval;

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyInterval other = (MoneyInterval) obj;
		if (interval == null) {
			if (other.interval != null)
				return false;
		} else if (!interval.equals(other.interval))
			return false;
		return true;
	}


	
}
