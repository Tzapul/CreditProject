package itsix.CreditProject.models;

public class Money implements IMoney {

	private Integer value;

	public Money(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer getValue() {
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

}
