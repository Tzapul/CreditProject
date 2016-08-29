package itsix.CreditProject.builders;

import itsix.CreditProject.models.Credit;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.IMoney;
import itsix.CreditProject.models.IPeriod;
import itsix.CreditProject.models.IRate;

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
		IRate dailyRate = rateBuilder.build(myPeriod.getNumberOfDays(), money);
		return new Credit(creditName, myMoney, interestRate, myPeriod, dailyRate);
	}

	
}
