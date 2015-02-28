package pl.grm.pts.tabs;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pl.grm.pts.*;

public class DBConverter extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private JTextField			tF_A;
	private JTextField			tF_P;
	private JTextField			tF_k;
	private JTextField			tF_Po;
	
	public DBConverter() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("A = ");
		panel_2.add(lblNewLabel_1);
		tF_A = new JTextField();
		panel_2.add(tF_A);
		tF_A.setColumns(10);
		
		JPanel btPanel = new JPanel();
		panel_2.add(btPanel);
		
		JButton button_1 = new JButton("<- Convert");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					convert(false);
				}).start();
			}
		});
		JButton button_2 = new JButton("Convert ->");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					convert(true);
				}).start();
			}
		});
		btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.Y_AXIS));
		btPanel.add(button_1);
		btPanel.add(button_2);
		
		JLabel lblNewLabel = new JLabel("P = ");
		panel_2.add(lblNewLabel);
		tF_P = new JTextField();
		tF_P.setText("1");
		panel_2.add(tF_P);
		tF_P.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("k = ");
		panel_1.add(lblNewLabel_2);
		
		tF_k = new JTextField();
		tF_k.setText("10");
		panel_1.add(tF_k);
		tF_k.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("P_o = ");
		panel_1.add(lblNewLabel_3);
		
		tF_Po = new JTextField();
		tF_Po.setText("1");
		panel_1.add(tF_Po);
		tF_Po.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblA = new JLabel("A = k*log_10(P/P_o)");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_3.add(lblA);
	}
	
	protected void convert(boolean inverted) {
		String k = tF_k.getText();
		String p_o = tF_Po.getText();
		String a = tF_A.getText();
		String p = tF_P.getText();
		if (inverted && p.equals("")) {
			p = "1";
		} else if (!inverted && a.equals("")) {
			a = "1";
		}
		try {
			String res = CalcCore.instance.calcDB(a, p, k, p_o, inverted) + "";
			if (inverted) {
				tF_P.setText(res);
			} else {
				tF_A.setText(res);
			}
		}
		catch (Exception e1) {
			JOptionPane.showInternalMessageDialog(DBConverter.this.getParent().getParent(),
					e1.getMessage());
		}
	}
}
