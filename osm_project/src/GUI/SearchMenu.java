package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchMenu extends JPanel implements ActionListener {
	
	private Dessin ancestor;
	
	public SearchMenu(Dessin frame){
		this.ancestor = frame;
		JLabel label = new JLabel("Chercher un lieu"); // label
		JTextField searchZone = new JTextField("Montpellier"); // zone de saisi	
		label.setSize(140, 40);
		searchZone.setSize(140, 40);
		this.setBounds(30, 30, 140, 90);
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(searchZone, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}
