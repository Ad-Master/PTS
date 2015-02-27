package pl.grm.pts.core.fourier;

import java.util.*;

import pl.grm.pts.core.fourier.util.*;
import pl.grm.pts.core.misc.*;

public class DFTCore {
	private int				N;
	private Vector<Point>	inputSignal;
	
	public DFTCore() {
		this.N = 0;
	}
	
	public DFTCore(Vector<Point> d) {
		this.setSignal(d);
	}
	
	public Complex dftPoint(int m) {
		double twoPi = 2 * Math.PI;
		Complex cx = new Complex();
		if ((m >= 0) && (m < this.N)) {
			double R = 0.0d;
			double I = 0.0d;
			Point p;
			if (m == 0) {
				
				for (int n = 0; n < this.N; n++) {
					p = this.inputSignal.elementAt(n);
					R = R + p.getY();
				}
			} else {
				double x;
				double scale;
				for (int n = 0; n < this.N; n++) {
					p = this.inputSignal.elementAt(n);
					x = p.getY();
					scale = (twoPi * n * m) / this.N;
					R = R + (x * Math.cos(scale));
					I = I - (x * Math.sin(scale));
				}
			}
			cx.setReal((float) R);
			cx.setImaginary((float) I);
		}
		return cx;
	}
	
	public void setSignal(Vector<Point> inputSignal) {
		if (inputSignal != null) {
			int len = inputSignal.size();
			if (len > 0) {
				this.N = len;
				this.inputSignal = inputSignal;
			}
		}
	}
	
	public Vector<Complex> getResultSignal() {
		if (N == 0) { return null; }
		Vector<Complex> result = new Vector<Complex>();
		for (int i = 0; i < this.N; i++) {
			Complex cx = dftPoint(i);
			result.addElement(cx);
		}
		return result;
	}
}
