package pl.grm.pts;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainFrame extends JFrame {
	private static final long		serialVersionUID	= 1L;
	private HashMap<TabType, Tab>	tabs				= new HashMap<TabType, Tab>();
	public static int				SET_HEIGHT			= 800, SET_WIDTH = 600;
	public static String			SET_TITLE			= "PTS Calc";
	private JPanel					contentPane;
	private CalcCore				calcCore;
	private JButton					buttondBConvert;
	private JTabbedPane				tabbedPane;
	private JButton					buttonHistogram;
	private JButton					button_3;
	private JButton					button_2;
	private JButton					button;
	private JMenuBar				menuBar;
	private JMenu					fileMenu;
	private JMenuItem				exitMenuItem;
	private JLayeredPane			layeredPane;
	private JMenu					helpMenu;
	private JMenuItem				aboutMenuItem;
	
	/**
	 * Create the frame.
	 * 
	 * @param calcCore
	 */
	public MainFrame(CalcCore calcCore) {
		this.calcCore = calcCore;
		this.setTitle(SET_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, SET_WIDTH, SET_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		topPanel.add(menuBar, BorderLayout.NORTH);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		aboutMenuItem = new JMenuItem("About ...");
		helpMenu.add(aboutMenuItem);
		
		layeredPane = new JLayeredPane();
		topPanel.add(layeredPane);
		layeredPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttondBConvert = new JButton("Konwertuj dB");
		buttondBConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.DBCONVERT);
			}
		});
		layeredPane.add(buttondBConvert);
		
		buttonHistogram = new JButton("Histogram");
		buttonHistogram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.HISTOGRAM);
			}
		});
		layeredPane.add(buttonHistogram);
		
		button_3 = new JButton("New button");
		layeredPane.add(button_3);
		
		button_2 = new JButton("New button");
		layeredPane.add(button_2);
		
		button = new JButton("New button");
		layeredPane.add(button);
		
		tabbedPane = new ClosableTabbedPane();
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
	
	protected void addTab(TabType tabType) {
		Tab tab = tabType.getTab();
		if (tab != null) {
			tabs.put(tabType, tab);
			tabbedPane.addTab(tabType.getName(), (JPanel) tab);
			tabbedPane.setSelectedComponent((JPanel) tab);
		}
	}
	
}
