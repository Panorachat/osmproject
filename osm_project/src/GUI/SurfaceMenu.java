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

public final class SurfaceMenu extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel menuZoom = new JPanel();
	private JPanel menuSearch = new JPanel();
	private Dessin ancestor;
	
// Test
	/**
	 * Menu zoom qui contient les 2 boutons de zoom
	 */
	public void initMenuZoomButtons(){
		Button more = new Button("more");
		more.setSize(70, 70);
		more.addActionListener(this);
		Button less = new Button("less");
		less.setSize(70, 70);
		less.addActionListener(this);
		menuZoom.setBounds(30, 400, 140, 70);
		menuZoom.setLayout(new BorderLayout()); // ajout du layout manager qui permet la disposition des elements NORTH, SOUTH, CENTER, EAST ou WEST
		menuZoom.add(more, BorderLayout.WEST);
		menuZoom.add(less, BorderLayout.EAST);
	}

	/**
	 * Menu search qui contient un label et un input de recherche
	 */
	public void initMenuSearchButtons(){
		JLabel label = new JLabel("Chercher un lieu"); // label
		JTextField searchZone = new JTextField("Montpellier"); // zone de saisi	
		label.setSize(140, 40);
		searchZone.setSize(140, 40);
		menuSearch.setBounds(30, 30, 140, 90);
		menuSearch.setLayout(new BorderLayout());
		menuSearch.add(label, BorderLayout.NORTH);
		menuSearch.add(searchZone, BorderLayout.SOUTH);
	}

	public SurfaceMenu(Dessin frame) {
		this.ancestor = frame;
		this.setBounds(this.ancestor.getWidth()-200, 0, 200, this.ancestor.getHeight());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		initMenuZoomButtons();
		initMenuSearchButtons();
		setLayout(new BorderLayout());
		add(menuZoom); // ajout du zoom a la surface nord ouest de la fenetre
		add(menuSearch); // ajout de la zone de recherche a la surface nord est de la fenetre
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
        System.out.println(this.ancestor.getUI().getWidth());

	}
}