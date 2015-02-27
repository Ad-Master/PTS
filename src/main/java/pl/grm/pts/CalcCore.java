package pl.grm.pts;

import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

import pl.grm.pts.core.*;
import pl.grm.pts.core.fourier.*;
import pl.grm.pts.core.fourier.util.*;
import pl.grm.pts.core.misc.*;
import pl.grm.pts.core.misc.Point;
import pl.grm.pts.tabs.*;

public class CalcCore {
	public static CalcCore		instance;
	private SignalAnalysisCore	signalAnalysisCore;
	private SISCore				sIS;
	private DFTCore				dftCore;
	
	public CalcCore() {
		this.signalAnalysisCore = new SignalAnalysisCore();
		this.sIS = new SISCore();
		this.dftCore = new DFTCore();
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
		SimpleVector vec1 = VectorUtils.toVector(vecS1);
		SimpleVector vec2 = VectorUtils.toVector(vecS2);
		if (vec1.getSize() == vec2.getSize()) {
			sIS.calc(vec1, vec2, outputs);
		} else {
			throw new Exception("Ró¿ne wielkoœci wektorów!");
		}
	}
	
	public String calcDFT(String txt) {
		SimpleVector vec = VectorUtils.toVector(txt);
		float[] arr = vec.getValuesF();
		Vector<Point> iVec = new Vector<Point>();
		for (int i = 0; i < arr.length; i++) {
			Point p = new Point(i, arr[i]);
			iVec.addElement(p);
		}
		dftCore.setSignal(iVec);
		Vector<Complex> resultSignal = dftCore.getResultSignal();
		String out = "For " + arr.length + " sampled input [";
		for (float f : arr) {
			out += f + " ";
		}
		out += "]\n";
		Iterator<Complex> iterator = resultSignal.iterator();
		while (iterator.hasNext()) {
			Complex cx = iterator.next();
			out += cx.toString() + "\n";
		}
		return out;
	}
	
}
