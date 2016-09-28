package itsix.CreditProject.models.implementations;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.ISold;

public class Sold implements ISold {

	private static final long serialVersionUID = 1L;
	
	private Double value;

	public Sold(Double value) {
		super();
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void add(Double money) {
		value += money;
	}

	@Override
	public void subtract(Double money) throws SoldLesserThanZeroException {
		LesserThanZero(value - money);
		value -= money;
	}

	private void LesserThanZero(Double value) throws SoldLesserThanZeroException {
		if(value < 0) {
			throw new SoldLesserThanZeroException();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sold other = (Sold) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public void take(Double dailyRate) {
		value -= dailyRate;
	}

	
}
