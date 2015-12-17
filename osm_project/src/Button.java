import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException; 

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	private String name;
	private Image img;

	/**
	 * Constructeur de bouton
	 * @param str : nom du fichier de l'ic�ne � ajouter po
	 */
	public Button(String str){
		super(str);
		this.name = str;
		try {
			img = ImageIO.read(getClass().getResource("/"+name+".png")); // ajout de l'ic�ne relative
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Grâce à cette instruction, notre objet va s'écouter
		//Dès qu'un événement de la souris sera intercepté, il en sera averti
		this.addMouseListener(this);
	}

	/**
	 * Dessin de l'icone du bouton
	 * @param g : objet graphique
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true); // d�grad� de couleur pour le fond du bouton
		g2d.setPaint(gp);
		img.getScaledInstance(2, 2, Image.SCALE_DEFAULT);
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // dessiner l'ic�ne du bouton
		//g2d.setColor(Color.blue); // s�lectionner la couleur
		//g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() /  2 /4), (this.getHeight() / 2) + 5); // �crire le nom du bouton (juste pour tester, car l'affichage d'ic�ne ne marche pas)
	}

	//Méthode appelée lors du clic de souris
	public void mouseClicked(MouseEvent event) { }

	//Méthode appelée lors du survol de la souris
	public void mouseEntered(MouseEvent event) { }

	//Méthode appelée lorsque la souris sort de la zone du bouton
	public void mouseExited(MouseEvent event) { }

	//Méthode appelée lorsque l'on presse le bouton gauche de la souris
	public void mousePressed(MouseEvent event) { }

	//Méthode appelée lorsque l'on relâche le clic de souris
	public void mouseReleased(MouseEvent event) { }       
}