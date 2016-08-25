package itsix.CreditProject.customs;

import java.util.List;

import javax.swing.JTable;

import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.ICredit;

public class CreditsTableModel extends JTable {
	
	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Name", "Monthly Fee", "Interest Rate", "Remaining Months"};

	private List<ICredit> data;
	
	public CreditsTableModel(List<ICredit> data) {
		this.data = data;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getName();
		case 1:
			return data.get(row).getMonthlyFee();
		case 2:
			return data.get(row).getInterestRate();
		case 3:
			return data.get(row).getRemainingMonths();
		}

		return null;
	}

	public ICredit getRow(Integer row) {
		return data.get(row);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
