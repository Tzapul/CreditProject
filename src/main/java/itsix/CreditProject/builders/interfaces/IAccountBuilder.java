package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IAccount;

public interface IAccountBuilder extends Serializable {

	IAccount buildDefaultAccount();

}
