package itsix.CreditProject.controllers;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.EditProductView;

public class EditCreditController implements IEditCreditController {

	private ICurrencyRepository currencyRepository;

	private IProductRepository productRepository;

	private IProduct credit;

	private EditProductView view;

	private IProductValidator productValidator;

	public EditCreditController(ICurrencyRepository currencyRepository, IProductRepository creditRepository,
			IProduct credit, IProductValidator productValidator) {
		this.currencyRepository = currencyRepository;
		this.productRepository = creditRepository;
		this.credit = credit;
		this.productValidator = productValidator;
	}

	@Override
	public void setView(EditProductView view) {
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
		view.setMinPeriod(credit.getMinPeriod());
		view.setMaxPeriod(credit.getMaxPeriod());
	}

	@Override
	public void updateCredit() {
		IProduct updatedCredit = view.getUpdatedProduct();

		IValidatorResult result = productValidator.validateFields(updatedCredit);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		productRepository.update(credit, updatedCredit);
		view.dispose();
	}

}
