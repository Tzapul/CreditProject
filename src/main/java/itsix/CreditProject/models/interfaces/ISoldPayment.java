package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

public interface ISoldPayment extends IPayment, Serializable {

	void setPayment(IPayment payment);

	void setAccount(IAccount account);
}
