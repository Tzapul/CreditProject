package itsix.CreditProject.models;

public class Sold implements ISold {

	private Integer value;
	
	public Sold(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

}
