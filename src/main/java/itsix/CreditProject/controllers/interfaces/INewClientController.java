package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;

import itsix.CreditProject.views.NewClientView;

public interface INewClientController extends Serializable {

	void setView(NewClientView newClientView);

	void addClient();

}
