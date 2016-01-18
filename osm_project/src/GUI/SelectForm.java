package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SelectForm extends JPanel{

	private static final long serialVersionUID = 1L;
	private Dessin ancestor;
	private JCheckBox[] cb;
	private final int maxCB = 5;

	public SelectForm(Dessin frame){
		this.ancestor = frame;
		setBackground(Color.white);
		JLabel title = new JLabel("Points d'intérêt :\n");
		add(title);
		cb = new JCheckBox[maxCB];		
		cb[0] = new JCheckBox("Parkings");
		cb[1] = new JCheckBox("Restaurants");
		cb[2] = new JCheckBox("Cafés");
		cb[3] = new JCheckBox("Écoles");
		cb[4] = new JCheckBox("Universités");

		for(int i=0;i<maxCB;i++){
			cb[i].addActionListener(new StateListener());
			add(cb[i]);
		}
		this.setBounds(30, 350, 140, 170);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public class StateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
			switch(((JCheckBox)e.getSource()).getText()){
				case "Parking":
					if(((JCheckBox)e.getSource()).isSelected()){
						//Afficher 
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
				case "Cafés":
					if(((JCheckBox)e.getSource()).isSelected()){
						//Afficher 
					}else {
						//Masquer 
					}
				break;
				case "Écoles":
					if(((JCheckBox)e.getSource()).isSelected()){
						//Afficher 
					}else {
						//Masquer 
					}
				break;
				case "Universités":
					if(((JCheckBox)e.getSource()).isSelected()){
						//Afficher 
					}else {
						//Masquer 
					}
				break;
				
			}
		}
	}
}