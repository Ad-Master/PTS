package pl.grm.pts;

import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

import pl.grm.pts.core.*;
import pl.grm.pts.tabs.*;

public class CalcCore {
	public static CalcCore		instance;
	private SignalAnalysisCore	signalAnalysisCore;
	
	public CalcCore() {
		this.signalAnalysisCore = new SignalAnalysisCore();
	}
	
	public synchronized void calcSignalAnalysis(SignalAnalysis signalAnalysis) throws Exception {
		ConcurrentHashMap<Integer, JTextField> samplesF = signalAnalysis.getSamples();
		ConcurrentHashMap<SignalDataType, JTextField> signalOutputs = signalAnalysis.getOutputs();
		ConcurrentHashMap<Integer, Double> samples = new ConcurrentHashMap<Integer, Double>();
		for (Iterator<Integer> it = samplesF.keySet().iterator(); it.hasNext();) {
			Integer sampleID = it.next();
			JTextField jTextField = samplesF.get(sampleID);
			String text = jTextField.getText();
			double value;
			if (text.equals("") || text.equals(" ")) {
				value = 0;
			} else {
				try {
					value = Double.parseDouble(text);
				}
				catch (NumberFormatException e) {
					throw new Exception("Z³a probka o id " + sampleID + " o wartosci " + text, e);
				}
			}
			samples.put(sampleID, value);
		}
		signalAnalysisCore.calculate(samples, signalOutputs);
	}
	
}
