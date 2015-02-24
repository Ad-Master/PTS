package pl.grm.pts.panels;

import java.awt.*;

import javax.swing.*;

public class Histogram extends JPanel {
	public Histogram() {
		this.setName("Histogram");
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		JLabel lblIloKolumn = new JLabel("Ilo\u015B kolumn");
		topPanel.add(lblIloKolumn);
		
		tFColumns = new JTextField();
		topPanel.add(tFColumns);
		tFColumns.setColumns(10);
		
		JLabel lblIloWierszy = new JLabel("Ilo\u015B wierszy");
		topPanel.add(lblIloWierszy);
		
		tFRows = new JTextField();
		topPanel.add(tFRows);
		tFRows.setColumns(10);
		
		JButton buttonCreateTable = new JButton("Stw\u00F3rz");
		topPanel.add(buttonCreateTable);
		
		table = new JTable();
		add(table);
		
		JPanel panelBottom = new JPanel();
		add(panelBottom, BorderLayout.SOUTH);
		
		JButton buttonCalc = new JButton("Oblicz");
		panelBottom.add(buttonCalc);
		
		JButton buttonShow = new JButton("Poka\u017C");
		panelBottom.add(buttonShow);
	}
	
	private static final long	serialVersionUID	= 1L;
	private JTable				table;
	private JTextField			tFColumns;
	private JTextField			tFRows;
	
}
