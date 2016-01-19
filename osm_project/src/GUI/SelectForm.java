package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Surface;


public class SelectForm extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Dessin ancestor;
	private JCheckBox[] cb;
	private final int maxCB = 5;

	public SelectForm(Dessin frame){
		this.ancestor = frame;
		setBackground(Color.white);
		JLabel title = new JLabel("Points d'int�r�t :\n");
		add(title);
		cb = new JCheckBox[maxCB];		
		cb[0] = new JCheckBox("Parkings");
		cb[1] = new JCheckBox("Restaurants");
		cb[2] = new JCheckBox("Caf�s");
		cb[3] = new JCheckBox("Boulangeries");
		cb[4] = new JCheckBox("Tout");

		for(int i=0;i<maxCB;i++){
			cb[i].addActionListener(this);
			add(cb[i]);
		}
		this.setBounds(30, 350, 140, 170);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	//public class StateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - �tat : " + ((JCheckBox)e.getSource()).isSelected());
			if(((JCheckBox)e.getSource()).isSelected()){
				//afficherPI(((JCheckBox)e.getSource()).getText());
			}
			else{
				//masquer
			}
			switch(((JCheckBox)e.getSource()).getText()){
			case "Parking":
				if(((JCheckBox)e.getSource()).isSelected()){
					this.ancestor.getMap().repaint();
					this.ancestor.getMap().afficherPI("parking");
				}else {
					//Masquer 
				}
				break;
			case "Restaurants":
				if(((JCheckBox)e.getSource()).isSelected()){
					//Afficher 
				}else {
					//Masquer 
				}
				break;
			case "Caf�s":
				if(((JCheckBox)e.getSource()).isSelected()){
					//Afficher 
				}else {
					//Masquer 
				}
				break;
			case "Boulangerie":
				if(((JCheckBox)e.getSource()).isSelected()){
					//Afficher 
				}else {
					//Masquer 
				}
				break;
			case "Tout":
				if(((JCheckBox)e.getSource()).isSelected()){
					//Afficher
				}else {
					//Masquer 
				}
				break;
			}
		}
	}
//}