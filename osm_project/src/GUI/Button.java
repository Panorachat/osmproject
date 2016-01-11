package GUI;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException; 

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton{

	private static final long serialVersionUID = 1L;
	private String name;
	private Image img;

	/**
	 * Constructeur de bouton
	 * @param str : nom du fichier
	 */
	public Button(String str){
		super(str);
		this.name = str;
		try {
			img = ImageIO.read(new File(name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Dessin de l'icone du bouton
	 * @param g : objet graphique
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		img.getScaledInstance(2, 2, Image.SCALE_DEFAULT);
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}   
}