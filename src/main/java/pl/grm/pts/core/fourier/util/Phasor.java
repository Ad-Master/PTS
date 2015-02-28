package pl.grm.pts.core.fourier.util;

public class Phasor {
	private double	magnitude;
	private double	angle;
	
	public Phasor() {
		this.magnitude = 0.0f;
		this.angle = 0.0f;
	}
	
	public Phasor(Complex c) {
		this.magnitude = c.getMagnitude();
		this.angle = c.getAngle();
	}
	
	public Phasor(double magn, double angle) {
		this.magnitude = magn;
		this.angle = angle;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(double a) {
		this.angle = a;
	}
	
	public Complex cx() {
		double radA = (this.angle * Math.PI) / 180;
		double R = Math.cos(radA) * this.magnitude;
		double I = Math.sin(radA) * this.magnitude;
		return new Complex(R, I);
	}
	
	public double getMagnitude() {
		return this.magnitude;
	}
	
	public void setMagnitude(double m) {
		this.magnitude = m;
	}
}
