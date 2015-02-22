package pl.grm.pts;

import java.awt.*;

public class Main {
	
	public static void main(String[] args) {
		CalcCore calcCore = new CalcCore();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame(calcCore);
					mainFrame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
