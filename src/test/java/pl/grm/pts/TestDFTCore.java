package pl.grm.pts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.*;

import org.junit.*;

import pl.grm.pts.core.fourier.*;
import pl.grm.pts.core.fourier.util.*;
import pl.grm.pts.core.misc.*;

public class TestDFTCore {
	
	@Test
	public final void testEmptyConstructor() {
		DFTCore dftCore = new DFTCore();
		assertThat(dftCore.getResultSignal(), nullValue());
	}
	
	@Test
	public final void testVectorConstructor() {
		Vector<Point> vec = new Vector<Point>();
		vec.add(new Point());
		DFTCore dftCore = new DFTCore(vec);
		assertThat(dftCore.getSignalSize(), is(1));
		vec.add(new Point());
		DFTCore dftCore2 = new DFTCore(vec);
		assertThat(dftCore2.getSignalSize(), is(2));
	}
	
	@Test
	public final void testSetSignal() {
		Vector<Point> vec = new Vector<Point>();
		vec.add(new Point());
		DFTCore dftCore = new DFTCore();
		dftCore.setSignal(vec);
		assertThat(dftCore.getSignalSize(), is(1));
	}
	
	@Test
	public final void testGetPoint() {
		Vector<Point> vec = new Vector<Point>();
		vec.add(new Point(0, 1));
		vec.add(new Point(1, 2));
		vec.add(new Point(2, 3));
		vec.add(new Point(3, 4));
		DFTCore dftCore = new DFTCore(vec);
		assertThat(dftCore.getPoint(0).getReal(), is(10D));
		assertThat(dftCore.getPoint(0).getImaginary(), is(0D));
		assertThat(dftCore.getPoint(1).getReal(), is(-2D));
		assertThat(dftCore.getPoint(1).getImaginary(), is(2D));
		assertThat(dftCore.getPoint(2).getReal(), is(-2D));
		assertThat((int) dftCore.getPoint(2).getImaginary(), is(0));
		assertThat(dftCore.getPoint(3).getReal(), is(-2D));
		assertThat(dftCore.getPoint(3).getImaginary(), is(-2D));
	}
	
	@Test
	public final void testGetResulSignal() {
		Vector<Point> vec = new Vector<Point>();
		vec.add(new Point(0, 1));
		vec.add(new Point(1, 2));
		vec.add(new Point(2, 3));
		vec.add(new Point(3, 4));
		DFTCore dftCore = new DFTCore(vec);
		Vector<Complex> resultSignal = dftCore.getResultSignal();
		assertThat(resultSignal.size(), is(4));
		assertThat(resultSignal.get(0).getReal(), is(10D));
		assertThat(resultSignal.get(0).getImaginary(), is(0D));
		assertThat(resultSignal.get(1).getReal(), is(-2D));
		assertThat(resultSignal.get(1).getImaginary(), is(2D));
	}
}
