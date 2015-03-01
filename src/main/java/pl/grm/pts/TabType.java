package pl.grm.pts;

import javax.swing.*;

import pl.grm.pts.tabs.*;

public enum TabType {
	DBCONVERT(
			"Konwersja dB",
			DBConverter.class) ,
	HISTOGRAM(
			"Histogram",
			Histogram.class) ,
	SIGNAL_ANALYSIS(
			"Analiza sygna³u",
			SignalAnalysis.class) ,
	SIGNAL_IN_SIGNAL(
			"Sygna³ w sygnale",
			SignalInSignal.class) ,
	HARMONIC_DISTRIBUTION(
			"Rozk³ad na harmoniczne",
			HarmonicDistribution.class) ,
	DFT(
			"DFT",
			DFT.class) ,
	FFT(
			"FFT",
			FFT.class) ,
	A_LAW(
			"A-law",
			Alaw.class) ,
	M_LAW(
			"M-law",
			Mlaw.class);
	
	private String					name;
	private Class<? extends Tab>	clazz;
	
	private TabType(String name, Class<? extends Tab> clazz) {
		this.name = name;
		this.clazz = clazz;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Tab getTab() {
		Tab tab = null;
		try {
			tab = this.clazz.newInstance();
			((JPanel) tab).setName(this.name);
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tab;
	}
}
