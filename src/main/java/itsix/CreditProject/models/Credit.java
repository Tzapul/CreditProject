package itsix.CreditProject.models;

public class Credit implements ICredit {

	private String name;

	private IMoney money;
	
	private Double interestRate;
	private IPeriod period;
	
	private IFee monthlyFee;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Integer getRemainingMonths() {
		return period.getNumberOfMonths();
	}
	
	@Override
	public Double getInterestRate() {
		return interestRate;
	}
	
	@Override
	public Double getMonthlyFee() {
		return monthlyFee.getValue();
	}
}
