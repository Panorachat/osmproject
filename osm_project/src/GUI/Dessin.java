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
 * @version 1.0
 * @since 2015
 */
public class Dessin extends JFrame{
    private static final long serialVersionzoomMenuD = 1L;
    protected final static int res_x=1080;//lat
    protected final static int res_y=720;//lon

    private Surface map;
    private SurfaceMenu UI;
    private ScaleBar scaleBar;
    private Menu menuBar;
    private SelectForm selectForm;
    
    public Dessin() {
        initUI();
    }
    /**
     * Fonction qzoomMenu sert a creer la fenetre ou sera affiche la map
     * @version 1.0
     */
    private void initUI() {
    	JLayeredPane contentPane = new JLayeredPane();
        setTitle("Map");
        setBounds(100, 100, res_x, res_y);
        setContentPane(contentPane);
        UI = new SurfaceMenu(this);
        menuBar = new Menu(this);
        setJMenuBar(menuBar.getMenuBar());
        map = new Surface(this);
        selectForm = new SelectForm(this);
        UI.setOpaque(true);
        selectForm.setOpaque(true);
        
        
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
	public ScaleBar getScaleBar(){
		return this.scaleBar;
	}
	
	public SurfaceMenu getUI(){
		return this.UI;
	}
	
	public Menu getMenu(){
		return menuBar;
	}
	
	
}