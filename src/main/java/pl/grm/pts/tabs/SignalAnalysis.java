package pl.grm.pts.tabs;

import java.awt.*;
import java.beans.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import pl.grm.pts.*;

public class SignalAnalysis extends JPanel implements Tab {
	private static final long						serialVersionUID	= 1L;
	private JTextField								tFSignalSamplesCount;
	private JPanel									panelSamples;
	private ConcurrentHashMap<Integer, JTextField>	signalSamplesFields	= new ConcurrentHashMap<Integer, JTextField>();
	private JTextField								tFAvargVal;
	
	public SignalAnalysis() {
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
		
		JLabel lblPrbki = new JLabel("Pr\u00F3bki:");
		panel_Samples.add(lblPrbki);
		
		panelSamples = new JPanel();
		panel_Samples.add(panelSamples);
		
		JScrollPane topScrollPane = new JScrollPane();
		topScrollPane.setViewportView(panel_Samples);
		topPanel.add(topScrollPane);
		
		JPanel sidePanel = new JPanel();
		add(sidePanel, BorderLayout.EAST);
		
		JScrollPane centerScrollPane = new JScrollPane();
		add(centerScrollPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		centerScrollPane.setViewportView(mainPanel);
		mainPanel.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1);
		
		JLabel lbl_1 = new JLabel("Warto\u015Bc srednia sygnalu Xi=u=Xdc=");
		panel_1.add(lbl_1);
		
		tFAvargVal = new JTextField();
		tFAvargVal.setEditable(false);
		panel_1.add(tFAvargVal);
		tFAvargVal.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		mainPanel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		mainPanel.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		mainPanel.add(panel_5);
		
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
}
