package itsix.CreditProject.models;

public class Credit implements ICredit {

	private String name;

	private IMoney money;

	private Double interestRate;
	private IPeriod period;

	private IFee monthlyFee;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getRemainingMonths() {
		return period.getNumberOfMonths();
	}

	@Override
	public Double getInterestRate() {
		return interestRate;
	}

	@Override
	public Double getMonthlyFee() {
		return monthlyFee.getValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credit other = (Credit) obj;
		if (interestRate == null) {
			if (other.interestRate != null)
				return false;
		} else if (!interestRate.equals(other.interestRate))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (monthlyFee == null) {
			if (other.monthlyFee != null)
				return false;
		} else if (!monthlyFee.equals(other.monthlyFee))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		return true;
	}
	
}
