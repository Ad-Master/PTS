package pl.grm.pts;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainFrame extends JFrame {
	private static final long		serialVersionUID	= 1L;
	private HashMap<TabType, Tab>	tabs				= new HashMap<TabType, Tab>();
	public static int				SET_HEIGHT			= 900, SET_WIDTH = 800;
	public static String			SET_TITLE			= "PTS Calc";
	private JPanel					contentPane;
	private JButton					buttondBConvert;
	private JTabbedPane				tabbedPane;
	private JButton					buttonHistogram;
	private JButton					buttonSignalAnalysis;
	private JButton					buttonSignalInSignal;
	private JButton					buttonHarmDistribution;
	private JMenuBar				menuBar;
	private JMenu					fileMenu;
	private JMenuItem				exitMenuItem;
	private JPanel					buttonPane;
	private JMenu					helpMenu;
	private JMenuItem				aboutMenuItem;
	private JButton					buttonDFT;
	private JScrollPane				scrollPane;
	private JButton					buttonFFT;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
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
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(buttonPane);
		scrollPane.setPreferredSize(new Dimension(600, 50));
		topPanel.add(scrollPane, BorderLayout.SOUTH);
		
		buttondBConvert = new JButton("Konw. dB");
		buttondBConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.DBCONVERT);
			}
		});
		buttonPane.add(buttondBConvert);
		
		buttonHistogram = new JButton("Histogram");
		buttonHistogram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.HISTOGRAM);
			}
		});
		buttonPane.add(buttonHistogram);
		
		buttonSignalAnalysis = new JButton("An. sygna³u");
		buttonSignalAnalysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.SIGNAL_ANALYSIS);
			}
		});
		buttonPane.add(buttonSignalAnalysis);
		
		buttonSignalInSignal = new JButton("Sygna³ w sygn.");
		buttonSignalInSignal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.SIGNAL_IN_SIGNAL);
			}
		});
		buttonPane.add(buttonSignalInSignal);
		
		buttonHarmDistribution = new JButton("Rozk³. na harm.");
		buttonHarmDistribution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.HARMONIC_DISTRIBUTION);
			}
		});
		buttonPane.add(buttonHarmDistribution);
		
		buttonDFT = new JButton("DFT");
		buttonDFT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.DFT);
			}
		});
		buttonPane.add(buttonDFT);
		
		buttonFFT = new JButton("FFT");
		buttonFFT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTab(TabType.FFT);
			}
		});
		buttonPane.add(buttonFFT);
		
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
