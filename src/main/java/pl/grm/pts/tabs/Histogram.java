package pl.grm.pts.tabs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

import pl.grm.pts.*;
import pl.grm.pts.tabs.components.*;

public class Histogram extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private Table				table_1;
	private Table				table_2;
	private JTextField			tFColumns;
	private JTextField			tFRows;
	private JScrollPane			scrollPane_2;
	private JScrollPane			scrollPane_1;
	private HGShowResult		dialog;
	private JButton				buttonCalc;
	private JButton				buttonShow;
	
	public Histogram() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		JLabel lblIloKolumn = new JLabel("Ilo\u015B kolumn");
		topPanel.add(lblIloKolumn);
		
		tFColumns = new JTextField();
		tFColumns.setColumns(10);
		tFColumns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateTableWorker tableWorker = new CreateTableWorker();
				tableWorker.execute();
			}
		});
		topPanel.add(tFColumns);
		
		JLabel lblIloWierszy = new JLabel("Ilo\u015B wierszy");
		topPanel.add(lblIloWierszy);
		
		tFRows = new JTextField();
		tFRows.setColumns(10);
		tFRows.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateTableWorker tableWorker = new CreateTableWorker();
				tableWorker.execute();
			}
		});
		topPanel.add(tFRows);
		
		JButton buttonCreateTable = new JButton("Stw\u00F3rz");
		buttonCreateTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateTableWorker tableWorker = new CreateTableWorker();
				tableWorker.execute();
			}
		});
		topPanel.add(buttonCreateTable);
		
		JPanel panelBottom = new JPanel();
		add(panelBottom, BorderLayout.SOUTH);
		
		buttonCalc = new JButton("Oblicz");
		buttonCalc.setEnabled(false);
		buttonCalc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalcHGWorker calcWorker = new CalcHGWorker();
				calcWorker.execute();
			}
		});
		panelBottom.add(buttonCalc);
		
		buttonShow = new JButton("Poka\u017C");
		buttonShow.setEnabled(false);
		buttonShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		panelBottom.add(buttonShow);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		scrollPane_1 = new JScrollPane();
		centerPanel.add(scrollPane_1);
		
		scrollPane_2 = new JScrollPane();
		centerPanel.add(scrollPane_2);
	}
	
	private class CreateTableWorker extends SwingWorker<Boolean, Void> {
		
		@Override
		protected Boolean doInBackground() throws Exception {
			String colS = tFColumns.getText();
			String rowS = tFRows.getText();
			int columnsCount;
			int rowsCount;
			try {
				columnsCount = Integer.parseInt(colS);
				rowsCount = Integer.parseInt(rowS);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
			if (columnsCount > 10 || rowsCount > 10) { return false; }
			table_1 = new Table(rowsCount, columnsCount, 1, true);
			table_2 = new Table(rowsCount, columnsCount, 1, true);
			return true;
		}
		
		@Override
		protected void done() {
			try {
				if (get()) {
					scrollPane_1.setViewportView(table_1);
					scrollPane_2.setViewportView(table_2);
					buttonCalc.setEnabled(true);
					
				} else {
					System.out.println("Smth went wrong!");
					JOptionPane.showMessageDialog(Histogram.this, "Smth went wrong", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class CalcHGWorker extends SwingWorker<Table, Void> {
		
		@Override
		protected Table doInBackground() throws Exception {
			SortedSet<Integer> set1 = table_1.getValues();
			SortedSet<Integer> set2 = table_2.getValues();
			int rCount = set1.size();
			int cCount = set2.size();
			Table table = new Table(rCount, cCount, 0, false);
			table.setHeaders(set1, set2);
			int startIndex = table_1.getStartIndex();
			for (int r = startIndex; r <= table_1.getRowsCount() + startIndex - 1; r++) {
				for (int c = startIndex; c <= table_1.getColumnsCount() + startIndex - 1; c++) {
					try {
						int v1 = table_1.getValueFrom(r, c);
						int v2 = table_2.getValueFrom(r, c);
						int currentValue = table.getValueFrom(v1, v2);
						table.setValueInCell(currentValue + 1, v1, v2);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			table.addRowsToPanel();
			return table;
		}
		
		@Override
		protected void done() {
			Table table = null;
			try {
				table = get();
				dialog = new HGShowResult(table);
				buttonShow.setEnabled(true);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		}
	}
}
