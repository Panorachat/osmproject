package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public final class SurfaceMenu extends JLayeredPane implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel menuZoom = new JPanel();
	private JPanel menuSearch = new JPanel();
	private Dessin ancestor;
	

	public SurfaceMenu(Dessin frame) {
		this.ancestor = frame;
		this.setBounds(this.ancestor.getWidth()-200, 0, 200, this.ancestor.getHeight());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(new ZoomMenu(this.ancestor));
		this.add(new SearchMenu(this.ancestor));
	}

	public void paintComponent(Graphics g){
		this.setBounds(this.ancestor.getWidth()-200, 0, 200, this.ancestor.getHeight());
		super.paintComponent(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		

	}
}