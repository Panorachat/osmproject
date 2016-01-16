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

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScaleBar extends JPanel{

	private static final long serialVersionUID = 1L;
	private int barWidth;
	private int barHeight;
	private int barX_offset;
	private int barY_offset;
	private Image img;
	private Dessin ancestor;
	
	public ScaleBar(Dessin window){
		try {
			this.img = ImageIO.read(new File("img/scaleBar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.ancestor = window;
		this.initProperties();

		this.setBounds(barX_offset, barY_offset, barWidth, barHeight);
		this.setOpaque(true);
		setLayout(new BorderLayout());
	}
	
	public void initProperties(){
		this.barWidth = 180;
		this.barHeight = 70;
		this.barX_offset = 100;
		this.barY_offset = 500;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, 0, 0, this.barWidth, this.barHeight, this);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		
	}   
}
