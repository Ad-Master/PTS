package pl.grm.pts;

import javax.swing.*;

import pl.grm.pts.tabs.*;

public enum TabType {
	DBCONVERT(
			"Konwersja dB",
			DBConverter.class) ,
	HISTOGRAM(
			"Histogram",
			Histogram.class);
	
	private String					name;
	private Class<? extends Tab>	clazz;
	
	private TabType(String name, Class<? extends Tab> clazz) {
		this.name = name;
		this.clazz = clazz;
	}
	
	public String getName() {
		return name;
	}
	
	public Tab getTab() {
		Tab tab = null;
		try {
			tab = this.clazz.newInstance();
			((JPanel) tab).setName(name);
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
