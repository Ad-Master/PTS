package pl.grm.pts.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DBConverter extends JPanel {
	private static final long	serialVersionUID	= 1L;
	private JTextField			textField_2;
	private JTextField			textField_1;
	
	public DBConverter() {
		this.setName("Konwersja dB");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(textField_1);
		
		JPanel btPanel = new JPanel();
		
		JButton button_1 = new JButton("<- Convert");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
		JButton button_2 = new JButton("Convert ->");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
		add(btPanel);
		btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.Y_AXIS));
		btPanel.add(button_1);
		btPanel.add(button_2);
		add(textField_2);
	}
}
