package pl.grm.pts.tabs.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class HGShowResult extends JDialog implements ActionListener {
	private static final long	serialVersionUID	= 1L;
	private JScrollPane			scrollPane;
	
	/**
	 * Create the dialog.
	 */
	public HGShowResult(Table table) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if (command.equals("OK")) {
			dispose();
		}
	}
}
