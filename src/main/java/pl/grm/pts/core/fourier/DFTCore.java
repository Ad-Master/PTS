package pl.grm.pts.core.fourier;

import java.util.Vector;

import pl.grm.pts.core.misc.Complex;
import pl.grm.pts.core.misc.Point;

public class DFTCore {
	private int				signalSize;
	private Vector<Point>	inputSignal;
	
	public DFTCore() {
		this.signalSize = 0;
	}
	
	public DFTCore(Vector<Point> inputSignal) {
		this();
		if (inputSignal != null) {
			this.setSignal(inputSignal);
			this.signalSize = inputSignal.size();
		}
	}
	
	public void setSignal(Vector<Point> inputSignal) {
		if (inputSignal != null) {
			int size = inputSignal.size();
			if (size > 0) {
				this.signalSize = size;
				this.inputSignal = inputSignal;
			}
		}
	}
	
	public Complex getPoint(int index) {
		double R = 0.0d;
		double I = 0.0d;
		if ((index >= 0) && (index < this.signalSize)) {
			if (index == 0) {
				for (int n = 0; n < this.signalSize; n++) {
					Point p = this.inputSignal.elementAt(n);
					R += p.getY();
				}
			} else {
				for (int n = 0; n < this.signalSize; n++) {
					Point p = this.inputSignal.elementAt(n);
					double y = p.getY();
					double scale = (2 * Math.PI * n * index) / this.signalSize;
					R += (y * Math.cos(scale));
					I -= (y * Math.sin(scale));
				}
			}
		}
		return new Complex((float) R, (float) I);
	}
	
	public Vector<Complex> getResultSignal() {
		if (signalSize == 0) { return null; }
		Vector<Complex> result = new Vector<Complex>();
		for (int i = 0; i < this.signalSize; i++) {
			Complex cx = getPoint(i);
			result.addElement(cx);
		}
		return result;
	}
	
	public int getSignalSize() {
		return signalSize;
	}
}
