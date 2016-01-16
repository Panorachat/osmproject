package GUI;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import map.Surface;

/**
 * Classe Graphique qzoomMenu se charge du dessin
 * @author Moi lol
 * @version 1.0
 * @since 2015
 */
public class Dessin extends JFrame{
    private static final long serialVersionzoomMenuD = 1L;
    protected final static int res_x=1080;//lat
    protected final static int res_y=720;//lon

    private Surface map;
    private SurfaceMenu UI;
    private ZoomMenu zoomMenu;
    private ScaleBar scaleBar;

    public Dessin() {
        initzoomMenu();
    }
    /**
     * Fonction qzoomMenu sert a creer la fenetre ou sera affiche la map
     * @version 1.0
     */
    private void initzoomMenu() {
    	JLayeredPane contentPane = new JLayeredPane();
        setTitle("Map");
        setBounds(100, 100, res_x, res_y);
        this.setContentPane(contentPane);
        this.map = new Surface(this);
        this.map.add(new ScaleBar(this));
        this.UI = new SurfaceMenu(this);
        this.zoomMenu = new ZoomMenu(this);
        this.zoomMenu.setOpaque(true);
        this.UI.setOpaque(true);
        setLayout(new BorderLayout());
        getContentPane().add(this.UI);
        getContentPane().add(this.map);
       
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public Surface getMap() {
		return map;
	}
	public void setMap(Surface map) {
		this.map = map;
	}
	
	public ZoomMenu getzoomMenu() {
		return zoomMenu;
	}

	public ScaleBar getScaleBar(){
		return this.scaleBar;
	}
	
	public SurfaceMenu getUI(){
		return this.UI;
	}
	
	
}