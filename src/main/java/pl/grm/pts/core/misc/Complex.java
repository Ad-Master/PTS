package pl.grm.pts.core.misc;


public class Complex {
	private double	real;
	private double	imaginary;
	
	public Complex() {
		this.real = 0.0f;
		this.imaginary = 0.0f;
	}
	
	public Complex(double newR, double newI) {
		this.real = newR;
		this.imaginary = newI;
	}
	
	public Complex(Phasor phase) {
		this.real = phase.cx().getReal();
		this.imaginary = phase.cx().getImaginary();
	}
	
	public Complex add(Complex cx) {
		double newR = this.real + cx.real;
		double newI = this.imaginary + cx.imaginary;
		return new Complex(newR, newI);
	}
	
	public Complex divide(Complex divisor) {
		double R2 = divisor.real;
		double I2 = divisor.imaginary;
		double div = Math.pow(R2, 2) + Math.pow(I2, 2);
		double newR = ((this.real * R2) + (this.imaginary * I2)) / div;
		double newI = ((R2 * this.imaginary) - (this.real * I2)) / div;
		return new Complex(newR, newI);
	}
	
	public Complex divide(double divisor) {
		double R2 = divisor;
		double newR, newI;
		double div = Math.pow(R2, 2);
		newR = (this.real * R2) / div;
		newI = (R2 * this.imaginary) / div;
		return new Complex(newR, newI);
	}
	
	public Complex multiply(Complex rhs) {
		double newR = (this.real * rhs.real) - (this.imaginary * rhs.imaginary);
		double newI = (this.real * rhs.imaginary) + (rhs.real * this.imaginary);
		return new Complex(newR, newI);
	}
	
	public Complex multiply(double rhs) {
		double newR = this.real * rhs;
		double newI = rhs * this.imaginary;
		return new Complex(newR, newI);
	}
	
	public boolean equals(Complex cx) {
		return ((this.real == cx.real) && (this.imaginary == cx.imaginary));
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
	}
	
	public Phasor getPhase() {
		return new Phasor(this.getMagnitude(), this.getAngle());
	}
	
	public double getAngle() {
		double angle = Math.atan(this.imaginary / this.real);
		angle = (angle * 180.0f) / Math.PI;
		return angle;
	}
	
	public double getReal() {
		return this.real;
	}
	
	public void setReal(double r) {
		this.real = r;
	}
	
	public double getImaginary() {
		return this.imaginary;
	}
	
	public void setImaginary(double i) {
		this.imaginary = i;
	}
	
	public Complex substract(Complex cx) {
		double newR = this.real - cx.real;
		double newI = this.imaginary - cx.imaginary;
		return new Complex(newR, newI);
	}
	
	@Override
	public String toString() {
		String string = "";
		if (this.real <= 0) {
			if (this.real != 0) {
				if (this.real < -0.00001) {
					string += " - " + Math.abs(this.real) + " ";
				}
			}
		} else if (this.real > 0.00001) {
			string += this.real;
		}
		if (this.imaginary <= 0) {
			if (this.imaginary != 0) {
				if (this.imaginary < -0.00001) {
					string += " - " + Math.abs(this.imaginary) + "j";
				}
			}
		} else if (this.imaginary > 0.00001) {
			string += " + " + this.imaginary + "j";
		}
		if (string.equals("")) {
			string = "0";
		}
		return string;
	}
}
