package GUI;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import execution.Dessin;

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
		this.initScaleBar();

		this.setLocation(200, 200);
		new JPanel(new BorderLayout());
		setLayout(new BorderLayout());
//		this.setSize(this.barWidth, this.barHeight);
//		this.setLocation(this.ancestor.getWidth() * (9/10), this.ancestor.getHeight() * (1/10));
		//this.setBounds((window.getSize().width) / 10, (window.getSize().height) * 9/10, this.barWidth, this.barHeight);
	}
	
	public void initScaleBar(){
		this.barWidth = 180;
		this.barHeight = (this.ancestor.getSize().height)/35;
		this.barX_offset = (this.ancestor.getSize().width) / 20;
		this.barY_offset = (int) ((this.ancestor.getSize().height)*0.9f);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		this.initScaleBar();
		g2d.drawImage(img, this.barX_offset, this.barY_offset, this.barWidth, this.barHeight, this);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		
	}   
}
