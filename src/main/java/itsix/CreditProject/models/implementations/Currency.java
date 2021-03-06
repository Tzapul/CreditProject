package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.ICurrency;

public class Currency implements ICurrency {

	private static final long serialVersionUID = 1L;
	
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}


}
