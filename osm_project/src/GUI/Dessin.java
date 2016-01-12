package GUI;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

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
    private ScaleBar scaleBar;
    
    
    public Dessin() {
        initUI();
        map.addMouseMotionListener(this);
    }
    /**
     * Fonction qui sert a creer la fenetre ou sera affiche la map
     * @version 1.0
     */
    private void initUI() {
    	
        setTitle("Map");
        setSize(res_x, res_y);

        this.setMap(new Surface(this));
        this.scaleBar = new ScaleBar(this);
        this.UI = new SurfaceMenu(this);

        setLayout(new BorderLayout());
        
        getContentPane().add(this.UI, BorderLayout.NORTH);
        getContentPane().add(this.map, BorderLayout.CENTER);

        
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
	public ScaleBar getScaleBar(){
		return this.scaleBar;
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