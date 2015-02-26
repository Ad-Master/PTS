package pl.grm.pts.core.misc;

import java.util.*;

public class VectorUtils {
	
	public static Vector toVector(String txt) throws NumberFormatException {
		int stringLength = txt.length();
		ArrayList<Double> values = new ArrayList<Double>();
		txt = txt.replace(',', '.');
		if (txt.charAt(0) == '[' && txt.charAt(stringLength - 1) == ']') {
			txt = txt.substring(1, stringLength - 1);
			stringLength = txt.length();
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
			Vector vector = new Vector(valArray);
			System.out.println(vector.toString());
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
}
