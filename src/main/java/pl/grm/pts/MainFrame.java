package pl.grm.pts;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import pl.grm.pts.panels.*;

public class MainFrame extends JFrame {
	private static final long			serialVersionUID	= 1L;
	private HashMap<TabType, JPanel>	tabs				= new HashMap<TabType, JPanel>();
	public static int					HEIGHT				= 800, WIDTH = 600;
	public static String				TITLE				= "PTS Calc";
	private JPanel						contentPane;
	private CalcCore					calcCore;
	private JButton						dBConvertBtn;
	private JTabbedPane					tabbedPane;
	private JButton						button_1;
	private JButton						button_3;
	private JButton						button_2;
	private JButton						button;
	
	/**
	 * Create the frame.
	 * 
	 * @param calcCore
	 */
	public MainFrame(CalcCore calcCore) {
		this.calcCore = calcCore;
		this.setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		dBConvertBtn = new JButton("Konwertuj dB");
		dBConvertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.DBCONVERT);
			}
		});
		topPanel.add(dBConvertBtn);
		
		button_1 = new JButton("New button");
		topPanel.add(button_1);
		
		button_3 = new JButton("New button");
		topPanel.add(button_3);
		
		button_2 = new JButton("New button");
		topPanel.add(button_2);
		
		button = new JButton("New button");
		topPanel.add(button);
		
		tabbedPane = new ClosableTabbedPane();
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
	
	protected void addTab(TabType tabType) {
		switch (tabType) {
			case DBCONVERT :
				DBConverter tab = new DBConverter();
				tabs.put(tabType, tab);
				tabbedPane.addTab("Konwersja dB", tab);
				break;
			default :
				break;
		
		}
	}
	
}
