package map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GUI.Dessin;

import java.awt.Image;
import java.awt.Stroke;

public class Surface extends JPanel implements ActionListener, MouseWheelListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	Parser p = new Parser();
	static private Bound b = new Bound();
	private double ZOOM = 1;
	private final double MINZOOM = 1; // Minimal zoom
	private final double MAXZOOM = 10; // Maximal zoom
	private final double ZOOMSTEP = 0.4d; // Value of zoom increment
	double position;//Determine position d'un point
	private BufferedImage map;

	int r=0;//Nombre de murs
	String tag = "";
	String value = "";
	Node n1, n2, nf1, nf2;

	public Dessin ancestor;

	public Surface(Dessin frame){
		super();
		this.ancestor = frame;
		addMouseWheelListener(this);
		addMouseMotionListener(this);
	}

	public double getZoom(){
		return this.ZOOM;
	}

	public void setZoom(double x){
		this.ZOOM=x;
	}
	/*private void drawImage(Graphics g2d, Node n){
    	g2d.drawImage(pi, (int)getPosition(n.getLat(),'x'), (int)getPosition(n.getLon(),'y'), this);
    }*/
	private void draw(Graphics2D g2d ,Node n1,Node n2){
		g2d.draw(new Line2D.Double(getPosition(n2.getLat(),'x'), getPosition(n2.getLon(),'y')
				, getPosition(n1.getLat(),'x'), getPosition(n1.getLon(),'y')));
	}
	/**
	 * Fonction qui retourne les coordonnes formates a la taille de la fenetre
	 * @param double Coordonnes 
	 * @param char x(lat) ou y(lon)
	 * @return double
	 * @version 1.0
	 */
	public double getPosition(double val, char coordonee){
		if(coordonee=='x'){
			position=((val-b.getMinLat())/(b.getMaxLat()-b.getMinLat()))*this.getSize().width*Math.cos(val);
		}
		else{
			position=((val-b.getMinLon())/(b.getMaxLon()-b.getMinLon()))*this.getSize().height;

		}
		return position;
	}	
	/**
	 * Fonction Paint le parametre
	 * @param Graphic
	 * @version 1.0
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(Color_Map.background_color.getColor());
		g2d.fillRect(0, 0, this.ancestor.getWidth()-200, this.ancestor.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setPaint(Color.gray);
		g2d.scale(ZOOM, ZOOM);
		Font f = new Font("Name",1,8);
		g2d.setFont(f);
		Stroke s = g2d.getStroke();


		//g2d.drawImage(loadInterestPointIMG(),10,10,this);
		//Parcour de la liste des way
		for (int wi = 0; wi < p.getWays().size(); wi++) {
			GeneralPath figure = new GeneralPath();
			r = 0;
			// Parcours de la liste des "ref" contenues dans les way
			for (int ri = 1; ri < p.getWays().get(wi).getRefSize(); ri++) {
				// Get longitude 1 et coordonÃƒÂ©es du 2nd point
				n1 = Parser.getNode(p.getWays().get(wi).getRef(ri - 1));
				n2 = Parser.getNode(p.getWays().get(wi).getRef(ri));
				if (r == 0) {
					nf1 = n1;
					nf2 = n2;
					figure.moveTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
				}
				if (r == p.getWays().get(wi).getRefSize()) {
					figure.lineTo(getPosition(nf1.getLat(), 'x'), getPosition(nf1.getLon(), 'y'));
					figure.lineTo(getPosition(nf2.getLat(), 'x'), getPosition(nf2.getLon(), 'y'));
					figure.closePath();
					colorWay(g2d, p.getWays().get(wi), figure, nf1, nf2, r);
					g2d.setPaint(Color.gray);
					g2d.setStroke(s);
					r = 0;

				} else {
					figure.lineTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
					figure.lineTo(getPosition(n2.getLat(), 'x'), getPosition(n2.getLon(), 'y'));
					figure.closePath();
					colorWay(g2d, p.getWays().get(wi), figure, n1, n2, r);
					g2d.setPaint(Color.gray);
					g2d.setStroke(s);
					r++;
				}

				// draw(g2d,p.getNode(p.getWays().get(wi).getRef(ri-1)),p.getNode(p.getWays().get(wi).getRef(ri)));
			}
		}
		for (int wi = 0; wi < p.getWays().size(); wi++) {
			for (int ri = 1; ri < p.getWays().get(wi).getRefSize(); ri++) {
				n1 = p.getNode(p.getWays().get(wi).getRef(ri - 1));
				n2 = p.getNode(p.getWays().get(wi).getRef(ri));
				draw(g2d, n1, n2);
			}
		}
		repaint(0, 0, this.ancestor.getWidth() - 200, this.ancestor.getHeight());
		this.ancestor.getUI().setBounds(this.ancestor.getWidth()-200, 0, this.ancestor.getUI().getWidth(),
				this.ancestor.getHeight());
	}
	
	public void initMap(){
		
	}
	/*public void drawBuildingString(Graphics2D g2d, Way w){
		g2d.setPaint(Color.BLACK);
		Node nd1 = p.getNode(w.getRef(0));
		Node nd2 = p.getNode(w.getRef(w.getRefSize()-1));
		g2d.drawString(value, (float) getPosition((nd1.getLat()+nd2.getLat())/2, 'x'), (float) getPosition((nd1.getLon()+nd2.getLon())/2, 'y'));
	}*/
	/**
	 * Constructeur de l'objet Compte.
	 * @param ActionEvent 
	 * @version 1.0
	 */
	public void actionPerformed(ActionEvent e) {
		// repaint();
	}

	
	//permet d'avoir la distance entre 2 nodes
	public double getDistance(Node n1, Node n2) {
		return Math.sqrt((Math.pow((getPosition(n2.getLat(), 'x')-getPosition(n1.getLat(), 'x')), 2)) + Math.pow((getPosition(n2.getLon(), 'y')- getPosition(n1.getLon(), 'y')),2));
	}
	
	//permet d'avoir la distance entre 2 points
	public double getDistance(double xA, double yA, double xB, double yB) {
		return Math.sqrt((Math.pow(xB - xA, 2)) + Math.pow(yB - yA,2));
	}
	
	//WIP :affiche le nom des rues suivant le bon angle 
	public void displayName(Graphics2D g2d, Way w, GeneralPath figure, Node n1, Node n2) {
		double distanceN1N3 = getDistance(getPosition(n1.getLat(), 'x'),getPosition(n1.getLon(), 'y'),getPosition(n1.getLat(), 'x'),getPosition(n2.getLon(), 'y'));
		double distanceN2N3 = getDistance(getPosition(n2.getLat(), 'x'),getPosition(n2.getLon(), 'y'),getPosition(n1.getLat(), 'x'),getPosition(n2.getLon(), 'y'));
		double distanceN1N2 = getDistance(n1,n2);
		double beta = Math.acos(distanceN1N3/distanceN1N2);
		//System.out.println(distanceN1N3 + " / " + distanceN1N2 + " = " + distanceN1N3/distanceN1N2 + " | " + beta  + " | " + Math.acos(0));
		g2d.rotate((Math.PI/2)-beta,getPosition(n1.getLat(), 'x'),getPosition(n1.getLon(), 'y'));
		figure.moveTo(getPosition(n1.getLat(), 'x') + 1, getPosition(n1.getLon(), 'y') + 1);
		g2d.setPaint(Color.BLACK);
		g2d.drawString(value, (float) getPosition((n1.getLat()+n2.getLat())/2, 'x'), (float) getPosition((n1.getLon()+n2.getLon())/2, 'y'));

	}

	public void colorWay(Graphics2D g2d, Way w, GeneralPath figure, Node n1, Node n2, int r) {
		for (int i = 0; i < w.getTagSize()-1; i++) {
			tag = w.getTag(i);
			value = w.getValue(i);
			switch (tag) {
			case "building":
				//g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color_Map.building_color.getColor());
				g2d.fill(figure);
				//g2d.setPaint(Color.BLACK);
				break;
			case "highway":
				switch (value) {
				case "motorway" :
					g2d.setPaint(Color_Map.motorway_color.getColor());
					g2d.fill(figure);
					break;
				case "trunk" :
					g2d.setPaint(Color_Map.trunk_color.getColor());
					g2d.fill(figure);
					break;
				case "primary" :
					g2d.setPaint(Color_Map.primary_color.getColor());
					g2d.fill(figure);
					break;
				case "secondary" :
					g2d.setPaint(Color_Map.secondary_color.getColor());
					g2d.fill(figure);
					break;
				case "tertiary"  :
					g2d.setPaint(Color_Map.tertiary_color.getColor());
					g2d.fill(figure);
					break;
				case "pedestrian"  :
					g2d.setPaint(Color_Map.pedestrian_color.getColor());
					g2d.fill(figure);
					break;					
				case "residential"  :
					g2d.setPaint(Color_Map.residential_color.getColor());
					g2d.fill(figure);
					break;					
				case "unclassified":
					g2d.setPaint(Color_Map.tertiary_color.getColor());
					g2d.fill(figure);
					break;
				}
				break;

			case "name":
				AffineTransform old = g2d.getTransform(); // sert pour remettre la rotation du graphique a 0
				//String sub = value.substring(0,value.length()-r);
				if (r == 1 || r%9 == 1) {
					displayName(g2d,w,figure,n1,n2);
				}
				g2d.setTransform(old);
				break;
			}
		}
	}

	public void zoom(boolean BUTTONVALUE){
		// Using zoom limits values
		if(this.ZOOM >= MAXZOOM && BUTTONVALUE){
			this.ZOOM = MAXZOOM;
		}
		else if(this.ZOOM <= MINZOOM && !BUTTONVALUE){
			this.ZOOM = MINZOOM;
		}
		else{
			// Zoom/unzoom
			if(BUTTONVALUE){
				this.ZOOM = this.ZOOM + ZOOMSTEP;
			}
			else{
				this.ZOOM = this.ZOOM - ZOOMSTEP;
			}
		}
	}

	public Image loadInterestPointIMG(){
		try {
			Image pi = ImageIO.read(new File("img/PointInteret.png"));
			return pi;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent evt) {
		// TODO Auto-generated method stub
		int wheelValue = evt.getWheelRotation();
		boolean zoomValue;
		if(wheelValue < 0){
			zoomValue = true;
		}
		else if(wheelValue > 0){
			zoomValue = false;
		}
		else{
			throw new IllegalArgumentException("Invalid value");
		}
		this.ancestor.getMap().zoom(zoomValue);
		System.out.println("zoom : " + zoomValue);
		System.out.println("scale : " + this.ancestor.getMap().getZoom());
        System.out.println(this.ancestor.getUI().getWidth());

	}
	
	@Override
	public void mouseDragged(MouseEvent evt) {
		// TODO Auto-generated method stub
		evt.translatePoint(evt.getComponent().getLocation().x, evt.getComponent()
	            .getLocation().y);
	    this.setLocation(evt.getX(), evt.getY());
	    //repaint(0, 0, this.getWidth() - this.ancestor.getUI().getWidth(), this.getHeight());
	    revalidate();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void MouseReleased(MouseEvent evt){
	    repaint(0, 0, this.getWidth() - this.ancestor.getUI().getWidth(), this.getHeight());
	}
}
