package itsix.CreditProject.controllers.implementation;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.IEditProductController;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.EditProductView;

public class EditProductController implements IEditProductController {

	private ICurrencyRepository currencyRepository;

	private IProductRepository productRepository;

	private IProduct product;

	private EditProductView view;

	private IProductValidator productValidator;

	public EditProductController(ICurrencyRepository currencyRepository, IProductRepository creditRepository,
			IProduct product, IProductValidator productValidator) {
		this.currencyRepository = currencyRepository;
		this.productRepository = creditRepository;
		this.product = product;
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
		view.setCreditName(product.getName());
		view.setMinimumSize(product.getMinValue());
		view.setMaxValue(product.getMaxValue());
		view.setInterestRate(product.getInterestRate());
		view.setCurrency(currencyRepository.getCreditIndex(product));
		view.setMinPeriod(product.getMinPeriod());
		view.setMaxPeriod(product.getMaxPeriod());
	}

	@Override
	public void updateCredit() {
		IProduct updatedCredit = view.getUpdatedProduct();

		IValidatorResult result = productValidator.validateFields(updatedCredit);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		productRepository.update(product, updatedCredit);
		view.dispose();
	}

}
