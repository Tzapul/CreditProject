package itsix.CreditProject.models.interfaces;

public interface ISoldPayment extends IPayment {

	void setPayment(IPayment payment);
	
	void setAccount(IAccount account);
}
