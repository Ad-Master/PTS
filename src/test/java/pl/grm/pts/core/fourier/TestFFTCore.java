package pl.grm.pts.core.fourier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Vector;

import org.junit.Test;

import pl.grm.pts.core.misc.Complex;

public class TestFFTCore {
	
	@Test
	public final void testFFT() {
		Vector<Complex> vec = new Vector<Complex>();
		vec.add(new Complex(1, 0));
		vec.add(new Complex(2, 0));
		FFTCore fftCore = new FFTCore();
		Vector<Complex> result = fftCore.fft(vec, false);
		Complex cx = result.get(0);
		double real = cx.getReal();
		double imaginary = cx.getImaginary();
		Complex cx2 = result.get(1);
		double real2 = cx2.getReal();
		double imaginary2 = cx2.getImaginary();
		assertThat(real, equalTo(3d));
		assertThat(imaginary, equalTo(0d));
		assertThat(real2, equalTo(-1d));
		assertThat(imaginary2, equalTo(0d));
	}
	
	@Test
	public final void testReverseBits() {
		int i = 1;
		int j = 1;
		int revBits = FFTCore.reverseBits(i >> 1, j);
		assertThat(revBits, equalTo(0));
		i = 2;
		j = 1;
		revBits = FFTCore.reverseBits(i >> 1, j);
		assertThat(revBits, equalTo(1));
		i = 3;
		j = 3;
		revBits = FFTCore.reverseBits(i >> 1, j);
		assertThat(revBits, equalTo(4));
		i = 3;
		j = 2;
		revBits = FFTCore.reverseBits(i >> 1, j);
		assertThat(revBits, equalTo(2));
		i = 2;
		j = 3;
		revBits = FFTCore.reverseBits(i >> 1, j);
		assertThat(revBits, equalTo(4));
	}
}
