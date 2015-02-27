package pl.grm.pts.core.fourier.util;

public class Phasor {
	private float	magnitude;
	private float	angle;
	
	public Phasor() {
		this.magnitude = 0.0f;
		this.angle = 0.0f;
	}
	
	public Phasor(Complex c) {
		this.magnitude = c.getMagnitude();
		this.angle = c.getAngle();
	}
	
	public Phasor(float magn, float angle) {
		this.magnitude = magn;
		this.angle = angle;
	}
	
	public float getAngle() {
		return this.angle;
	}
	
	public void setAngle(float a) {
		this.angle = a;
	}
	
	public Complex cx() {
		double radA = (this.angle * Math.PI) / 180;
		double R = Math.cos(radA) * this.magnitude;
		double I = Math.sin(radA) * this.magnitude;
		return new Complex((float) R, (float) I);
	}
	
	public float getMagnitude() {
		return this.magnitude;
	}
	
	public void setMagnitude(float m) {
		this.magnitude = m;
	}
}
