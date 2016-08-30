package itsix.CreditProject.models.interfaces;

public interface ICredit {

	String getName();

	Integer getRemainingDays();

	Double getInterestRate();

	Double getDailyRate();

	Double getBorrowedMoney();

	Double getRemainingMoney();

}
