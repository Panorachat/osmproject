package GUI;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import map.Surface;

/**
 * Classe Graphique qui se charge du dessin
 * @author Moi lol
 * @version 1.0
 * @since 2015
 */
public class Dessin extends JFrame implements MouseMotionListener {
    private static final long serialVersionUID = 1L;
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
    	JLayeredPane contentPane = new JLayeredPane();
        setTitle("Map");
        setBounds(100, 100, res_x, res_y);
        this.setContentPane(contentPane);
        this.map = new Surface(this);
        this.scaleBar = new ScaleBar(this);
        this.UI = new SurfaceMenu(this);
        this.UI.setOpaque(true);
        setLayout(new BorderLayout());

        getContentPane().add(this.UI);

        getContentPane().add(this.map);
        System.out.println(this.getUI().getWidth());
       
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

	public ScaleBar getScaleBar(){
		return this.scaleBar;
	}
	
//	public JComponent setDisplay(){
//		JComponent contentPane = createContentPane();
//		contentPane.add(map);
//		contentPane.add(scaleBar);
//		return contentPane;
//	}
	
//	public JLayeredPane createContentPane(){
//		contentPane = new JLayeredPane();
//        contentPane.setBounds(100, 100, this.getWidth(), this.getHeight());
//        contentPane.setLayout(new BorderLayout(0, 0));
//        contentPane.setVisible(true);
//        return contentPane;
//	}
	
	@Override
	public void mouseDragged(MouseEvent evt) {
		// TODO Auto-generated method stub
		evt.translatePoint(evt.getComponent().getLocation().x, evt.getComponent()
	            .getLocation().y);
	    map.setLocation(evt.getX(), evt.getY());
	    
	}
	
	public void mouseReleased(MouseEvent evt){
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}