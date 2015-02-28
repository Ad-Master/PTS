package pl.grm.pts.tabs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import pl.grm.pts.*;
import pl.grm.pts.core.fourier.util.*;

public class DFT extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private JTextField			tFieldSignalInput;
	private JTextArea			tA_Result;
	private JTextField			tF_w1dc;
	private JTextField			tF_w11;
	private JTextField			tF_w12;
	private JTextField			tF_w2dc;
	private JTextField			tF_w21;
	private JTextField			tF_w22;
	private JTextField			tf_w3dc;
	private JTextField			tF_w31;
	private JTextField			tF_w32;
	
	public DFT() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Top = new JPanel();
		add(panel_Top, BorderLayout.NORTH);
		
		JLabel lblSygna = new JLabel("Sygna\u0142");
		panel_Top.add(lblSygna);
		
		tFieldSignalInput = new JTextField();
		tFieldSignalInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					countDFT();
				}).start();
			}
		});
		tFieldSignalInput.setColumns(10);
		panel_Top.add(tFieldSignalInput);
		
		JButton btnObliczDft = new JButton("Oblicz DFT");
		btnObliczDft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					countDFT();
				}).start();
			}
		});
		panel_Top.add(btnObliczDft);
		
		JPanel panel_Main = new JPanel();
		add(panel_Main, BorderLayout.CENTER);
		panel_Main.setLayout(new BorderLayout(0, 0));
		
		tA_Result = new JTextArea();
		tA_Result.setRows(10);
		tA_Result.setEditable(false);
		tA_Result.setColumns(40);
		panel_Main.add(tA_Result, BorderLayout.CENTER);
		
		JPanel panel_SideIn = new JPanel();
		panel_Main.add(panel_SideIn, BorderLayout.EAST);
		panel_SideIn.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_w1 = new JPanel();
		panel_SideIn.add(panel_w1);
		panel_w1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblWersja = new JLabel("Wersja 1    DC");
		lblWersja.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w1.add(lblWersja);
		
		tF_w1dc = new JTextField();
		panel_w1.add(tF_w1dc);
		tF_w1dc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("1 h");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w1.add(lblNewLabel);
		
		tF_w11 = new JTextField();
		panel_w1.add(tF_w11);
		tF_w11.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("2 h");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w1.add(lblNewLabel_1);
		
		tF_w12 = new JTextField();
		panel_w1.add(tF_w12);
		tF_w12.setColumns(10);
		
		JPanel panel_w2 = new JPanel();
		panel_SideIn.add(panel_w2);
		panel_w2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblWersja_1 = new JLabel("Wersja 2 DC");
		lblWersja_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w2.add(lblWersja_1);
		
		tF_w2dc = new JTextField();
		tF_w2dc.setColumns(10);
		panel_w2.add(tF_w2dc);
		
		JLabel label = new JLabel("1 h");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w2.add(label);
		
		tF_w21 = new JTextField();
		tF_w21.setColumns(10);
		panel_w2.add(tF_w21);
		
		JLabel label_1 = new JLabel("2 h");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w2.add(label_1);
		
		tF_w22 = new JTextField();
		tF_w22.setColumns(10);
		panel_w2.add(tF_w22);
		
		JPanel panel_w3 = new JPanel();
		panel_SideIn.add(panel_w3);
		panel_w3.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblWersjai = new JLabel("W 3 (I optyczna) DC");
		lblWersjai.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w3.add(lblWersjai);
		
		tf_w3dc = new JTextField();
		tf_w3dc.setColumns(10);
		panel_w3.add(tf_w3dc);
		
		JLabel label_2 = new JLabel("1 h");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w3.add(label_2);
		
		tF_w31 = new JTextField();
		tF_w31.setColumns(10);
		panel_w3.add(tF_w31);
		
		JLabel label_3 = new JLabel("2 h");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_w3.add(label_3);
		
		tF_w32 = new JTextField();
		tF_w32.setColumns(10);
		panel_w3.add(tF_w32);
		
		JPanel panel_Bottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_Bottom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_Bottom, BorderLayout.SOUTH);
		
		JLabel lblObecnieMax = new JLabel("Obecnie tylko 4 sk");
		lblObecnieMax.setHorizontalAlignment(SwingConstants.LEFT);
		panel_Bottom.add(lblObecnieMax);
	}
	
	protected void countDFT() {
		Vector<Complex> resultSignal;
		try {
			resultSignal = CalcCore.instance.calcDFT(tFieldSignalInput.getText());
			int size = resultSignal.size();
			String out = "For " + size + " sampled input [" + tFieldSignalInput.getText() + "]\n";
			Iterator<Complex> iterator = resultSignal.iterator();
			while (iterator.hasNext()) {
				Complex cx = iterator.next();
				out += cx.toString() + "\n";
			}
			tA_Result.setText(out);
			if (size > 1) {
				final Complex w1dc = resultSignal.get(0);
				tF_w1dc.setText(w1dc.toString());
				tF_w2dc.setText(w1dc.divide(size).toString());
				tF_w32.setText(w1dc.divide(size).toString());
				if (size > 3) {
					Complex w11 = resultSignal.get(1);
					Complex w12 = resultSignal.get(2);
					Complex w13 = resultSignal.get(3);
					tF_w11.setText(w11.add(w13).toString());
					tF_w12.setText(w12.toString());
					tF_w21.setText(w11.add(w13).divide(size).toString());
					tF_w22.setText(w12.divide(size).toString());
					tf_w3dc.setText(w12.divide(size).toString());
					tF_w31.setText(w11.add(w13).divide(size).toString());
				}
			}
		}
		catch (Exception e1) {
			JOptionPane
					.showInternalMessageDialog(DFT.this.getParent().getParent(), e1.getMessage());
		}
	}
}
