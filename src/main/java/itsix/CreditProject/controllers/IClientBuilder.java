package itsix.CreditProject.controllers;

import itsix.CreditProject.models.IClient;

public interface IClientBuilder {

	IClient build(Integer ssn, String firstname, String lastname, String address);

}
