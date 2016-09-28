package itsix.CreditProject.repositories;

import java.io.IOException;

public interface IParser {

	IRepository parse() throws ClassNotFoundException, NullPointerException, IOException;

}
