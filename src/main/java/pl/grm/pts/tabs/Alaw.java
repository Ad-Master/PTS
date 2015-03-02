package pl.grm.pts.tabs;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.grm.pts.Tab;

public class Alaw extends JPanel implements Tab {
	private static final long	serialVersionUID	= 1L;
	private JTextField			tF_x;
	
	public Alaw() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Top = new JPanel();
		add(panel_Top, BorderLayout.NORTH);
		
		JLabel lblSygnaX = new JLabel("sygna\u0142 x = ");
		panel_Top.add(lblSygnaX);
		
		tF_x = new JTextField();
		panel_Top.add(tF_x);
		tF_x.setColumns(10);
		
		JPanel panel_Main = new JPanel();
		add(panel_Main, BorderLayout.CENTER);
		
		JPanel panel_Bot = new JPanel();
		add(panel_Bot, BorderLayout.SOUTH);
	}
	
}
