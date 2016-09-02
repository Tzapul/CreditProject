package itsix.CreditProject.models.implementations;

import java.text.DecimalFormat;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IMoney;
import itsix.CreditProject.models.interfaces.IPeriod;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.models.interfaces.IRate;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class Credit implements ICredit {

	private String name;

	private IMoney borrowedMoney;
	private IMoney remainingMoney;

	private Double interestRate;

	private IPeriod period;
	private IPeriod remainingDays;

	private IRate dailyRate;
	
	private IInnerPublisher publisher;
	private IProduct product;

	public Credit(String name, IMoney borrowedMoney, IMoney remainingMoney, Double interestRate, IPeriod period,
			IRate dailyRate, IPeriod remainingDays, IInnerPublisher publisher, IProduct product) {
		this.name = name;
		this.borrowedMoney = borrowedMoney;
		this.period = period;
		this.dailyRate = dailyRate;
		this.remainingMoney = remainingMoney;
		this.remainingDays = remainingDays;
		this.publisher = publisher;
		this.product = product;
		this.interestRate = this.product.getInterestRate();
	}

	@Override
	public Double getRemainingMoney() {
		DecimalFormat df = new DecimalFormat("#.###");
		return Double.valueOf(df.format(remainingMoney.getValue()));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getRemainingDays() {
		return remainingDays.getNumberOfDays();
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
		if (borrowedMoney == null) {
			if (other.borrowedMoney != null)
				return false;
		} else if (!borrowedMoney.equals(other.borrowedMoney))
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
	public Double getBorrowedMoney() {
		DecimalFormat df = new DecimalFormat("#.###");
		return Double.valueOf(df.format(borrowedMoney.getValue()));
	}

	@Override
	public void recalculate(Double money) {
		Double previousValue = remainingMoney.getValue();
		remainingMoney.take(money);

		dailyRate.recalculate(previousValue, remainingMoney.getValue(), remainingDays.getNumberOfDays());
		publisher.notifySubscribers();
	}

	@Override
	public Integer getPeriod() {
		return period.getNumberOfDays();
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
	}

}
