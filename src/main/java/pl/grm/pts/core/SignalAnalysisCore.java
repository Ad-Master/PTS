package pl.grm.pts.core;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

import javax.swing.*;

public class SignalAnalysisCore {
	private ConcurrentHashMap<Integer, Double>				samples;
	private ConcurrentHashMap<SignalDataType, JTextField>	signalOutputs;
	private HashMap<SignalDataType, SignalData>				signalDatas;
	private Double[]										samplesArray;
	private int												N;
	
	public void calculate(ConcurrentHashMap<Integer, Double> samples,
			ConcurrentHashMap<SignalDataType, JTextField> signalOutputs) {
		this.samples = samples;
		this.signalOutputs = signalOutputs;
		this.N = samples.size();
		genSamplesToArray(this.samples);
		signalDatas = new HashMap<SignalDataType, SignalData>();
		calcDatas();
		setOutputs();
	}
	
	private void genSamplesToArray(ConcurrentHashMap<Integer, Double> samples) {
		this.samplesArray = new Double[N];
		int i = 0;
		for (Iterator<Entry<Integer, Double>> it = samples.entrySet().iterator(); it.hasNext(); i++) {
			samplesArray[i] = it.next().getValue();
		}
	}
	
	private void calcDatas() {
		addData(new SignalData(SignalDataType.DIRECT_CURRENT, calcDC()));
	}
	
	private void addData(SignalData signalData) {
		signalDatas.put(signalData.getSignalDataType(), signalData);
	}
	
	private double calcDC() {
		double sum = 0;
		for (Double sample : samplesArray) {
			sum += sample;
		}
		return sum / N;
	}
	
	private void setOutputs() {
		for (SignalDataType dataType : SignalDataType.values()) {
			if (signalDatas.containsKey(dataType) && signalOutputs.containsKey(dataType)) {
				SignalData sData = signalDatas.get(dataType);
				JTextField jTextField = signalOutputs.get(dataType);
				jTextField.setText(sData.getValue() + "");
			}
		}
	}
}
