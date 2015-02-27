package pl.grm.pts;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DFT extends JPanel implements Tab {
	private JTextField	tFieldSignalInput;
	private JTextArea	tA_Result;
	
	public DFT() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblSygna = new JLabel("Sygna\u0142");
		panel.add(lblSygna);
		
		tFieldSignalInput = new JTextField();
		panel.add(tFieldSignalInput);
		tFieldSignalInput.setColumns(10);
		
		JButton btnObliczDft = new JButton("Oblicz DFT");
		btnObliczDft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					tA_Result.setText(CalcCore.instance.calcDFT(tFieldSignalInput.getText()));
					;
				}).start();
			}
		});
		panel.add(btnObliczDft);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		tA_Result = new JTextArea();
		tA_Result.setRows(20);
		tA_Result.setEditable(false);
		tA_Result.setColumns(40);
		panel_1.add(tA_Result);
	}
	
}
