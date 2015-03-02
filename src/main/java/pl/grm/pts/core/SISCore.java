package pl.grm.pts.core;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import pl.grm.pts.core.misc.SimpleVector;
import pl.grm.pts.core.misc.VectorUtils;

public class SISCore {
	
	private SimpleVector				vec1;
	private SimpleVector				vec2;
	private HashMap<String, Component>	outputs;
	private int							size;
	
	public void calc(SimpleVector vec1, SimpleVector vec2, HashMap<String, Component> outputs) {
		this.vec1 = vec1;
		this.vec2 = vec2;
		this.size = vec1.getSize();
		this.outputs = outputs;
		
		printOnOutputs();
	}
	
	public void printOnOutputs() {
		JToggleButton tgBtn = (JToggleButton) outputs.get("stateBtTg");
		double e1;
		double e2;
		if (tgBtn.isSelected()) {
			e1 = calcE(vec2);
			e2 = calcE(vec1);
		} else {
			e1 = calcE(vec1);
			e2 = calcE(vec2);
		}
		((JTextField) outputs.get("ev")).setText(e1 + "");
		((JTextField) outputs.get("ew")).setText(e2 + "");
		((JCheckBox) outputs.get("og")).setSelected(isOG());
		((JCheckBox) outputs.get("on")).setSelected(isON());
		double p = calcp(2);
		((JTextField) outputs.get("p")).setText(p + "");
		((JTextField) outputs.get("P")).setText(calcP(p).toString());
	}
	
	private boolean isOG() {
		if (VectorUtils.multiply(vec1, vec2) == 0) { return true; }
		return false;
	}
	
	private boolean isON() {
		if (isOG() == true) {
			if (VectorUtils.multiply(vec1, vec1) == 1 && VectorUtils.multiply(vec2, vec2) == 1) { return true; }
		}
		return false;
	}
	
	private double calcp(int i) {
		double wp = VectorUtils.multiply(vec1, vec2);
		double wv = VectorUtils.multiply(vec1, vec1);
		double p = wp / wv;
		if (i > -1) {
			p *= Math.pow(10, i);
			p = Math.round(p);
			p /= Math.pow(10, i);
		}
		return p;
	}
	
	private SimpleVector calcP(double p) {
		if (p == 0) {
			p = calcp(2);
		}
		VectorUtils.multiply(p, vec1);
		VectorUtils.round(vec1, 2);
		return vec1;
	}
	
	private double calcE(SimpleVector vec) {
		double res = 0;
		for (double v : vec.getValuesD()) {
			res += Math.pow(v, 2);
		}
		return res;
	}
}
