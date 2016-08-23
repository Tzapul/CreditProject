package itsix.CreditProject.models;

public class Credit implements ICredit {

	private String name;

	private IInterval interval;
	private ICurrency currency;

	private Double interestRate;
	private Integer period;

	public Credit(String name, IInterval interval, ICurrency currency, Double interestRate, Integer period) {
		super();
		this.name = name;
		this.interval = interval;
		this.currency = currency;
		this.interestRate = interestRate;
		this.period = period;
	}

	@Override
	public String getDescription() {
		StringBuilder builder = new StringBuilder();

		appendName(builder);
		appendInterval(builder);
		appendCurrency(builder);
		appendInterestRate(builder);
		appendPeriod(builder);
		return builder.toString();
	}

	private void appendPeriod(StringBuilder builder) {
		builder.append("Period : " + period + " months");
		builder.append("\n");
	}

	private void appendInterestRate(StringBuilder builder) {
		builder.append("Interest rate : " + interestRate + "%");
		builder.append("\n");
	}

	private void appendCurrency(StringBuilder builder) {
		builder.append("Currency : " + currency.getName() + " (" + currency.getSymbol() + ")");
		builder.append("\n");
	}

	private void appendInterval(StringBuilder builder) {
		builder.append("Money interval : " + interval.getMin().intValue() + " - " + interval.getMax().intValue());
		builder.append("\n");
	}

	private void appendName(StringBuilder builder) {
		builder.append("Credit name : " + name);
		builder.append("\n");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double getMinValue() {
		return interval.getMin();
	}

	@Override
	public Double getMaxValue() {
		return interval.getMax();
	}

	@Override
	public Double getInterestRate() {
		return interestRate;
	}

	@Override
	public ICurrency getCurrency() {
		return currency;
	}

	@Override
	public Integer getPeriod() {
		return period;
	}
	
	@Override
	public IInterval getInterval() {
		return interval;
	}

	@Override
	public boolean hasCurrency(ICurrency currency) {
		return currency.equals(this.currency);
	}

	@Override
	public void updateFields(ICredit credit) {
		this.name = credit.getName();
		this.period = credit.getPeriod();
		this.interestRate = credit.getInterestRate();
		this.currency = credit.getCurrency();
		this.interval = credit.getInterval();
	}



}
