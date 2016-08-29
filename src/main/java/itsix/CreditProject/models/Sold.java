package itsix.CreditProject.models;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public class Sold implements ISold {

	private Integer value;

	public Sold(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void add(Integer money) {
		value += money;
	}

	@Override
	public void subtract(Integer money) throws SoldLesserThanZeroException {
		LesserThanZero(value - money);
		value -= money;
	}

	private void LesserThanZero(int value) throws SoldLesserThanZeroException {
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

	
}
