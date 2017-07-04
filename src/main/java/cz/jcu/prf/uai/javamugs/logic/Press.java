package cz.jcu.prf.uai.javamugs.logic;


public class Press {
	
	private int color;
	private double drawTime;
			
	public Press(int color, double drawTime) {
		this.color = color;
		this.drawTime = drawTime;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public double getDrawTime() {
		return this.drawTime;
	}
}
