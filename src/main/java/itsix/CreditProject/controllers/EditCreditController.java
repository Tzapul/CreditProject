package itsix.CreditProject.controllers;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.repositories.ICreditRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.EditCreditView;

public class EditCreditController implements IEditCreditController {

	private ICurrencyRepository currencyRepository;


	private ICreditRepository creditRepository;

	private ICredit credit;

	private EditCreditView view;

	private ICreditValidator creditValidator;

	public EditCreditController(ICurrencyRepository currencyRepository, ICreditRepository creditRepository,
			ICredit credit, ICreditValidator creditValidator) {
		this.currencyRepository = currencyRepository;
		this.creditRepository = creditRepository;
		this.credit = credit;
		this.creditValidator = creditValidator;
	}

	@Override
	public void setView(EditCreditView view) {
		this.view = view;
	}

	@Override
	public Vector<ICurrency> getCurrencies() {
		return currencyRepository.getCurrencies();
	}

	@Override
	public void populateFields() {
		view.setCreditName(credit.getName());
		view.setMinimumSize(credit.getMinValue());
		view.setMaxValue(credit.getMaxValue());
		view.setInterestRate(credit.getInterestRate());
		view.setCurrency(currencyRepository.getCreditIndex(credit));
		view.setPeriod(credit.getPeriod());
	}

	@Override
	public void updateCredit() {
		ICredit updatedCredit = view.getUpdatedCredit();

		IValidatorResult result = creditValidator.validateFields(updatedCredit);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		creditRepository.update(credit, updatedCredit);
		view.dispose();
	}

}
