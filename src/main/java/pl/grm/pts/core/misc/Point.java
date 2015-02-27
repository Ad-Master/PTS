package pl.grm.pts.core.misc;

public class Point {
	private float	x, y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
