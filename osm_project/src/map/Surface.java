package map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GUI.Dessin;
import GUI.ScaleBar;

import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;

public class Surface extends JPanel implements ActionListener, MouseListener, MouseWheelListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	Parser p = new Parser();
	static private Bound b = new Bound();
	
	private double ZOOM = 1;
	private final double MINZOOM = 0.5; // Minimal zoom
	private final double MAXZOOM = 10; // Maximal zoom
	private final double ZOOMSTEP = 0.2d; // Value of zoom increment
	
	double position;//Determine position d'un point
	private ScaleBar scaleBar;
	
	private Point mouseClick = new Point(this.getWidth()/2, this.getHeight()/2);
	private Point mouseReleased = new Point(this.getWidth()/2, this.getHeight()/2);
	private AffineTransform transformer;
	Graphics2D g2d;
	
	private boolean needRepaint = true;
	private boolean parking = true;
	
	int r=0;//Nombre de murs
	String tag = "";
	String value = "";
	Node n1, n2, nf1, nf2;
	
	private BufferedImage mapTemp;

	public Dessin ancestor;

	public Surface(Dessin frame){
		super();
		this.ancestor = frame;
		this.mapTemp = new BufferedImage(this.ancestor.getWidth()-200, this.ancestor.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = mapTemp.getGraphics();
		initMap(g);
		this.setBounds(0, 0, this.ancestor.getWidth()-200, this.ancestor.getHeight());
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		this.scaleBar = new ScaleBar(this);
		this.scaleBar.setOpaque(true);
		this.setBounds(50, 600, this.scaleBar.getWidth(), this.scaleBar.getHeight());
		this.add(scaleBar);
	}

	public double getZoom(){
		return this.ZOOM;
	}

	public void setZoom(double x){
		this.ZOOM=x;
	}
	
	public void setParking(boolean b){
		this.parking = true;
	}
	
	public Bound getB(){
		return this.b;
	}
	/*private void drawImage(Graphics g2d, Node n){
    	g2d.drawImage(pi, (int)getPosition(n.getLat(),'x'), (int)getPosition(n.getLon(),'y'), this);
    }*/
	private void draw(Graphics2D g2d ,Node n1,Node n2){
		g2d.draw(new Line2D.Double(getPosition(n2.getLat(),'x'), getPosition(n2.getLon(),'y')
				, getPosition(n1.getLat(),'x'), getPosition(n1.getLon(),'y')));
	}
	/**
	 * Fonction qui retourne les coordonnes formatees a la taille de la fenetre
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
		if(this.needRepaint){
			Graphics mapTempGraphics = this.mapTemp.getGraphics();
			initMap(mapTempGraphics);
			this.scaleBar.repaint();
			this.needRepaint = false;
		}
		else{
			super.paintComponent(g);
			g.drawImage(mapTemp, 0, 0, null);
		}
		this.ancestor.getUI().repaint();
	}
	
	public void setNeedRepaint(boolean b){
		this.needRepaint = b;
	}
	
	public void initMap(Graphics g){
		g2d = (Graphics2D) g.create();
		g2d.setPaint(ColorMap.background_color.getColor());
		g2d.fillRect(0, 0, this.ancestor.getWidth() - 200, this.ancestor.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setPaint(Color.gray);
		this.transformer = new AffineTransform();
		transformer.translate(mouseClick.getX()-mouseReleased.getX(), mouseClick.getY()-mouseReleased.getY());
		g2d.setTransform(transformer);
	    mouseClick = mouseReleased;
		g2d.scale(ZOOM, ZOOM);
		Font f = new Font("Name",1,8);
		g2d.setFont(f);
		Stroke s = g2d.getStroke();
		Collections.sort(p.Ways);


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
					r++;
				}
				if (r == p.getWays().get(wi).getRefSize()-1) {
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
					r++;
				}

				// draw(g2d,p.getNode(p.getWays().get(wi).getRef(ri-1)),p.getNode(p.getWays().get(wi).getRef(ri)));
			}
		}
		for (int wi = 0; wi < p.getWays().size(); wi++) {
			for (int ri = 1; ri < p.getWays().get(wi).getRefSize(); ri++) {
				if (p.getWays().get(wi).getTagSize() == 0) {
					tag = "";
				} else {
					tag = p.getWays().get(wi).getTag(p.getWays().get(wi).getTagSize()-1);;
				}

				//if (tag != "highway") {
					n1 = Parser.getNode(p.getWays().get(wi).getRef(ri - 1));
					n2 = Parser.getNode(p.getWays().get(wi).getRef(ri));
					draw(g2d, n1, n2);
				//}
			}
		}
		
		//phase pour afficher les noms, WIP
		for (int wi = 0; wi < p.getWays().size(); wi++) {
			GeneralPath figure = new GeneralPath();
			r = 0;
			for (int ri = 1; ri < p.getWays().get(wi).getRefSize(); ri++) {
				n1 = Parser.getNode(p.getWays().get(wi).getRef(ri - 1));
				n2 = Parser.getNode(p.getWays().get(wi).getRef(ri));
				if (r == 0) {
					figure.moveTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
					r++;
				}
				figure.moveTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
				figure.moveTo(getPosition(n2.getLat(), 'x'), getPosition(n2.getLon(), 'y'));

				g2d.setPaint(Color.black);
				nameWay(g2d, p.getWays().get(wi), figure, n1, n2, r, f);
				g2d.setStroke(s);
				r++;
			}
		}

		repaint(0, 0, this.ancestor.getWidth() - 200, this.ancestor.getHeight());
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
			//double distanceN2N3 = getDistance(getPosition(n2.getLat(), 'x'),getPosition(n2.getLon(), 'y'),getPosition(n1.getLat(), 'x'),getPosition(n2.getLon(), 'y'));
			double distanceN1N2 = getDistance(n1,n2);
			double beta = Math.acos(distanceN1N3/distanceN1N2);
			//System.out.println(distanceN1N3 + " / " + distanceN1N2 + " = " + distanceN1N3/distanceN1N2 + " | " + beta  + " | " + Math.acos(0));
			g2d.rotate(-(Math.PI/2)+beta,getPosition(n1.getLat(), 'x'),getPosition(n1.getLon(), 'y'));
			//figure.moveTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y')+50);
			g2d.setPaint(Color.BLACK);
			g2d.drawString(value, (float) (getPosition(n1.getLat(), 'x')), (float) (getPosition(n1.getLon(), 'y')));
			//figure.closePath();
		}

		public void colorWay(Graphics2D g2d, Way w, GeneralPath figure, Node n1, Node n2, int r) {
			for (int i = 0; i < w.getTagSize()-1; i++) {
				tag = w.getTag(i);
				value = w.getValue(i);
				switch (tag) {
				case "building":
					//g2d.setStroke(new BasicStroke(2));
					g2d.setPaint(ColorMap.building_color.getColor());
					g2d.fill(figure);
					//g2d.setPaint(Color.BLACK);
					break;
				case "highway":
					switch (value) {
					case "motorway" :
						g2d.setPaint(ColorMap.motorway_color.getColor());
						g2d.fill(figure);
						break;
					case "trunk" :
						g2d.setPaint(ColorMap.trunk_color.getColor());
						g2d.fill(figure);
						break;
					case "primary" :
						g2d.setPaint(ColorMap.primary_color.getColor());
						g2d.fill(figure);
						break;
					case "secondary" :
						g2d.setPaint(ColorMap.secondary_color.getColor());
						g2d.fill(figure);
						break;
					case "tertiary"  :
						g2d.setPaint(ColorMap.tertiary_color.getColor());
						g2d.fill(figure);
						break;
					case "pedestrian"  :
						g2d.setPaint(ColorMap.pedestrian_color.getColor());
						g2d.fill(figure);
						break;					
					case "residential"  :
						g2d.setPaint(ColorMap.residential_color.getColor());
						g2d.fill(figure);
						break;					
					case "unclassified":
						g2d.setPaint(ColorMap.tertiary_color.getColor());
						g2d.fill(figure);
						break;
					}
				}
			}
		}

	public void nameWay(Graphics2D g2d, Way w, GeneralPath figure, Node n1, Node n2, int r, Font f) {
		for (int i = 0; i < w.getTagSize() - 1; i++) {
			if (w.getTag().contains("highway") || w.getTag().contains("places") || w.getTag().contains("route")) {
				tag = w.getTag(i);
				value = w.getValue(i);

				switch (tag) {
				case "name":
					AffineTransform old = g2d.getTransform(); // sert pour remettre la rotation du graphique a 0
					// String sub = value.substring(0,value.length()-r);
					if (getDistance(n1, n2) >= g2d.getFontMetrics(f).stringWidth(value)) {
						displayName(g2d, w, figure, n1, n2);
					}
					g2d.setTransform(old);
					break;
				}
			}
		}
	}
	

	public void afficherAllPI() {
		GeneralPath figure = new GeneralPath();
		for (int wi = 0; wi < p.getNodes().size(); wi++) {
			n1 = p.getNodes().get(wi);
			figure.moveTo(getPosition(n1.getLat(), 'x'),
					getPosition(n1.getLon(), 'y'));
			imagePI(g2d, n1, figure);
		}
	}

	public void afficherPI(String PI) {
		GeneralPath figure = new GeneralPath();
		for (int wi = 0; wi < p.getNodes().size(); wi++) {
			n1 = p.getNodes().get(wi);
			figure.moveTo(getPosition(n1.getLat(), 'x'),
					getPosition(n1.getLon(), 'y'));
			imagePI(this.getGraphics(), n1, figure, PI);
		}
	}

	public void imagePI(Graphics g2d, Node n, GeneralPath figure) {
		Image img;
		for (int i = 0; i < n.getTagSize() - 1; i++) {
			tag = n.getTag(i);
			value = n.getValue(i);
			try {
				img = ImageIO.read(new File("img/point_interet/" + tag + "/"
						+ value + ".png"));
				g2d.drawImage(img, (int) getPosition(n.getLat(), 'x'),
						(int) getPosition(n.getLon(), 'y'), 15, 15, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public void imagePI(Graphics g2d, Node n, GeneralPath figure, String PI) {
		Image img;
		for (int i = 0; i < n.getTagSize() - 1; i++) {
			tag = n.getTag(i);
			value = n.getValue(i);
			switch (tag) {
			case "amenity":
				if (value.equals(PI)) {
					try {
						img = ImageIO.read(new File("img/point_interet/" + tag
								+ "/" + value + ".png"));
						g2d.drawImage(img, (int) getPosition(n.getLat(), 'x'),
								(int) getPosition(n.getLon(), 'y'), 15, 15,
								this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
				break;
			case "shop":
				if (value.equals(PI)) {
					try {
						img = ImageIO.read(new File("img/point_interet/" + tag
								+ "/" + value + ".png"));
						g2d.drawImage(img, (int) getPosition(n.getLat(), 'x'),
								(int) getPosition(n.getLon(), 'y'), 15, 15,
								this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
				break;
			case "highway":
				if (value.equals(PI)) {
					try {
						img = ImageIO.read(new File("img/point_interet/" + tag
								+ "/" + value + ".png"));
						g2d.drawImage(img, (int) getPosition(n.getLat(), 'x'),
								(int) getPosition(n.getLon(), 'y'), 15, 15,
								this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
				break;
			}
		}
	}

	public Dessin getAncestor() {
		return this.ancestor;
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
        this.needRepaint = true;
        repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent evt) {
		evt.translatePoint(evt.getComponent().getLocation().x, evt.getComponent()
	            .getLocation().y);
	    this.needRepaint = true;
	    repaint();
	    this.mouseReleased = evt.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void MouseReleased(MouseEvent evt){

	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		this.mouseClick = evt.getPoint();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
