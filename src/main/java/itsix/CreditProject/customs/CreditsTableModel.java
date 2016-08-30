package itsix.CreditProject.customs;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import itsix.CreditProject.models.interfaces.ICredit;

public class CreditsTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Name", "Daily Fee", "Interest Rate", "Remaining Days", "Remaining Money"};

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
			return data.get(row).getDailyRate();
		case 2:
			return data.get(row).getInterestRate();
		case 3:
			return data.get(row).getRemainingDays();
		case 4:
			return data.get(row).getRemainingMoney();
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
