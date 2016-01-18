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
		
		double lat1 = this.ancestor.getB().getMinLat();
		double lat2 = lat1;
		double lon1 = this.ancestor.getB().getMinLon();
		double lon2 = this.ancestor.getB().getMaxLon();

	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lon2-lon1);
	    double d = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1-d));
	    double dist = ((earthRadius * c)/6)/this.ancestor.getZoom();
	    
	    return (int) dist;
		
	}
	
	public static ImageIcon scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return new ImageIcon(img);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String label = getDistanceScale() + " m";
		this.distanceLabel.setText(label);
	}
}
