package pl.grm.pts.tabs.components;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class Table extends JPanel {
	private static final long		serialVersionUID	= 1L;
	private int						rowsCount;
	private int						columnsCount;
	private HashMap<Integer, Row>	rows;
	private int						startIndex;
	private SortedSet<Integer>		columnIndexes		= new TreeSet<Integer>();
	
	public Table(int rowsCount, int columnsCount, int startValue) {
		this.rowsCount = rowsCount + 1;
		this.columnsCount = columnsCount + 1;
		this.startIndex = startValue;
		setLayout(new GridLayout(this.rowsCount, this.columnsCount));
		createTable();
		addRowsToPanel();
	}
	
	private void createTable() {
		this.rows = new HashMap<Integer, Row>();
		createColumnsHeadersRow();
		for (int rowIndex = startIndex; rowIndex < rowsCount - 1 + startIndex; rowIndex++) {
			addRow(rowIndex, new Row(rowIndex, columnsCount, startIndex, true));
		}
	}
	
	private void createColumnsHeadersRow() {
		Row row = new Row(startIndex - 1, columnsCount, startIndex, false);
		JLabel labelE = new JLabel("//");
		labelE.setName("Column Empty Header");
		labelE.setHorizontalAlignment(SwingConstants.CENTER);
		row.addCell(startIndex - 1, labelE);
		for (int columnIndex = startIndex; columnIndex < columnsCount + startIndex - 1; columnIndex++) {
			JLabel label = new JLabel("" + columnIndex);
			label.setName("Column Header " + columnIndex);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			row.addCell(columnIndex, label);
		}
		this.addRow(startIndex - 1, row);
	}
	
	private void addRow(int rowIndex, Row row) {
		this.rows.put(rowIndex, row);
		this.columnIndexes.addAll(row.getColumnIndexes());
	}
	
	private void addRowsToPanel() {
		for (Iterator<Entry<Integer, Row>> it = rows.entrySet().iterator(); it.hasNext();) {
			Row row = it.next().getValue();
			row.addAllCellsToPanel(this);
		}
	}
	
	public Row getRow(int rowIndex) {
		return rows.get(rowIndex);
	}
	
	public int getRowsCount() {
		return this.rowsCount;
	}
	
	public int getColumnsCount() {
		return this.columnsCount;
	}
	
	public int getValueFrom(int rowIndex, int columnIndex) {
		if (!rows.containsKey(rowIndex) || !columnIndexes.contains(columnIndex)
				|| rowIndex <= startIndex - 1 || columnIndex <= startIndex - 1) { return 0; }
		Row row = getRow(rowIndex);
		Component comp = row.getCell(columnIndex);
		try {
			if (comp instanceof JTextField) {
				JTextField tField = (JTextField) comp;
				String text = tField.getText();
				int value = Integer.parseInt(text);
				return value;
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setValueInCell(int value, int rowIndex, int columnIndex) {
		if (rowIndex > rowsCount || columnIndex > columnsCount || rowIndex < 1 || columnIndex < 1) { return; }
		Row row = getRow(rowIndex);
		Component comp = row.getCell(columnIndex);
		try {
			if (comp instanceof JTextField) {
				JTextField tField = (JTextField) comp;
				tField.setText(value + "");
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public int getMaxValue() {
		int max = 0;
		for (int c = 1; c <= columnsCount; c++) {
			for (int r = 1; r <= rowsCount; r++) {
				if (max < getValueFrom(r, c)) {
					max = getValueFrom(r, c);
				}
			}
		}
		return max;
	}
	
	public SortedSet<Integer> getValues() {
		SortedSet<Integer> set = new TreeSet<Integer>();
		for (int c = 1; c <= columnsCount; c++) {
			for (int r = 1; r <= rowsCount; r++) {
				set.add(getValueFrom(r, c));
			}
		}
		return set;
	}
	
	public void setColumnsHeaders(String[] values) {
		Row row = getRow(0);
		for (int c = 0; c < columnsCount; c++) {
			Component cell = row.getCell(c);
			if (cell instanceof JLabel) {
				JLabel label = (JLabel) cell;
				label.setText(values[c]);
			}
		}
	}
}