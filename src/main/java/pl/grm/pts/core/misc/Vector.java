package pl.grm.pts.core.misc;

import java.util.*;

public class Vector {
	private double[]	values;
	private int			size;
	
	public Vector(double... values) {
		this.setValues(values);
		setSize(values.length);
	}
	
	public void increaseVector(double... valToAdd) {
		double[] valOld = this.values;
		int oldSize = this.size;
		int toAddSize = valToAdd.length;
		this.setSize(oldSize + toAddSize);
		this.values = new double[size];
		int j = 0, k = 0;
		for (int i = 0; i < size; i++) {
			double newValue = 0;
			if (j < oldSize) {
				newValue = valOld[j];
				j++;
			} else {
				newValue = valToAdd[k];
				k++;
			}
			setValue(i, newValue);
		}
	}
	
	public void setValue(int index, double newValue) {
		if (index < size) {
			values[index] = newValue;
		}
	}
	
	public double getValue(int index) {
		if (index < size) { return values[index]; }
		return 0;
	}
	
	public double[] getValues() {
		return values;
	}
	
	public void setValues(double[] values) {
		this.values = values;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Vector  size = " + this.size + " " + Arrays.toString(this.values);
	}
}
