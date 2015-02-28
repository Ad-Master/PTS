package pl.grm.pts;

import java.awt.*;
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, SET_WIDTH, SET_HEIGHT);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);
		
		JPanel topPanel = new JPanel();
		this.contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		this.menuBar = new JMenuBar();
		topPanel.add(this.menuBar, BorderLayout.NORTH);
		
		this.fileMenu = new JMenu("File");
		this.menuBar.add(this.fileMenu);
		
		this.exitMenuItem = new JMenuItem("Exit");
		this.fileMenu.add(this.exitMenuItem);
		
		this.helpMenu = new JMenu("Help");
		this.menuBar.add(this.helpMenu);
		
		this.aboutMenuItem = new JMenuItem("About ...");
		this.aboutMenuItem
				.addActionListener(e -> {
					JOptionPane
							.showMessageDialog(
									this,
									"Program napisany zgodnie z materia³ami z przedmiotu Podstawy Teorii Sygna³ów\n\tWykonany przez _Admaster",
									"About", JOptionPane.INFORMATION_MESSAGE);
				});
		this.helpMenu.add(this.aboutMenuItem);
		
		this.buttonPane = new JPanel();
		this.buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.buttonPane);
		this.scrollPane.setPreferredSize(new Dimension(600, 50));
		topPanel.add(this.scrollPane, BorderLayout.SOUTH);
		
		this.buttondBConvert = new JButton("Konw. dB");
		this.buttondBConvert.addActionListener(e -> this.addTab(TabType.DBCONVERT));
		this.buttonPane.add(this.buttondBConvert);
		
		this.buttonHistogram = new JButton("Histogram");
		this.buttonHistogram.addActionListener(e -> this.addTab(TabType.HISTOGRAM));
		this.buttonPane.add(this.buttonHistogram);
		
		this.buttonSignalAnalysis = new JButton("An. sygna³u");
		this.buttonSignalAnalysis.addActionListener(e -> MainFrame.this
				.addTab(TabType.SIGNAL_ANALYSIS));
		this.buttonPane.add(this.buttonSignalAnalysis);
		
		this.buttonSignalInSignal = new JButton("Sygna³ w sygn.");
		this.buttonSignalInSignal.addActionListener(e -> MainFrame.this
				.addTab(TabType.SIGNAL_IN_SIGNAL));
		this.buttonPane.add(this.buttonSignalInSignal);
		
		this.buttonHarmDistribution = new JButton("Rozk³. na harm.");
		this.buttonHarmDistribution.addActionListener(e -> MainFrame.this
				.addTab(TabType.HARMONIC_DISTRIBUTION));
		this.buttonPane.add(this.buttonHarmDistribution);
		
		this.buttonDFT = new JButton("DFT");
		this.buttonDFT.addActionListener(e -> MainFrame.this.addTab(TabType.DFT));
		this.buttonPane.add(this.buttonDFT);
		
		this.buttonFFT = new JButton("FFT");
		this.buttonFFT.addActionListener(e -> MainFrame.this.addTab(TabType.FFT));
		this.buttonPane.add(this.buttonFFT);
		
		this.tabbedPane = new ClosableTabbedPane();
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
	}
	
	protected void addTab(TabType tabType) {
		Tab tab = tabType.getTab();
		if (tab != null) {
			this.tabs.put(tabType, tab);
			this.tabbedPane.addTab(tabType.getName(), (JPanel) tab);
			this.tabbedPane.setSelectedComponent((JPanel) tab);
		}
	}
	
}
