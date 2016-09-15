package itsix.CreditProject.builders.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.models.implementations.Credit;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IMoney;
import itsix.CreditProject.models.interfaces.IPeriod;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.models.interfaces.IRate;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;

public class CreditBuilder implements ICreditBuilder {

	private IMoneyBuilder moneyBuilder;
	
	private IPeriodBuilder periodBuilder;

	private IRateBuilder rateBuilder;

	
	public CreditBuilder(IMoneyBuilder moneyBuilder, IPeriodBuilder periodBuilder, IRateBuilder feeBuilder) {
		super();
		this.moneyBuilder = moneyBuilder;
		this.periodBuilder = periodBuilder;
		this.rateBuilder = feeBuilder;
	}


	@Override
	public ICredit build(String creditName, Double money, MutableDouble interestRate, Integer period, IProduct product, IAccount account) {
		
		IPeriod remainingDays = periodBuilder.build(period);
		IPeriod myPeriod = periodBuilder.build(period);
		
		IRate dailyRate = rateBuilder.build(myPeriod.getNumberOfDays(), money, interestRate);
		
		IMoney remainingMoney = moneyBuilder.build(money * (1 + interestRate.doubleValue() / 100));
		
		IMoney myMoney = moneyBuilder.build(money);
		
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		return new Credit(creditName, myMoney, remainingMoney, interestRate, myPeriod, dailyRate, remainingDays, publisher, product, account);
	}

	
}
