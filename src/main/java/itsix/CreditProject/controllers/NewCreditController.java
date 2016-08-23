package itsix.CreditProject.controllers;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.ICreditBuilder;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.NewCreditView;

public class NewCreditController implements INewCreditController {

	private IRepository repository;

	private NewCreditView view;

	private ICreditBuilder fixedInterestBuilder;
	private ICreditBuilder variableInterestBuilder;
	private ICreditBuilder currentBuilder;

	private ICreditValidator validator;

	public NewCreditController(IRepository repository, ICreditBuilder fixedInterestBuilder,
			ICreditBuilder variableInterestBuilder, ICreditValidator validator) {

		this.repository = repository;
		this.fixedInterestBuilder = fixedInterestBuilder;
		this.variableInterestBuilder = variableInterestBuilder;
		this.validator = validator;
		
		changeToFixedBuilder();
	}

	@Override
	public void createNewCredit() {
		ICredit credit = currentBuilder.build(view.getCreditName(), view.getMinValue(), view.getMaxValue(),
				view.getInterestRate(), view.getCurrency(), view.getPeriod());

		IValidatorResult result = validator.validateFields(credit);
		
		if(result.isNotValid()) {
			JOptionPane.showMessageDialog(null,
				    result.getDescription(),
				    "Invalid Fields",
				    JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		repository.getCreditRepository().add(credit);
		view.dispose();
	}

	@Override
	public Vector<ICurrency> getCurrencies() {
		return repository.getCurrencyRepository().getCurrencies();
	}

	@Override
	public void setView(NewCreditView newCreditView) {
		this.view = newCreditView;
	}

	@Override
	public void changeToFixedBuilder() {
		this.currentBuilder = fixedInterestBuilder;
	}

	@Override
	public void changeToVariableBuilder() {
		this.currentBuilder = variableInterestBuilder;
	}

	@Override
	public void setLabelsVisible() {
		view.setInterestRateVisible();
		view.setInterestRateValueVisible();

		if (view.interestIsNotNull()) {
			updateValue();
		}
	}

	@Override
	public void setLabelsInvisible() {
		view.setInterestRateInvisible();
		view.setInterestRateValueInvisible();
	}

	@Override
	public void updateRealInterestRate() {
		updateValue();
	}

	public void updateValue() {
		Double value = repository.getIndicator() + view.getInterestRate();
		view.assignInterestRateValue(value);
	}

}
