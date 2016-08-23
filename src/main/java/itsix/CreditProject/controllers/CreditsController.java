package itsix.CreditProject.controllers;

import java.util.List;

import itsix.CreditProject.builders.FixedCreditBuilder;
import itsix.CreditProject.builders.IFixedCreditBuilder;
import itsix.CreditProject.builders.IIntervalBuilder;
import itsix.CreditProject.builders.IVariableCreditBuilder;
import itsix.CreditProject.builders.IntervalBuilder;
import itsix.CreditProject.builders.VariableCreditBuilder;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.validator.CreditValidator;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.CreditsView;
import itsix.CreditProject.views.EditCreditView;
import itsix.CreditProject.views.NewCreditView;

public class CreditsController implements ICreditsController {

	private IRepository repository;

	private CreditsView view;

	public CreditsController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void setView(CreditsView view) {
		this.view = view;
	}

	@Override
	public void goToNewCreditView() {

		// Initialize interval and credit builders
		IIntervalBuilder intervalBuilder = new IntervalBuilder();
		IFixedCreditBuilder fixedInterestBuilder = new FixedCreditBuilder(intervalBuilder);
		IVariableCreditBuilder variableInterestBuilder = new VariableCreditBuilder(intervalBuilder, repository);

		// Initializing creditValidator
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

		ICreditValidator creditValidator = new CreditValidator(validator);

		INewCreditController controller = new NewCreditController(repository, fixedInterestBuilder,
				variableInterestBuilder, creditValidator);
		NewCreditView newCreditView = new NewCreditView(controller);

		controller.setView(newCreditView);
		newCreditView.setVisible(true);
	}

	@Override
	public List<ICredit> getCreditsList() {
		return repository.getCreditRepository().getCredits();
	}

	@Override
	public void setDescriptionText(String description) {
		view.setDescription(description);
	}

	@Override
	public void goToEditProduct() {

		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);

		ICreditValidator creditValidator = new CreditValidator(validator);

		IEditCreditController editCreditController = new EditCreditController(repository.getCurrencyRepository(),
				repository.getCreditRepository(), view.getProductList().getSelectedValue(), creditValidator);

		EditCreditView editCreditView = new EditCreditView(editCreditController);
		editCreditController.setView(editCreditView);

		editCreditController.populateFields();

		editCreditView.setVisible(true);
	}

	@Override
	public void delete(ICredit credit) {
		repository.getCreditRepository().delete(credit);
		;
	}

	@Override
	public void clearDescription() {
		view.clearDescription();
	}

}
