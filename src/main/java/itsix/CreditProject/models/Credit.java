package itsix.CreditProject.models;

import java.text.DecimalFormat;

public class Credit implements ICredit {

	private String name;

	private IMoney money;

	private Double interestRate;
	private IPeriod period;

	private IRate dailyRate;

	public Credit(String name, IMoney money, Double interestRate, IPeriod period, IRate dailyRate) {
		super();
		this.name = name;
		this.money = money;
		this.interestRate = interestRate;
		this.period = period;
		this.dailyRate = dailyRate;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getRemainingDays() {
		return period.getNumberOfDays();
	}

	@Override
	public Double getInterestRate() {
		return interestRate;
	}

	@Override
	public Double getDailyRate() {
		DecimalFormat df = new DecimalFormat("#.###");
		return Double.valueOf(df.format(dailyRate.getRate()));
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
		if (dailyRate == null) {
			if (other.dailyRate != null)
				return false;
		} else if (!dailyRate.equals(other.dailyRate))
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

	@Override
	public Double getMoney() {
		return money.getValue();
	}
	
}
