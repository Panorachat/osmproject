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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
