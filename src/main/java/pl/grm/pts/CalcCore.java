package pl.grm.pts;

import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

import pl.grm.pts.core.*;
import pl.grm.pts.core.misc.*;
import pl.grm.pts.core.misc.Vector;
import pl.grm.pts.tabs.*;

public class CalcCore {
	public static CalcCore		instance;
	private SignalAnalysisCore	signalAnalysisCore;
	private SISCore				sIS;
	private VectorUtils			vecUtils;
	
	public CalcCore() {
		this.vecUtils = new VectorUtils();
		this.signalAnalysisCore = new SignalAnalysisCore();
		this.sIS = new SISCore();
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
	
	public synchronized void calcSignalInSignal(String vecS1, String vecS2,
			HashMap<String, Component> outputs) throws Exception {
		Vector vec1 = VectorUtils.toVector(vecS1);
		Vector vec2 = VectorUtils.toVector(vecS2);
		sIS.calc(vec1, vec2, outputs);
	}
	
}
