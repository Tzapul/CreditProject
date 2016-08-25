package itsix.CreditProject.models;

public class Money implements IMoney {

	private Integer value;

	public Money(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}
	
	
}
