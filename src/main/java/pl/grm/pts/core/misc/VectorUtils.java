package pl.grm.pts.core.misc;

import java.util.*;

public class VectorUtils {
	
	public static SimpleVector toVector(String txt) throws NumberFormatException {
		int stringLength = txt.length();
		ArrayList<Double> values = new ArrayList<Double>();
		txt = txt.replace(',', '.');
		if (txt.length() > 0) {
			if (txt.charAt(0) == '[' && txt.charAt(stringLength - 1) == ']') {
				txt = txt.substring(1, stringLength - 1);
				stringLength = txt.length();
			}
			String[] valSA = txt.split(" ");
			for (String valS : valSA) {
				try {
					double valD = Double.parseDouble(valS);
					values.add(valD);
				}
				catch (Exception e) {
					throwBadInputException();
				}
			}
			double[] valArray = toArray(values);
			SimpleVector vector = new SimpleVector(valArray);
			return vector;
		}
		throwBadInputException();
		return null;
	}
	
	public static void throwBadInputException() throws NumberFormatException {
		throw new NumberFormatException(
				"Bad input string syntax. \nExpected \"[x x x x]\" where x are numbers");
	}
	
	public static double[] toArray(ArrayList<Double> values) {
		double[] valArray = new double[values.size()];
		Iterator<Double> it = values.iterator();
		for (int i = 0; it.hasNext(); i++) {
			valArray[i] = it.next();
		}
		return valArray;
	}
	
	public static void multiply(double p, SimpleVector vec) {
		int size = vec.getSize();
		for (int i = 0; i < size; i++) {
			vec.setValue(i, vec.getValue(i) * p);
		}
	}
	
	public static int multiply(SimpleVector vec1, SimpleVector vec2) {
		int size = vec1.getSize();
		int res = 0;
		for (int i = 0; i < size; i++) {
			res += vec1.getValue(i) * vec2.getValue(i);
		}
		return res;
	}
	
	public static void round(SimpleVector vec, int i) {
		for (int n = 0; n < vec.getSize(); n++) {
			double val = vec.getValue(n);
			val *= Math.pow(10, i);
			val = Math.round(val);
			val /= Math.pow(10, i);
			vec.setValue(n, val);
		}
	}
}
