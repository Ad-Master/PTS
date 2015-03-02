package pl.grm.pts.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.grm.pts.CalcCore;
import pl.grm.pts.Tab;
import pl.grm.pts.core.misc.Complex;

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
		tF_input.setColumns(10);
		tF_input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					countFFT();
				}).start();
			}
		});
		panel_Top.add(tF_input);
		
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
		
		JLabel lblMax = new JLabel("max 10");
		panel_Bottom.add(lblMax);
		
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
