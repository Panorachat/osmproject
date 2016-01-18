package GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		

	}
}