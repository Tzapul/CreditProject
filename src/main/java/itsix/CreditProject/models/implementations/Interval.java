package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IInterval;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		if (maxValue == null) {
			if (other.maxValue != null)
				return false;
		} else if (!maxValue.equals(other.maxValue))
			return false;
		if (minValue == null) {
			if (other.minValue != null)
				return false;
		} else if (!minValue.equals(other.minValue))
			return false;
		return true;
	}

}
