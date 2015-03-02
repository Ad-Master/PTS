package pl.grm.pts.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import pl.grm.pts.CalcCore;
import pl.grm.pts.Tab;
import pl.grm.pts.core.SignalDataType;

public class SignalAnalysis extends JPanel implements Tab {
	private static final long						serialVersionUID	= 1L;
	private JTextField								tFSignalSamplesCount;
	private JPanel									panelSamples;
	private ConcurrentHashMap<Integer, JTextField>	signalSamplesFields	= new ConcurrentHashMap<Integer, JTextField>();
	private JTextField								tF_DC;
	private CalcCore								calcCore;
	private JTextField								tF_AC;
	private JTextField								tF_Ec;
	private JTextField								tF_E_Xi;
	private JTextField								tF_EDC;
	private JTextField								tF_EAC;
	private JTextField								tF_PAvg;
	private JTextField								tF_PAC;
	private JTextField								tF_PDC;
	private JTextField								tF_RMS;
	private JTextField								tF_BSTD;
	
	public SignalAnalysis() {
		this.calcCore = CalcCore.instance;
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_SampleCount = new JPanel();
		topPanel.add(panel_SampleCount);
		
		JLabel lbl1 = new JLabel("Ilo\u015Bc pr\u00F3bek sygna\u0142u:");
		panel_SampleCount.add(lbl1);
		
		tFSignalSamplesCount = new JTextField();
		tFSignalSamplesCount.setColumns(10);
		addChangeListener(tFSignalSamplesCount, e -> {
			changeSamplesCount();
		});
		panel_SampleCount.add(tFSignalSamplesCount);
		
		JPanel panel_Samples = new JPanel();
		FlowLayout flowLayout_S = (FlowLayout) panel_Samples.getLayout();
		flowLayout_S.setAlignment(FlowLayout.LEFT);
		
		JLabel lblPrbki = new JLabel("Pr\u00F3bki:");
		panel_Samples.add(lblPrbki);
		
		panelSamples = new JPanel();
		panel_Samples.add(panelSamples);
		
		JScrollPane topScrollPane = new JScrollPane();
		topScrollPane.setViewportView(panel_Samples);
		topPanel.add(topScrollPane);
		
		JPanel sidePanel = new JPanel();
		add(sidePanel, BorderLayout.EAST);
		
		JButton btnCalc = new JButton("Oblicz");
		sidePanel.add(btnCalc);
		btnCalc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					try {
						calcCore.calcSignalAnalysis(SignalAnalysis.this);
					}
					catch (Exception e1) {
						JOptionPane.showInternalMessageDialog(SignalAnalysis.this.getParent()
								.getParent(), e1.getMessage());
					}
				}).start();
			}
		});
		
		JScrollPane centerScrollPane = new JScrollPane();
		add(centerScrollPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		centerScrollPane.setViewportView(mainPanel);
		mainPanel.setLayout(new GridLayout(15, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_1);
		
		JLabel lbl_1 = new JLabel("Warto\u015Bc srednia sygnalu X_i = u = X_DC =");
		panel_1.add(lbl_1);
		
		tF_DC = new JTextField();
		tF_DC.setEditable(false);
		tF_DC.setColumns(20);
		panel_1.add(tF_DC);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_2);
		
		JLabel lbl_2 = new JLabel("Sk\u0142adowa przemienna AC X_AC = ");
		panel_2.add(lbl_2);
		
		tF_AC = new JTextField();
		tF_AC.setEditable(false);
		tF_AC.setColumns(20);
		panel_2.add(tF_AC);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_3);
		
		JLabel lbl_3 = new JLabel("Energia ca\u0142kowita E_c = ");
		panel_3.add(lbl_3);
		
		tF_Ec = new JTextField();
		tF_Ec.setEditable(false);
		tF_Ec.setColumns(20);
		panel_3.add(tF_Ec);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_4);
		
		JLabel lbl_4 = new JLabel("Energia pr\u00F3bek");
		panel_4.add(lbl_4);
		
		tF_E_Xi = new JTextField();
		tF_E_Xi.setEditable(false);
		panel_4.add(tF_E_Xi);
		tF_E_Xi.setColumns(20);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_5);
		
		JLabel lbl_5 = new JLabel("Energia DC");
		panel_5.add(lbl_5);
		
		tF_EDC = new JTextField();
		tF_EDC.setEditable(false);
		panel_5.add(tF_EDC);
		tF_EDC.setColumns(20);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_6.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_6);
		
		JLabel lbl_6 = new JLabel("Energia AC");
		panel_6.add(lbl_6);
		
		tF_EAC = new JTextField();
		tF_EAC.setEditable(false);
		tF_EAC.setColumns(20);
		panel_6.add(tF_EAC);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_7.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_7);
		
		JLabel lbl_7 = new JLabel("Moc \u015Brednia P_avg = ");
		panel_7.add(lbl_7);
		
		tF_PAvg = new JTextField();
		tF_PAvg.setEditable(false);
		tF_PAvg.setColumns(20);
		panel_7.add(tF_PAvg);
		
		JPanel panel_8 = new JPanel();
		mainPanel.add(panel_8);
		FlowLayout flowLayout_8 = (FlowLayout) panel_8.getLayout();
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
		
		JLabel lbl_8 = new JLabel("Moc AC P_AC = ");
		panel_8.add(lbl_8);
		
		tF_PAC = new JTextField();
		tF_PAC.setEditable(false);
		tF_PAC.setColumns(20);
		panel_8.add(tF_PAC);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_9.getLayout();
		flowLayout_9.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_9);
		
		JLabel lbl_9 = new JLabel("Moc DC P_DC = ");
		panel_9.add(lbl_9);
		
		tF_PDC = new JTextField();
		tF_PDC.setEditable(false);
		tF_PDC.setColumns(20);
		panel_9.add(tF_PDC);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_10.getLayout();
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_10);
		
		JLabel lbl_10 = new JLabel("Wartosc skuteczna RMS X_RMS = ");
		panel_10.add(lbl_10);
		
		tF_RMS = new JTextField();
		tF_RMS.setEditable(false);
		tF_RMS.setColumns(20);
		panel_10.add(tF_RMS);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_11.getLayout();
		flowLayout_11.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(panel_11);
		
		JLabel lbl_11 = new JLabel("Odchylenie standardowe");
		panel_11.add(lbl_11);
		
		tF_BSTD = new JTextField();
		tF_BSTD.setEditable(false);
		tF_BSTD.setColumns(20);
		panel_11.add(tF_BSTD);
		
	}
	
	private void changeSamplesCount() {
		int count;
		String txt = tFSignalSamplesCount.getText();
		if (txt.equals("") || txt.equals("0")) {
			count = signalSamplesFields.size();
		} else {
			try {
				count = Integer.parseInt(txt);
			}
			catch (NumberFormatException e1) {
				tFSignalSamplesCount.setText("1");
				count = signalSamplesFields.size();
			}
		}
		if (signalSamplesFields.size() != count - 1) {
			if (signalSamplesFields.size() < count - 1) {
				for (int i = 0; i < count; i++) {
					if (!signalSamplesFields.containsKey(i)) {
						JTextField tF = new JTextField("0", 3);
						signalSamplesFields.put(i, tF);
						panelSamples.add(tF);
					}
				}
			} else {
				for (Iterator<Integer> it = signalSamplesFields.keySet().iterator(); it.hasNext();) {
					Integer nmb = it.next();
					if (nmb >= count) {
						JTextField comp = signalSamplesFields.get(nmb);
						panelSamples.remove(comp);
						signalSamplesFields.remove(nmb);
					}
				}
			}
		}
	}
	
	public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
		Objects.requireNonNull(text);
		Objects.requireNonNull(changeListener);
		DocumentListener dl = new DocumentListener() {
			private int	lastChange	= 0, lastNotifiedChange = 0;
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				lastChange++;
				SwingUtilities.invokeLater(() -> {
					if (lastNotifiedChange != lastChange) {
						lastNotifiedChange = lastChange;
						changeListener.stateChanged(new ChangeEvent(text));
					}
				});
			}
		};
		text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
			Document d1 = (Document) e.getOldValue();
			Document d2 = (Document) e.getNewValue();
			if (d1 != null)
				d1.removeDocumentListener(dl);
			if (d2 != null)
				d2.addDocumentListener(dl);
			dl.changedUpdate(null);
		});
		Document d = text.getDocument();
		if (d != null)
			d.addDocumentListener(dl);
	}
	
	public ConcurrentHashMap<Integer, JTextField> getSamples() {
		return signalSamplesFields;
	}
	
	public ConcurrentHashMap<SignalDataType, JTextField> getOutputs() {
		ConcurrentHashMap<SignalDataType, JTextField> outputs = new ConcurrentHashMap<SignalDataType, JTextField>();
		outputs.put(SignalDataType.DIRECT_CURRENT, tF_DC);
		outputs.put(SignalDataType.ALTERNATE_CURRENT, tF_AC);
		outputs.put(SignalDataType.ENERGY_ALL, tF_Ec);
		outputs.put(SignalDataType.ENERGY_Xi, tF_E_Xi);
		outputs.put(SignalDataType.ENERGY_AC, tF_EAC);
		outputs.put(SignalDataType.ENERGY_DC, tF_EDC);
		outputs.put(SignalDataType.POWER_AVG, tF_PAvg);
		outputs.put(SignalDataType.POWER_AC, tF_PAC);
		outputs.put(SignalDataType.POWER_DC, tF_PDC);
		outputs.put(SignalDataType.RMS, tF_RMS);
		outputs.put(SignalDataType.BIAS_STD, tF_BSTD);
		return outputs;
	}
}
