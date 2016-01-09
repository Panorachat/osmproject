
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScaleBar extends JPanel{

	private static final long serialVersionUID = 1L;
	private int barWidth;
	private int barHeight;
	private Image img;
	private Dessin ancestor;
	
	public ScaleBar(Dessin window){
		this.ancestor = window;
		this.initScaleBar();
		this.barWidth = (window.getSize().width) / 10;
		this.barHeight = (window.getSize().height)/20;
//		this.setLocation(200, 200);
		new JPanel(new BorderLayout());
		setLayout(new BorderLayout());
//		this.setSize(this.barWidth, this.barHeight);
//		this.setLocation(this.ancestor.getWidth() * (9/10), this.ancestor.getHeight() * (1/10));
		//this.setBounds((window.getSize().width) / 10, (window.getSize().height) * 9/10, this.barWidth, this.barHeight);
	}
	
	public void initScaleBar(){
		try {
			this.img = ImageIO.read(new File("scaleBar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.img, (this.ancestor.getWidth())/12, (this.ancestor.getHeight())/22, null);
//		Graphics2D g2d = (Graphics2D) g.create();
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		
	}   
}
