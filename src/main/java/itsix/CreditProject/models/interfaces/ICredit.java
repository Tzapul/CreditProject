package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.pubSub.IPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public interface ICredit extends IPublisher, ISubscriber, Serializable {

	String getName();

	Integer getRemainingDays();

	MutableDouble getInterestRate();

	Double getDailyRate();

	Double getBorrowedMoney();

	Double getRemainingMoney();

	void recalculate(Double money);

	Integer getPeriod();

	void takeMoneyFrom(IAccount account);

	void decrementRemainingDays();

	boolean hasExpired();

}
