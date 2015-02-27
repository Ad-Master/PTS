package pl.grm.pts.tabs;

import java.awt.*;

import javax.swing.*;

import pl.grm.pts.*;

public class FFT extends JPanel implements Tab {
	public FFT() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Top = new JPanel();
		add(panel_Top, BorderLayout.NORTH);
		
		JPanel panel_Bottom = new JPanel();
		add(panel_Bottom, BorderLayout.SOUTH);
		
		JPanel panel_Main = new JPanel();
		add(panel_Main, BorderLayout.CENTER);
	}
	
	private static final long	serialVersionUID	= 1L;
	
}
