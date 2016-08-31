package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.models.implementations.Credit;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IMoney;
import itsix.CreditProject.models.interfaces.IPeriod;
import itsix.CreditProject.models.interfaces.IRate;

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
	public ICredit build(String creditName, Double money, Double interestRate, Integer period) {
		IMoney myMoney = moneyBuilder.build(money);
		IPeriod myPeriod = periodBuilder.build(period);
		IRate dailyRate = rateBuilder.build(myPeriod.getNumberOfDays(), money, interestRate);
		IMoney remainingMoney = moneyBuilder.build(money * (1 + interestRate / 100));
		return new Credit(creditName, myMoney, remainingMoney, interestRate, myPeriod, dailyRate);
	}

	
}
