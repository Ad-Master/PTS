package pl.grm.pts.core.misc;

import java.util.*;

import pl.grm.pts.core.fourier.util.*;

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
					throwBadInputException(e);
				}
			}
			double[] valArray = toArray(values);
			SimpleVector vector = new SimpleVector(valArray);
			return vector;
		}
		throwBadInputException(null);
		return null;
	}
	
	public static Vector<Complex> toComplexVector(String txt) throws NumberFormatException {
		int stringLength = txt.length();
		ArrayList<Complex> values = new ArrayList<Complex>();
		txt = txt.replace(',', '.');
		if (txt.length() > 0) {
			if (txt.charAt(0) == '[' && txt.charAt(stringLength - 1) == ']') {
				txt = txt.substring(1, stringLength - 1);
				stringLength = txt.length();
			}
			String[] valSA = txt.split(" ");
			for (String valS : valSA) {
				double real = 0;
				double imag = 0;
				try {
					if (valS.contains("j")) {
						int iSign = 0;
						boolean plus = false;
						boolean doublePart = false;
						if (valS.contains("+")) {
							iSign = valS.indexOf('+');
							plus = true;
							doublePart = true;
						} else if (valS.contains("-")) {
							iSign = valS.indexOf('-');
							doublePart = true;
						}
						if (doublePart) {
							String fPart = valS.substring(0, iSign);
							String sPart = valS.substring(iSign + 1);
							boolean gotImaginary = false;
							if (fPart.contains("j")) {
								gotImaginary = true;
								int iJ = fPart.indexOf('j');
								if (iJ == 0 || iJ == 1) {
									if (fPart.length() > 1) {
										imag = Double.parseDouble(fPart.substring(iJ + 1));
									} else {
										imag = 1;
									}
								} else if (iJ == fPart.length() - 1) {
									imag = Double.parseDouble(fPart.substring(0, iJ + 1));
								}
							} else {
								real = Double.parseDouble(fPart);
							}
							if (!gotImaginary && sPart.contains("j")) {
								gotImaginary = true;
								int iJ = sPart.indexOf('j');
								if (iJ == 0) {
									if (sPart.length() > 1) {
										imag = Double.parseDouble(sPart.substring(iJ));
									} else {
										imag = 1;
									}
								} else if (iJ == sPart.length() - 1) {
									String substring = sPart.substring(0, iJ);
									imag = Double.parseDouble(substring);
								}
								if (!plus) {
									imag = -imag;
								}
							} else {
								real = Double.parseDouble(sPart);
							}
						}
					} else {
						real = Double.parseDouble(valS);
					}
					Complex cx = new Complex(real, imag);
					values.add(cx);
				}
				catch (Exception e) {
					throwBadInputException(e);
				}
			}
			Vector<Complex> vector = new Vector<Complex>(values);
			return vector;
		}
		throwBadInputException(null);
		return null;
	}
	
	public static void throwBadInputException(Exception e) throws NumberFormatException {
		throw new NumberFormatException(
				"Bad input string syntax. \nExpected \"[x x x x]\" where x are numbers\n" + e == null
						? "" : e.getLocalizedMessage());
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
