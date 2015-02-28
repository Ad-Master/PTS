package pl.grm.pts.core.fourier;

import java.util.*;

import pl.grm.pts.core.fourier.util.*;

public class FFTCore {
	
	public Vector<Complex> fft(Vector<Complex> inputO, boolean inverted)
			throws NumberFormatException {
		int size = inputO.size();
		double mptd = Math.log(size) / Math.log(2.0);
		int mpt = (int) mptd;
		if ((mpt - mptd) != 0) { throw new NumberFormatException(
				"Input size of signal is not power of 2"); }
		@SuppressWarnings("unchecked")
		Vector<Complex> output = (Vector<Complex>) inputO.clone();
		int n2 = size / 2;
		int nu1 = mpt - 1;
		int k = 0;
		double constant;
		if (inverted) {
			constant = 2 * Math.PI;
		} else {
			constant = -2 * Math.PI;
		}
		double tReal, tImag;
		for (int l = 1; l <= mpt; l++) {
			while (k < size) {
				for (int i = 1; i <= n2; i++) {
					double p, arg, cos, sin;
					p = bitreverseReference(k >> nu1, mpt);
					arg = (constant * p) / size;
					cos = Math.cos(arg);
					sin = Math.sin(arg);
					tReal = (output.get(k + n2).getReal() * cos)
							+ (output.get(k + n2).getImaginary() * sin);
					tImag = (output.get(k + n2).getImaginary() * cos)
							- (output.get(k + n2).getReal() * sin);
					output.get(k + n2).setReal((output.get(k).getReal() - tReal));
					output.get(k + n2).setImaginary((output.get(k).getImaginary() - tImag));
					output.get(k).setReal((tReal + output.get(k).getReal()));
					output.get(k).setImaginary((tImag + output.get(k).getImaginary()));
					k++;
				}
				k += n2;
			}
			k = 0;
			nu1--;
			n2 /= 2;
		}
		k = 0;
		int r;
		while (k < size) {
			r = bitreverseReference(k, mpt);
			if (r > k) {
				tReal = output.get(k).getReal();
				tImag = output.get(k).getImaginary();
				output.get(k).setReal(output.get(r).getReal());
				output.get(k).setImaginary(output.get(r).getImaginary());
				output.get(r).setReal(tReal);
				output.get(r).setImaginary(tImag);
			}
			k++;
		}
		return output;
	}
	
	public double[] fft(final double[] inputReal, double[] inputImag, boolean inverted)
			throws NumberFormatException {
		int size = inputReal.length;
		double ld = Math.log(size) / Math.log(2.0);
		int nu = (int) ld;
		if ((nu - ld) != 0) { throw new NumberFormatException(
				"Input size of signal is not power of 2"); }
		double[] xReal = this.clone(inputReal);
		double[] xImag = this.clone(inputImag);
		int n2 = size / 2;
		int nu1 = nu - 1;
		double tReal, tImag;
		int k = 0;
		double constant;
		if (inverted) {
			constant = 2 * Math.PI;
		} else {
			constant = -2 * Math.PI;
		}
		for (int l = 1; l <= nu; l++) {
			while (k < size) {
				for (int i = 1; i <= n2; i++) {
					double p, arg, cos, sin;
					p = bitreverseReference(k >> nu1, nu);
					arg = (constant * p) / size;
					cos = Math.cos(arg);
					sin = Math.sin(arg);
					tReal = (xReal[k + n2] * cos) + (xImag[k + n2] * sin);
					tImag = (xImag[k + n2] * cos) - (xReal[k + n2] * sin);
					xReal[k + n2] = xReal[k] - tReal;
					xImag[k + n2] = xImag[k] - tImag;
					xReal[k] += tReal;
					xImag[k] += tImag;
					k++;
				}
				k += n2;
			}
			k = 0;
			nu1--;
			n2 /= 2;
		}
		// Second phase - recombination
		k = 0;
		int r;
		while (k < size) {
			r = bitreverseReference(k, nu);
			if (r > k) {
				tReal = xReal[k];
				tImag = xImag[k];
				xReal[k] = xReal[r];
				xImag[k] = xImag[r];
				xReal[r] = tReal;
				xImag[r] = tImag;
			}
			k++;
		}
		
		double[] newArray = new double[xReal.length * 2];
		double radice = 1 / Math.sqrt(size);
		for (int i = 0; i < newArray.length; i += 2) {
			int i2 = i / 2;
			// I used Stephen Wolfram's Mathematica as a reference so I'm going
			// to normalize the output while I'm copying the elements.
			newArray[i] = xReal[i2] * radice;
			newArray[i + 1] = xImag[i2] * radice;
		}
		return newArray;
	}
	
	private static int bitreverseReference(int j, int nu) {
		int j2;
		int jj = j;
		int k = 0;
		for (int i = 1; i <= nu; i++) {
			j2 = jj / 2;
			k = ((2 * k) + jj) - (2 * j2);
			jj = j2;
		}
		return k;
	}
	
	public Vector<Complex> getResultSignal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double[] clone(double[] arr) {
		int size = arr.length;
		double[] cArr = new double[size];
		for (int i = 0; i < size; i++) {
			cArr[i] = arr[i];
		}
		return cArr;
	}
}
