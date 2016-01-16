package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ZoomMenu extends JPanel implements ActionListener {
	
	private Dessin ancestor;
	
	public ZoomMenu(Dessin frame){
		this.ancestor = frame;
		Button more = new Button("more");
		more.setSize(70, 70);
		more.addActionListener(this);
		Button less = new Button("less");
		less.setSize(70, 70);
		less.addActionListener(this);
		this.setBounds(30, 200, 140, 70);
		this.setLayout(new BorderLayout()); // ajout du layout manager qui permet la disposition des elements NORTH, SOUTH, CENTER, EAST ou WEST
		this.add(more, BorderLayout.WEST);
		this.add(less, BorderLayout.EAST);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
