package itsix.CreditProject.models.interfaces;

import itsix.CreditProject.pubSub.IPublisher;

public interface ICredit extends IPublisher{

	String getName();

	Integer getRemainingDays();

	Double getInterestRate();

	Double getDailyRate();

	Double getBorrowedMoney();

	Double getRemainingMoney();

	void recalculate(Double money);

	Integer getPeriod();

}
