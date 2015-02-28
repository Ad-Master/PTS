package pl.grm.pts.tabs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import pl.grm.pts.*;
import pl.grm.pts.core.fourier.util.*;

public class FFT extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private JTextField			tF_input;
	private JPanel				panel_Main;
	
	public FFT() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Top = new JPanel();
		add(panel_Top, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Sygna\u0142 x = ");
		panel_Top.add(lblNewLabel_1);
		
		tF_input = new JTextField();
		panel_Top.add(tF_input);
		tF_input.setColumns(10);
		
		JButton btnObliczFft = new JButton("Oblicz FFT");
		btnObliczFft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					countFFT();
				}).start();
			}
		});
		panel_Top.add(btnObliczFft);
		
		JPanel panel_Bottom = new JPanel();
		add(panel_Bottom, BorderLayout.SOUTH);
		
		panel_Main = new JPanel();
		add(panel_Main, BorderLayout.CENTER);
	}
	
	protected void countFFT() {
		String vec = tF_input.getText();
		try {
			Vector<Complex> output = CalcCore.instance.calcFFT(vec);
			panel_Main.removeAll();
			int count = output.size();
			panel_Main.setLayout(new GridLayout(count, 1, 0, 0));
			for (int i = 0; i < count; i++) {
				Complex cx = output.get(i);
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				JLabel label = new JLabel("X" + i);
				panel.add(label);
				JTextField tF = new JTextField();
				tF.setColumns(30);
				tF.setText(cx.toString());
				panel.add(tF);
				panel_Main.add(panel);
			}
		}
		catch (Exception e1) {
			JOptionPane
					.showInternalMessageDialog(FFT.this.getParent().getParent(), e1.getMessage());
		}
	}
	
}
