package braynstorm.navigator;

public class Point {
	private int x, y, z;


	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		z = 0;
	}
	
	public Point(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(){
		x = 0;
		y = 0;
		z = 0;
	}

	public void setCoords(int X, int Y, int Z){
		x = X;
		y = Y;
		z = Z;
	}
	
	public short[] toShortArray() {
		return new short[] {
			(short) x, (short) y, (short) z	
		};
	}
	
	public int[] toIntArray() {
		return new int[] {
			x,  y,  z	
		};
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
}
