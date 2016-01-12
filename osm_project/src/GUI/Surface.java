package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import map.Bound;
import map.Node;
import map.Parser;
import map.Way;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Stroke;

public class Surface extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  static private Bound b = new Bound();
  private double ZOOM = 5;
  private final double MINZOOM = 1; // Minimal zoom
  private final double MAXZOOM = 10; // Maximal zoom
  private final double ZOOMSTEP = 0.4d; // Value of zoom increment
  
  Image pi = Toolkit.getDefaultToolkit().getImage("PointInteret.png");
  double position;//Determine postition d'un point
  int r=0;//Nombre de murs
  String tag = "";
  String value = "";
  Node n1, n2;
  
  public Dessin ancestor;
  
  	public Surface(Dessin frame){
  		super();
  		this.ancestor = frame;
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
        position=((val-b.getMinLat())/(b.getMaxLat()-b.getMinLat()))*this.ancestor.getSize().width*Math.cos(val);
      }
      else{
        position=((val-b.getMinLon())/(b.getMaxLon()-b.getMinLon()))*this.ancestor.getSize().height;
      
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
        g2d.fillRect(0, 0, 900, 900);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setPaint(Color.gray);
        g2d.scale(ZOOM, ZOOM);
        Stroke s = g2d.getStroke();
        //g2d.drawImage(pi,10,10,this);
        //Parcour de la liste des way
        for(int wi=0;wi<Parser.Ways.size();wi++){
            //Parcours de la liste des "ref" contenues dans les way
        	GeneralPath figure = new GeneralPath();
        	r = 0;
        	for(int ri=1;ri<Parser.Ways.get(wi).getRefSize();ri++){
              //Get longitude 1 et coordonnes du 2nd point 	
        		 n1 = Parser.getNode(Parser.Ways.get(wi).getRef(ri - 1));
                 n2 = Parser.getNode(Parser.Ways.get(wi).getRef(ri));
                 if (r == 0) {
                     figure.moveTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
                     r++;
                 }
                 figure.lineTo(getPosition(n1.getLat(), 'x'), getPosition(n1.getLon(), 'y'));
                 figure.lineTo(getPosition(n2.getLat(), 'x'), getPosition(n2.getLon(), 'y'));
                 figure.closePath();
                 colorWay(g2d, Parser.Ways.get(wi), figure, n1, n2);
                 g2d.setPaint(Color.gray);
                 g2d.setStroke(s);
                 
        		//draw(g2d, p.getNode(p.Ways.get(wi).getRef(ri-1)),p.getNode(p.Ways.get(wi).getRef(ri)));
            }
        }
        for (int wi = 0; wi < Parser.Ways.size(); wi++) {
            for (int ri = 1; ri < Parser.Ways.get(wi).getRefSize(); ri++) {
                n1 = Parser.getNode(Parser.Ways.get(wi).getRef(ri - 1));
                n2 = Parser.getNode(Parser.Ways.get(wi).getRef(ri));
                draw(g2d, n1, n2);
            }
        }
        repaint();
    }

    /**
   * Constructeur de l'objet Compte.
   * @param ActionEvent 
   * @version 1.0
   */
    public void actionPerformed(ActionEvent e) {
       // repaint();
    }
    
    public void colorWay(Graphics2D g2d, Way w, GeneralPath figure, Node n1, Node n2) {
        for (int i = 0; i < w.getTagSize(); i++) {
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
                        case "unclassified":
                            g2d.setPaint(Color_Map.tertiary_color.getColor());
                            g2d.fill(figure);
                            break;
                    }
                   
                    
                    break;
                    
                case "name":
                    //g2d.setPaint(Color.BLACK);
                    //g2d.drawString(value, (float) getPosition(n1.getLat(), 'x'), (float) getPosition(n1.getLon(), 'y'));
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

}
//
