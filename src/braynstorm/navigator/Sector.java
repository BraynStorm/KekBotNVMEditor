package braynstorm.navigator;

public class Sector {
	public int x, y;
	
	public static Sector[][] sectors = new Sector[500][500];
	
	public Sector(int x, int y){
		this.x = x;
		this.y = y;
		sectors[x][y] = this;
	}
	
	public int getCenterX(){
		return (x - 135) * 192 + 192 / 2;
	}
	
	
	public int getCenterY(){
		return (y - 92) * 192 + 192 / 2;
	}
	
	
	public Sector offset(int x, int y){
		return sectors[this.x + x][this.y + y] == null ? new Sector(x + this.x, y + this.y) : sectors[this.x + x][this.y + y];
	}
	
	public boolean equals(Sector s){
		return s.x == x && s.y == y;
	}
	
	public String toString(){
		return "Xsec: " + x + ", Ysec: " + y;
	}
	
}
