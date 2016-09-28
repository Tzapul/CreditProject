package itsix.CreditProject.models.interfaces;

import java.io.Serializable;
import java.util.List;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.implementations.Credit;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.IPublisher;

public interface IAccount extends IPublisher, Serializable {

	String getCurrencyName();

	String getSymbol();

	Double getSold();

	void setPublisher(IInnerPublisher publisher);

	ICurrency getCurrency();

	void addNew(ICredit credit);

	List<ICredit> getCredits();

	void deposit(Double money);

	void add(Double money);

	void withdraw(Double money) throws SoldLesserThanZeroException;

	void payCredits();

	void withdrawForCredit(Double dailyRate);

	void remove(Credit credit);

}
