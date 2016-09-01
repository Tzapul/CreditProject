package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IMoney;

public class Money implements IMoney {

	private Double value;

	public Money(Double value) {
		super();
		this.value = value;
	}


	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	@Override
	public void take(Double money) {
		value -= money;
	}

}
