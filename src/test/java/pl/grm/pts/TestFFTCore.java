package pl.grm.pts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.*;

import org.junit.*;

import pl.grm.pts.core.fourier.*;
import pl.grm.pts.core.fourier.util.*;

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
	
}
