package itsix.CreditProject.models;

public interface ICredit {

	String getName();

	Integer getRemainingDays();

	Double getInterestRate();

	Double getDailyRate();

	Double getMoney();

}
