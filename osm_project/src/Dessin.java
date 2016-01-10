import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * Classe Graphique qui se charge du dessin
 * @author Moi lol
 * @version 1.0
 * @since 2015
 */
public class Dessin extends JFrame implements MouseMotionListener {
    private static final long serialVersionUID = 1L;//Aucune idée mais y'a plus d'erreurs
    protected final static int res_x=900;//lat
    protected final static int res_y=900;//lon
    
    private Surface map;
    private SurfaceMenu UI;
    private ScaleBar SB;
    
    private JLayeredPane layers; // Pour la superposition des éléments
    
    public Dessin() {
        initUI();
        map.addMouseMotionListener(this);
    }
    /**
     * Fonction qui sert a créer la fenetre ou sera affiche la map
     * @version 1.0
     */
    private void initUI() {
 
    	this.layers = new JLayeredPane();
    	
        setTitle("Map");
        setSize(res_x, res_y);
        
    	this.layers = new JLayeredPane();
    	
        this.setMap(new Surface());
        this.layers.add(this.map);
        this.layers.setLayer(this.map, 0);
        
        this.UI = new SurfaceMenu(this);
        
        this.SB = new ScaleBar(this);
        this.layers.add(this.SB);
        this.layers.setLayer(this.SB, 0);
        
        System.out.println(layers.getSize().width);

        setLayout(new BorderLayout());
        
        getContentPane().add(layers, BorderLayout.CENTER);
        getContentPane().add(UI, BorderLayout.NORTH);
        //this.add(SB);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
	public Surface getMap() {
		return map;
	}
	public void setMap(Surface map) {
		this.map = map;
	}
	
	public SurfaceMenu getUI() {
		return UI;
	}
	public void setUI(SurfaceMenu UI) {
		this.UI = UI;
	}
	@Override
	public void mouseDragged(MouseEvent evt) {
		// TODO Auto-generated method stub
		evt.translatePoint(evt.getComponent().getLocation().x, evt.getComponent()
	            .getLocation().y);
	    map.setLocation(evt.getX(), evt.getY());
	    repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}