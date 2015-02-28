package pl.grm.pts.core;

public class DBConvCore {
	
	public double calcDB(double a, double p, double k, double po, boolean inverted) {
		double res;
		if (!inverted) {
			res = k * Math.log10(p / po);
		} else {
			res = Math.pow(10, a / k) * po;
		}
		return res;
	}
}
