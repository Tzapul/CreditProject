package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.views.NewClientView;

public interface INewClientController extends IWindowShower {

	void setView(NewClientView newClientView);

	void addClient();

}
