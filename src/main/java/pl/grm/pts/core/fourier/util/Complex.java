package pl.grm.pts.core.fourier.util;

public class Complex {
	private float	real;
	private float	imaginary;
	
	public Complex() {
		this.real = 0.0f;
		this.imaginary = 0.0f;
	}
	
	public Complex(float real, float imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public Complex(Phasor phase) {
		this.real = phase.cx().getReal();
		this.imaginary = phase.cx().getImaginary();
	}
	
	public Complex add(Complex cx) {
		float newR = this.real + cx.real;
		float newI = this.imaginary + cx.imaginary;
		return new Complex(newR, newI);
	}
	
	public Complex divide(Complex divisor) {
		float R2 = divisor.real;
		float I2 = divisor.imaginary;
		float div = (float) (Math.pow(R2, 2) + Math.pow(I2, 2));
		float newR = ((this.real * R2) + (this.imaginary * I2)) / div;
		float newI = ((R2 * this.imaginary) - (this.real * I2)) / div;
		return new Complex(newR, newI);
	}
	
	public Complex divide(float divisor) {
		float R2 = divisor;
		float newR, newI;
		float div = (float) Math.pow(R2, 2);
		newR = (this.real * R2) / div;
		newI = (R2 * this.imaginary) / div;
		return new Complex(newR, newI);
	}
	
	public Complex multiply(Complex rhs) {
		float newR = (this.real * rhs.real) - (this.imaginary * rhs.imaginary);
		float newI = (this.real * rhs.imaginary) + (rhs.real * this.imaginary);
		return new Complex(newR, newI);
	}
	
	public Complex multiply(float rhs) {
		float newR = this.real * rhs;
		float newI = rhs * this.imaginary;
		return new Complex(newR, newI);
	}
	
	public boolean equals(Complex cx) {
		return ((this.real == cx.real) && (this.imaginary == cx.imaginary));
	}
	
	public float getMagnitude() {
		return (float) Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
	}
	
	public Phasor getPhase() {
		return new Phasor(this.getMagnitude(), this.getAngle());
	}
	
	public float getAngle() {
		float angle = (float) Math.atan(this.imaginary / this.real);
		angle = (angle * 180.0f) / (float) Math.PI;
		return angle;
	}
	
	public float getReal() {
		return this.real;
	}
	
	public void setReal(float r) {
		this.real = r;
	}
	
	public float getImaginary() {
		return this.imaginary;
	}
	
	public void setImaginary(float i) {
		this.imaginary = i;
	}
	
	public Complex substract(Complex cx) {
		float newR = this.real - cx.real;
		float newI = this.imaginary - cx.imaginary;
		return new Complex(newR, newI);
	}
	
	@Override
	public String toString() {
		return this.real + (this.imaginary < 0 ? " " : " + ") + this.imaginary + "j";
	}
}
