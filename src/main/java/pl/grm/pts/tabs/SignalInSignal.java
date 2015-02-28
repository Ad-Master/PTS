package pl.grm.pts.tabs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import pl.grm.pts.*;

public class SignalInSignal extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private JTextField			tF_V;
	private JTextField			tF_W;
	private JToggleButton		tglbtnVWW;
	private JTextField			tF_P;
	private JTextField			tF_p;
	private JCheckBox			cB_on;
	private JCheckBox			cB_og;
	private JTextField			tF_EV;
	private JTextField			tF_EW;
	
	public SignalInSignal() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		JLabel lblV = new JLabel("V/ =");
		topPanel.add(lblV);
		
		tF_V = new JTextField();
		tF_V.setColumns(10);
		tF_V.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					calcSIS();
				}
				catch (Exception e1) {
					JOptionPane.showInternalMessageDialog(SignalInSignal.this.getParent()
							.getParent(), e1.getMessage());
				}
			}
		});
		topPanel.add(tF_V);
		
		JLabel lblW = new JLabel("W/ = ");
		topPanel.add(lblW);
		
		tF_W = new JTextField();
		tF_W.setColumns(10);
		tF_W.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					calcSIS();
				}
				catch (Exception e1) {
					JOptionPane.showInternalMessageDialog(SignalInSignal.this.getParent()
							.getParent(), e1.getMessage());
				}
			}
		});
		topPanel.add(tF_W);
		
		tglbtnVWW = new JToggleButton("V/ w W/  ");
		tglbtnVWW.setHorizontalAlignment(SwingConstants.LEFT);
		tglbtnVWW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tglbtnVWW.isSelected()) {
					tglbtnVWW.setText("W/ w V/ ");
				} else {
					tglbtnVWW.setText("V/ w W/ ");
				}
			}
		});
		topPanel.add(tglbtnVWW);
		
		JButton btnOblicz = new JButton("Oblicz");
		btnOblicz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					calcSIS();
				}
				catch (Exception e1) {
					JOptionPane.showInternalMessageDialog(SignalInSignal.this.getParent()
							.getParent(), e1.getMessage());
				}
			}
		});
		topPanel.add(btnOblicz);
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		
		JLabel lblP = new JLabel("P = ");
		lblP.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblP);
		
		tF_P = new JTextField();
		tF_P.setEditable(false);
		panel.add(tF_P);
		tF_P.setColumns(10);
		
		JLabel lblP_1 = new JLabel("P = ");
		panel.add(lblP_1);
		
		tF_p = new JTextField();
		tF_p.setEditable(false);
		panel.add(tF_p);
		tF_p.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2);
		
		cB_og = new JCheckBox("Ortogonalne");
		panel_2.add(cB_og);
		
		cB_on = new JCheckBox("Ortonormalne");
		panel_2.add(cB_on);
		
		JPanel panel_3 = new JPanel();
		mainPanel.add(panel_3);
		
		JLabel lblEv = new JLabel("E_V/ =");
		panel_3.add(lblEv);
		
		tF_EV = new JTextField();
		tF_EV.setEditable(false);
		panel_3.add(tF_EV);
		tF_EV.setColumns(10);
		
		JLabel lblEw = new JLabel("E_W/ = ");
		panel_3.add(lblEw);
		
		tF_EW = new JTextField();
		tF_EW.setEditable(false);
		panel_3.add(tF_EW);
		tF_EW.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel labelBottom = new JLabel("Info: Max 4 elementowy wektor np. [0 1.5 2 3]");
		panel_1.add(labelBottom);
	}
	
	protected HashMap<String, Component> getOutputs() {
		HashMap<String, Component> outputs = new HashMap<String, Component>();
		outputs.put("P", tF_P);
		outputs.put("p", tF_p);
		outputs.put("og", cB_og);
		outputs.put("on", cB_on);
		outputs.put("ev", tF_EV);
		outputs.put("ew", tF_EW);
		outputs.put("stateBtTg", tglbtnVWW);
		return outputs;
	}
	
	public void calcSIS() throws Exception {
		boolean side = tglbtnVWW.isSelected();
		if (side) {
			CalcCore.instance.calcSignalInSignal(tF_W.getText(), tF_V.getText(), getOutputs());
		} else {
			CalcCore.instance.calcSignalInSignal(tF_V.getText(), tF_W.getText(), getOutputs());
		}
	}
	
}
