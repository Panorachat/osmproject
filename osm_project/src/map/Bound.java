package map;
public class Bound {
    private double minLon;
    private double maxLon;
    private double minLat;
    private double maxLat;

    public Bound(){
    	String[] bound=Parser.getBounds();
    	this.minLat=Double.parseDouble(bound[0]);
    	this.minLon=Double.parseDouble(bound[1]);
    	this.maxLat=Double.parseDouble(bound[2]);
    	this.maxLon= Double.parseDouble(bound[3]);
    }
	public double getMinLon() {
		return minLon;
	}
	public void setMinLon(double minLon) {
		this.minLon = minLon;
	}
	public double getMaxLon() {
		return maxLon;
	}
	public void setMaxLon(double maxLon) {
		this.maxLon = maxLon;
	}
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
}