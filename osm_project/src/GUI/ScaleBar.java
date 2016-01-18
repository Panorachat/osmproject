package GUI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Node;
import map.Surface;

public class ScaleBar extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int barWidth;
	private int barHeight;
	private int barX_offset;
	private int barY_offset;
	
	private ImageIcon img;
	private ImageIcon converted;
	private Surface ancestor;
	
	private JPanel barPanel;
	private JLabel distanceLabel;
	
	public ScaleBar(Surface window){
		this.ancestor = window;

		this.initProperties();
		this.img = new ImageIcon("img/scaleBar.png");
		this.converted = scaleImage(this.img.getImage(), barWidth, barHeight/2);

		System.out.println("x offset : " + barX_offset + " y offset : " + barY_offset + " width : " + barWidth + " height : " + barHeight);
		this.setOpaque(true);
		
		this.barPanel = new JPanel();
		this.barPanel.add(new JLabel(converted));
		this.barPanel.setSize(this.getWidth(), this.getHeight()/2);
		this.barPanel.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.add(barPanel, BorderLayout.NORTH);
		String label = getDistanceScale() + " m";
		this.distanceLabel = new JLabel(label);
		this.add(distanceLabel, BorderLayout.SOUTH);
		this.distanceLabel.setOpaque(true);
	}
	
	public void initProperties(){
		this.barWidth = 180;
		this.barHeight = 100;
		this.barX_offset = 50;
		this.barY_offset = 550;
	}
	
	public int getDistanceScale(){
		DecimalFormat decimalFormat = (DecimalFormat)DecimalFormat.getInstance();
		decimalFormat.applyPattern("###0.##########");
		int r = 6366; // Rayon de la terre
		double lat1 = this.ancestor.getB().getMinLat();
		double lat2 = lat1;
		double lon1 = this.ancestor.getB().getMinLon();
		double lon2 = this.ancestor.getB().getMaxLon();
//		Node n1 = new Node(-1, lon1, lat1, false);
//		Node n2 = new Node(-2, lon2, lat2, false);
//		while(this.ancestor.getDistance(n1, n2)<180){
//			lon2 += 0.0000000001;
//			n2.setLon(lon2);
//		}
		// Conversion en radians
		double lat1_rad = Math.toRadians(lat1);
		double lon1_rad = Math.toRadians(lon1);
		double lat2_rad = Math.toRadians(lat2);
		double lon2_rad = Math.toRadians(lon2);
		
		// Calcul de la distance
		double d = (2*Math.asin(Math.sqrt(
				Math.pow((Math.sin((lat2_rad-lat1_rad)/2)), 2)
				+
				Math.cos(lat1_rad)*Math.cos(lat2_rad)*
				(Math.pow(Math.sin(((lon2_rad-lon1_rad)/2)), 2))
				)))*r;
		
		return (int) ((d)/(this.ancestor.getZoom()));
	}
	
	public static ImageIcon scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return new ImageIcon(img);
	}
	
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		String str = 500/this.ancestor.getZoom() + " m";
//		g.drawString(str, 10, this.getHeight()/2);
//	}
}
