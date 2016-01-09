import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		more.addActionListener(this);
		Button less = new Button("less");
		less.addActionListener(this);
		menuZoom.setLayout(new BorderLayout()); // ajout du layout manager qui permet la disposition des �l�ments NORTH, SOUTH, CENTER, EAST ou WEST
		menuZoom.add(more, BorderLayout.WEST);
		menuZoom.add(less, BorderLayout.EAST);
	}

	/**
	 * Menu search qui contient un label et un input de recherche
	 */
	public void initMenuSearchButtons(){
		JLabel label = new JLabel("Chercher un lieu"); // label
		JTextField searchZone = new JTextField("Montpellier"); // zone de saisi	
		menuSearch.setLayout(new BorderLayout());
		menuSearch.add(label, BorderLayout.WEST);
		menuSearch.add(searchZone, BorderLayout.EAST);
	}

	public SurfaceMenu(Dessin frame) {
		initMenuZoomButtons();
		initMenuSearchButtons();
		new JPanel(new BorderLayout());
		setLayout(new BorderLayout());
		add(menuZoom, BorderLayout.WEST); // ajout du zoom � la surface nord ouest de la fen�tre
		add(menuSearch, BorderLayout.EAST); // ajout de la zone de recherche � la surface nord est de la fen�tre
		this.ancestor = frame;
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
	}
}