package pl.grm.pts.tabs.components;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class Table extends JPanel {
	private static final long		serialVersionUID	= 1L;
	private int						rowsCount			= 1;
	private int						columnsCount		= 1;
	private HashMap<Integer, Row>	rows				= new HashMap<Integer, Row>();
	private int						startIndex			= 1;
	private SortedSet<Integer>		columnIndexes		= new TreeSet<Integer>();
	private SortedSet<Integer>		columnHeaders;
	
	public Table(int rowsCount, int columnsCount, int startIndex, boolean createEmptyTable) {
		this.rowsCount = rowsCount + 1;
		this.columnsCount = columnsCount + 1;
		this.startIndex = startIndex;
		setLayout(new GridLayout(this.rowsCount, this.columnsCount));
		if (createEmptyTable) {
			createEmptyTable();
			addRowsToPanel();
		}
	}
	
	private void createEmptyTable() {
		createColumnsHeadersRow(null);
		for (int rowIndex = startIndex; rowIndex < rowsCount - 1 + startIndex; rowIndex++) {
			addRow(rowIndex, new Row(rowIndex, columnsCount, startIndex, true, columnHeaders));
		}
	}
	
	private void createColumnsHeadersRow(SortedSet<Integer> columnHeaders) {
		if (this.columnHeaders == null) {
			if (columnHeaders == null) {
				columnHeaders = new TreeSet<Integer>();
				for (int columnIndex = startIndex; columnIndex < columnsCount + startIndex - 1; columnIndex++) {
					columnHeaders.add(columnIndex);
				}
			}
			this.columnHeaders = columnHeaders;
		}
		Row row = new Row(startIndex - 1, columnsCount, startIndex, false, this.columnHeaders);
		JLabel labelE = new JLabel("//");
		labelE.setName("Column Empty Header");
		labelE.setHorizontalAlignment(SwingConstants.CENTER);
		row.addCell(startIndex - 1, labelE);
		for (Iterator<Integer> it = this.columnHeaders.iterator(); it.hasNext();) {
			int columnIndex = it.next();
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
	
	public void addRowsToPanel() {
		for (Iterator<Entry<Integer, Row>> it = rows.entrySet().iterator(); it.hasNext();) {
			Row row = it.next().getValue();
			row.addAllCellsToPanel(this);
		}
	}
	
	public void setHeaders(SortedSet<Integer> rowHeaders, SortedSet<Integer> columnHeaders) {
		Iterator<Integer> rowIt = rowHeaders.iterator();
		createColumnsHeadersRow(columnHeaders);
		while (rowIt.hasNext()) {
			int rowIndex = rowIt.next();
			Row row = new Row(rowIndex, columnsCount, startIndex, true, columnHeaders);
			this.addRow(rowIndex, row);
		}
	}
	
	public int getRowsCount() {
		return this.rowsCount - 1;
	}
	
	public int getColumnsCount() {
		return this.columnsCount - 1;
	}
	
	public int getValueFrom(int rowIndex, int columnIndex) throws Exception {
		if (!rows.containsKey(rowIndex) || rowIndex <= startIndex - 1) { throw new Exception(
				"GET | There is no row: " + rowIndex); }
		if (!columnIndexes.contains(columnIndex) || columnIndex <= startIndex - 1) { throw new Exception(
				"GET | There is no column " + columnIndex + " in row " + rowIndex); }
		Row row = rows.get(rowIndex);
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
	
	public void setValueInCell(int value, int rowIndex, int columnIndex) throws Exception {
		if (!rows.containsKey(rowIndex) || rowIndex <= startIndex - 1) { throw new Exception(
				"SET | There is no row: " + rowIndex); }
		if (!columnIndexes.contains(columnIndex) || columnIndex <= startIndex - 1) { throw new Exception(
				"SET | There is no column " + columnIndex + " in row " + rowIndex); }
		Row row = rows.get(rowIndex);
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
	
	public int getMaxValue() throws Exception {
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
		for (Iterator<Entry<Integer, Row>> it = rows.entrySet().iterator(); it.hasNext();) {
			Row row = it.next().getValue();
			set.addAll(row.getValues());
		}
		return set;
	}
	
	public void setRowsColumnsHeaders(String[] values) {
		Row row = rows.get(startIndex - 1);
		for (int c = startIndex; c < columnsCount; c++) {
			Component cell = row.getCell(c);
			if (cell instanceof JLabel) {
				JLabel label = (JLabel) cell;
				label.setText(values[c]);
			}
		}
	}
	
	public int getStartIndex() {
		return startIndex;
	}
}