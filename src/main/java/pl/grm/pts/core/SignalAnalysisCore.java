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
		addData(SignalDataType.DIRECT_CURRENT, calcDC());
		addData(SignalDataType.ALTERNATE_CURRENT, calcAC());
		addData(SignalDataType.ENERGY_Xi, calcEi());
		addData(SignalDataType.ENERGY_ALL, calcEc());
		addData(SignalDataType.ENERGY_AC, calcEAC());
		addData(SignalDataType.ENERGY_DC, calcEDC());
		addData(SignalDataType.POWER_AVG, calcPAvg());
		addData(SignalDataType.POWER_AC, calcPAC());
		addData(SignalDataType.POWER_DC, calcPDC());
		addData(SignalDataType.RMS, calcRMS());
		addData(SignalDataType.BIAS_STD, calcBSTD());
	}
	
	private void addData(SignalDataType signalDataType, double value) {
		signalDatas.put(signalDataType, new SignalData(signalDataType, value));
	}
	
	private void addData(SignalDataType signalDataType, double[] values) {
		signalDatas.put(signalDataType, new SignalData(signalDataType, values));
	}
	
	private double calcDC() {
		double sum = 0;
		for (Double sample : samplesArray) {
			sum += sample;
		}
		return sum / N;
	}
	
	private double[] calcAC() {
		double[] acArray = new double[N];
		double X_DC = signalDatas.get(SignalDataType.DIRECT_CURRENT).getValue();
		for (int i = 0; i < N; i++) {
			Double X_i = samplesArray[i];
			acArray[i] = X_i - X_DC;
		}
		return acArray;
	}
	
	private double[] calcEi() {
		double[] E_i = new double[N];
		for (int i = 0; i < N; i++) {
			Double X_i = samplesArray[i];
			E_i[i] = Math.pow(Math.abs(X_i), 2);
		}
		return E_i;
	}
	
	private double calcEc() {
		double[] values = signalDatas.get(SignalDataType.ENERGY_Xi).getValues();
		double sum = 0;
		for (int i = 0; i < N; i++) {
			sum += values[i];
		}
		return sum;
	}
	
	private double calcEDC() {
		double value = signalDatas.get(SignalDataType.DIRECT_CURRENT).getValue();
		return Math.pow(value, 2) * N;
	}
	
	private double calcEAC() {
		double[] values = signalDatas.get(SignalDataType.ALTERNATE_CURRENT).getValues();
		double sum = 0;
		for (int i = 0; i < N; i++) {
			sum += Math.pow(values[i], 2);
		}
		return sum;
	}
	
	private double calcPAvg() {
		double E = signalDatas.get(SignalDataType.ENERGY_ALL).getValue();
		return E / N;
	}
	
	private double calcPDC() {
		return Math.pow(signalDatas.get(SignalDataType.DIRECT_CURRENT).getValue(), 2);
	}
	
	private double calcPAC() {
		double[] values = signalDatas.get(SignalDataType.ALTERNATE_CURRENT).getValues();
		double res = 0;
		for (int i = 0; i < N; i++) {
			res += Math.pow(values[i], 2);
		}
		return res / N;
	}
	
	private double calcRMS() {
		return Math.sqrt(signalDatas.get(SignalDataType.POWER_AVG).getValue());
	}
	
	private double calcBSTD() {
		double res = 0;
		double[] values = signalDatas.get(SignalDataType.ALTERNATE_CURRENT).getValues();
		for (int i = 0; i < N; i++) {
			res += Math.pow(Math.abs(values[i]), 2);
		}
		return Math.sqrt(res / (N - 1));
	}
	
	private void setOutputs() {
		for (SignalDataType dataType : SignalDataType.values()) {
			if (signalDatas.containsKey(dataType) && signalOutputs.containsKey(dataType)) {
				SignalData sData = signalDatas.get(dataType);
				JTextField jTextField = signalOutputs.get(dataType);
				String txt;
				if (sData.isArray()) {
					txt = "[";
					for (Double val : sData.getValues()) {
						txt += val + "  ";
					}
					txt += "]";
				} else {
					txt = sData.getValue() + "";
				}
				jTextField.setText(txt);
			}
		}
	}
}
