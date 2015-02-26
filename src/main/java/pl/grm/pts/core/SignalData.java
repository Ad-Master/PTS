package pl.grm.pts.core;

public class SignalData {
	
	private SignalDataType	signalDataType;
	private double			value;
	
	public SignalData(SignalDataType signalDataType) {
		this.setSignalDataType(signalDataType);
	}
	
	public SignalData(SignalDataType signalDataType, double value) {
		this.signalDataType = signalDataType;
		this.value = value;
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
}
