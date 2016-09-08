package itsix.CreditProject.controllers.implementation;

import itsix.CreditProject.controllers.interfaces.IDaysController;
import itsix.CreditProject.controllers.interfaces.IRepository;

public class DaysController implements IDaysController {

	private IRepository repository;
	
	public DaysController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void addDays(Integer days) {
		repository.addDays(days);
	}

}
