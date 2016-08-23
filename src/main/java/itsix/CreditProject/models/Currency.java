package itsix.CreditProject.models;

public class Currency implements ICurrency {

	private String name;
	private String symbol;

	public Currency(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return name + " (" + symbol + ")";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}
	

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!Currency.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		final ICurrency currency = (ICurrency) obj;
		
		if((this.name == null) ? (currency.getName() != null) : !this.name.equals(currency.getName())) {
			return false;
		}
		
		if((this.symbol == null) ? (currency.getSymbol() != null) : !this.symbol.equals(currency.getSymbol())) {
			return false;
		}
		
		return true;
	}
}
