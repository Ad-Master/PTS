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
	private FFTCore				fftCore;
	private DBConvCore			dbConvCore;
	
	public CalcCore() {
		this.signalAnalysisCore = new SignalAnalysisCore();
		this.sIS = new SISCore();
		this.dftCore = new DFTCore();
		this.fftCore = new FFTCore();
		this.dbConvCore = new DBConvCore();
	}
	
	public double calcDB(String a, String p, String k, String p_o, boolean inverted)
			throws Exception {
		double ai;
		double pi;
		double ki;
		double poi;
		try {
			ai = Double.parseDouble(a);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(e.getLocalizedMessage() + "\nin A");
		}
		try {
			pi = Double.parseDouble(p);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(e.getLocalizedMessage() + "\nin P");
		}
		try {
			ki = Double.parseDouble(k);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(e.getLocalizedMessage() + "\nin k");
		}
		try {
			poi = Double.parseDouble(p_o);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(e.getLocalizedMessage() + "\nin P_o");
		}
		return this.dbConvCore.calcDB(ai, pi, ki, poi, inverted);
	}
	
	public Vector<Complex> calcDFT(String txt) throws Exception {
		SimpleVector vec = VectorUtils.toVector(txt);
		Vector<Point> iVec = vec.toPointVector();
		this.dftCore.setSignal(iVec);
		Vector<Complex> resultSignal = this.dftCore.getResultSignal();
		return resultSignal;
	}
	
	public Vector<Complex> calcFFT(String txt) throws Exception {
		Vector<Complex> vec = VectorUtils.toComplexVector(txt);
		Vector<Complex> resultSignal = this.fftCore.fft(vec, false);
		return resultSignal;
	}
	
	public synchronized void calcSignalAnalysis(SignalAnalysis signalAnalysis) throws Exception {
		ConcurrentHashMap<Integer, JTextField> samplesF = signalAnalysis.getSamples();
		ConcurrentHashMap<SignalDataType, JTextField> signalOutputs = signalAnalysis.getOutputs();
		ConcurrentHashMap<Integer, Double> samples = new ConcurrentHashMap<Integer, Double>();
		for (Integer sampleID : samplesF.keySet()) {
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
		this.signalAnalysisCore.calculate(samples, signalOutputs);
	}
	
	public synchronized void calcSignalInSignal(String vecS1, String vecS2,
			HashMap<String, Component> outputs) throws Exception {
		SimpleVector vec1 = VectorUtils.toVector(vecS1);
		SimpleVector vec2 = VectorUtils.toVector(vecS2);
		if (vec1.getSize() == vec2.getSize()) {
			this.sIS.calc(vec1, vec2, outputs);
		} else {
			throw new Exception("Ró¿ne wielkoœci wektorów!");
		}
	}
}
