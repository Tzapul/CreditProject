package itsix.CreditProject.models.implementations;

import java.text.DecimalFormat;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IMoney;
import itsix.CreditProject.models.interfaces.IPeriod;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.models.interfaces.IRate;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class Credit implements ICredit {

	private static final long serialVersionUID = 1L;

	private String name;

	private IMoney borrowedMoney;
	private IMoney remainingMoney;

	private MutableDouble interestRate;
	private Double previousInterestRate;

	private IPeriod period;
	private IPeriod remainingDays;

	private IRate dailyRate;

	private IInnerPublisher publisher;
	private IProduct product;

	private IAccount account;

	public Credit(String name, IMoney borrowedMoney, IMoney remainingMoney, MutableDouble interestRate, IPeriod period,
			IRate dailyRate, IPeriod remainingDays, IInnerPublisher publisher, IProduct product, IAccount account) {
		this.name = name;
		this.borrowedMoney = borrowedMoney;
		this.period = period;
		this.dailyRate = dailyRate;
		this.remainingMoney = remainingMoney;
		this.remainingDays = remainingDays;
		this.publisher = publisher;
		this.product = product;
		this.interestRate = this.product.getInterestRate();
		this.previousInterestRate = this.interestRate.toDouble();
		this.account = account;
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
	public MutableDouble getInterestRate() {
		return interestRate;
	}

	@Override
	public Double getDailyRate() {
		DecimalFormat df = new DecimalFormat("#.###");
		return Double.valueOf(df.format(dailyRate.getRate()));
	}

	@Override
	public Double getBorrowedMoney() {
		DecimalFormat df = new DecimalFormat("#.###");
		return Double.valueOf(df.format(borrowedMoney.getValue()));
	}

	@Override
	public void recalculate(Double money) {
		remainingMoney.take(money);

		if (creditIsDone()) {
			account.remove(this);
			publisher.notifySubscribers();
			return;
		}

		dailyRate.recalculate(remainingMoney.getValue(), remainingDays.getNumberOfDays());
		publisher.notifySubscribers();
	}

	private boolean creditIsDone() {
		return remainingMoney.getValue() <= 0;
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

	@Override
	public void update() {
		remainingMoney.recalculate(borrowedMoney.getValue(), previousInterestRate, interestRate);
		dailyRate.recalculate(remainingMoney.getValue(), remainingDays.getNumberOfDays());
		previousInterestRate = interestRate.toDouble();
		publisher.notifySubscribers();
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
	public void takeMoneyFrom(IAccount account) {
		remainingMoney.take(getDailyRate());
		account.withdrawForCredit(getDailyRate());
	}

	@Override
	public void decrementRemainingDays() {
		remainingDays.decrement();
	}

	@Override
	public boolean hasExpired() {
		return remainingDays.isZero();
	}
}
