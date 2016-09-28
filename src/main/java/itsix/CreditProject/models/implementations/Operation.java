package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IOperation;

public class Operation implements IOperation {

	private static final long serialVersionUID = 1L;
	
	private Integer day;

	public Operation(Integer day) {
		super();
		this.day = day;
	}

	@Override
	public Integer getDay() {
		return day;
	}
	
	
}
