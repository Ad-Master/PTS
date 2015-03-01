package pl.grm.pts.core.fourier;

import java.util.*;

import pl.grm.pts.core.fourier.util.*;

public class FFTCore {
	
	private Vector<Complex>	lastOutput;
	
	public Vector<Complex> fft(Vector<Complex> inputO, boolean inverted)
			throws NumberFormatException {
		int size = inputO.size();
		double mptd = Math.log(size) / Math.log(2.0);
		int mpt = (int) mptd;
		if ((mpt - mptd) != 0) { throw new NumberFormatException(
				"Input size of signal is not power of 2"); }
		Vector<Complex> output = (Vector<Complex>) inputO.clone();
		int halfSize = size / 2;
		int mptm1 = mpt - 1;
		double constant;
		if (inverted) {
			constant = 2 * Math.PI;
		} else {
			constant = -2 * Math.PI;
		}
		double tReal, tImag;
		int k = 0;
		for (int l = 1; l <= mpt; l++) {
			while (k < size) {
				for (int i = 1; i <= halfSize; i++) {
					double p, arg, cos, sin;
					p = reverseBits(k >> mptm1, mpt);
					arg = (constant * p) / size;
					cos = Math.cos(arg);
					sin = Math.sin(arg);
					tReal = (output.get(k + halfSize).getReal() * cos)
							+ (output.get(k + halfSize).getImaginary() * sin);
					tImag = (output.get(k + halfSize).getImaginary() * cos)
							- (output.get(k + halfSize).getReal() * sin);
					output.get(k + halfSize).setReal((output.get(k).getReal() - tReal));
					output.get(k + halfSize).setImaginary((output.get(k).getImaginary() - tImag));
					output.get(k).setReal((tReal + output.get(k).getReal()));
					output.get(k).setImaginary((tImag + output.get(k).getImaginary()));
					k++;
				}
				k += halfSize;
			}
			k = 0;
			mptm1--;
			halfSize /= 2;
		}
		for (k = 0; k < size; k++) {
			int r = reverseBits(k, mpt);
			if (r > k) {
				tReal = output.get(k).getReal();
				tImag = output.get(k).getImaginary();
				output.get(k).setReal(output.get(r).getReal());
				output.get(k).setImaginary(output.get(r).getImaginary());
				output.get(r).setReal(tReal);
				output.get(r).setImaginary(tImag);
			}
		}
		this.lastOutput = (Vector<Complex>) output.clone();
		return output;
	}
	
	public static int reverseBits(int j, int nu) {
		int k = 0;
		for (int i = 1; i <= nu; i++) {
			k = (2 * k + j) - (2 * (j / 2));
			j = j / 2;
		}
		return k;
	}
	
	public Vector<Complex> getLastResultSignal() {
		return lastOutput;
	}
}
