package map;
/*import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;*/

import java.util.ArrayList;

import javax.swing.JPanel;
public class Node extends JPanel {

	private static final long serialVersionUID = 1L;
	private long id;
	private double lon;
	private double lat;
	private boolean visibility;
	private ArrayList<String> tagList = new ArrayList<String>();
	private ArrayList<String> valueList = new ArrayList<String>();
	
	
	public void addTag(String e) {
		this.tagList.add(e);
	}
	public void addValue(String e) {
		this.valueList.add(e);
	}

	public int getTagSize() {
		return this.tagList.size();
	}

	public int getValueSize() {
		return this.valueList.size();
	}
	
	public String getValue(int i) {
		return valueList.get(i);
	}
	
	public String getTag(int i) {
		return tagList.get(i);
	}
	
	public long getId(){
		return id;
	}
	public double getLon() {
		return lon;
	}
	public double getLat() {
		return lat;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Node(){}
	public double[] getCoordinate(){
		double[] coordinates = new double[2];
		coordinates[0]=this.lon;
		coordinates[1]=this.lat;
		return coordinates;
	}
	public Node(Node n){
		this.id=n.getId();
		this.lat=n.getLat();
		this.lon=n.getLon();
		this.visibility=n.getVisibility();
	}
	public Node(int id, double lon, double lat, boolean visibility){
		this.id = id;
		this.lon = lon;
		this.lat = lat;
		this.visibility = visibility;
	}
	public Node(String id, String lon, String lat, String visibility){
		this.id = Long.valueOf(id);
		this.lon = Double.parseDouble(lon);
		this.lat = Double.parseDouble(lat);
		this.visibility = Boolean.getBoolean(visibility);
	}
	public boolean getVisibility(){
		return this.visibility;
	}
}