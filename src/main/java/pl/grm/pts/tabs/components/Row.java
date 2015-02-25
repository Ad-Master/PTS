package pl.grm.pts.tabs.components;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class Row extends JPanel {
	private static final long			serialVersionUID	= 1L;
	private HashMap<Integer, Component>	cells;
	private int							rowIndex;
	private int							cellsCount;
	private int							startIndex;
	
	public Row(int rowIndex, int cellsCount, int startIndex, boolean fillUp) {
		this.rowIndex = rowIndex;
		this.cellsCount = cellsCount;
		this.startIndex = startIndex;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		cells = new HashMap<Integer, Component>();
		if (fillUp) {
			createEmptyRow();
		}
	}
	
	public void createEmptyRow() {
		this.addCell(startIndex - 1, createRowHeader(rowIndex));
		for (int columnIndex = startIndex; columnIndex < (cellsCount - 1 + startIndex); columnIndex++) {
			this.addCell(columnIndex, createTextFieldCell());
		}
	}
	
	private JLabel createRowHeader(int rowIndex) {
		JLabel label = new JLabel("" + (rowIndex));
		label.setName("Row Header " + (rowIndex));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}
	
	private JTextField createTextFieldCell() {
		JTextField textField = new JTextField("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setName("TF ");
		return textField;
	}
	
	public void addCell(int columnIndex, Component comp) {
		if (cells.size() < cellsCount || cells.containsKey(columnIndex)) {
			cells.put(columnIndex, comp);
		}
	}
	
	public void addAllCellsToPanel(JPanel panel) {
		for (Iterator<Entry<Integer, Component>> it = cells.entrySet().iterator(); it.hasNext();) {
			Component comp = it.next().getValue();
			panel.add(comp);
		}
	}
	
	public Set<? extends Integer> getColumnIndexes() {
		return cells.keySet();
	}
	
	public Component[] getCells() {
		Component[] cellsA = new Component[cells.size()];
		int i = 0;
		for (Iterator<Entry<Integer, Component>> it = cells.entrySet().iterator(); it.hasNext(); i++) {
			Component comp = it.next().getValue();
			cellsA[i] = comp;
		}
		return cellsA;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public Component getCell(int columnIndex) {
		return cells.get(columnIndex);
	}
	
	public int getFilledCellsCount() {
		int count = 0;
		for (Iterator<Entry<Integer, Component>> it = cells.entrySet().iterator(); it.hasNext();) {
			Component comp = it.next().getValue();
			String txt = "";
			if (comp instanceof JTextField) {
				JTextField tField = (JTextField) comp;
				txt = tField.getText();
			} else if (comp instanceof JLabel) {
				JLabel label = (JLabel) comp;
				txt = label.getText();
			}
			if (!(txt.equals("0") || txt.equals(""))) {
				count++;
			}
		}
		return count;
	}
}