package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
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

//		initMenuZoomButtons();
//		initMenuSearchButtons();
//		setLayout(new BorderLayout());
//		add(menuZoom, BorderLayout.NORTH); // ajout du zoom a la surface nord ouest de la fenetre
//		add(menuSearch, BorderLayout.CENTER); // ajout de la zone de recherche a la surface nord est de la fenetre
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean zoomButtonValue;
		String buttonLabel = evt.getActionCommand();
		switch (buttonLabel){
		case "more":
			zoomButtonValue = true;
			break;
		case "less":
			zoomButtonValue = false;
			break;
		default:
			throw new IllegalArgumentException("Invalid button label: " + buttonLabel);
		}
		this.ancestor.getMap().zoom(zoomButtonValue);
		System.out.println("zoom : " + zoomButtonValue);
		System.out.println("scale : " + this.ancestor.getMap().getZoom());
        System.out.println(this.getWidth());

	}
}