package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

public interface ICashPayment extends IPayment, Serializable {

	void setPayment(IPayment payment);

}
