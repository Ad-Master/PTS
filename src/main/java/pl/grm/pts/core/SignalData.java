package pl.grm.pts.core;

public class SignalData {
	
	private SignalDataType	signalDataType;
	private double			value;
	private double[]		values;
	private boolean			array;
	
	public SignalData(SignalDataType signalDataType) {
		this.setSignalDataType(signalDataType);
	}
	
	public SignalData(SignalDataType signalDataType, double value) {
		this.signalDataType = signalDataType;
		this.value = value;
		setArray(false);
	}
	
	public SignalData(SignalDataType signalDataType, double[] values) {
		this.signalDataType = signalDataType;
		this.setValues(values);
		setArray(true);
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public SignalDataType getSignalDataType() {
		return signalDataType;
	}
	
	private void setSignalDataType(SignalDataType signalDataType) {
		this.signalDataType = signalDataType;
	}
	
	public boolean isArray() {
		return array;
	}
	
	public void setArray(boolean array) {
		this.array = array;
	}
	
	public double[] getValues() {
		return values;
	}
	
	public void setValues(double[] values) {
		this.values = values;
	}
}
