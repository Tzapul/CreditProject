package itsix.CreditProject.models;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.IPublisher;

public interface IAccount extends IPublisher {

	String getCurrencyName();

	String getSymbol();

	Integer getSold();

	void deposit(Integer moneyToDeposit);

	void setPublisher(IInnerPublisher publisher);

	void withdraw(Integer money) throws SoldLesserThanZeroException;

	ICurrency getCurrency();

}
