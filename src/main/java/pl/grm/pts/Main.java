package pl.grm.pts;

import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
	
	public static void main(String[] args) {
		CalcCore calcCore = new CalcCore();
		CalcCore.instance = calcCore;
		EventQueue.invokeLater(() -> {
			try {
				MainFrame mainFrame = new MainFrame();
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Metal".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				mainFrame.setVisible(true);
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		});
	}
}
